package com.example.justicelawmovil.viewModel

import android.content.Context
import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justicelawmovil.data.ApiClient
import kotlinx.coroutines.launch
import com.example.justicelawmovil.service.UpdateProfileRequest
import retrofit2.HttpException
import java.io.IOException

sealed class UpdateProfileState {
    object Idle : UpdateProfileState()
    object Loading : UpdateProfileState()
    data class Success(val message: String, val photo: String?) : UpdateProfileState()
    data class Error(val message: String) : UpdateProfileState()
}

class UpdateProfileViewModel : ViewModel() {
    private val _updateProfileState = MutableLiveData<UpdateProfileState>(UpdateProfileState.Idle)
    val updateProfileState: LiveData<UpdateProfileState> = _updateProfileState

    private val apiService = ApiClient.userApiService

    fun updateUserProfile(context: Context, request: UpdateProfileRequest) {
        Log.d("UpdateProfileViewModel", "Request recibido: $request")  // Log para verificar que se recibe el request

        viewModelScope.launch {
            _updateProfileState.value = UpdateProfileState.Loading

            val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val token = sharedPreferences.getString("token", null)

            Log.d("UpdateProfileViewModel", "Token obtenido: $token")

            if (token.isNullOrEmpty()) {
                Log.d("UpdateProfileViewModel", "Token no encontrado")  // Log adicional
                _updateProfileState.value = UpdateProfileState.Error("Token no encontrado")
                return@launch
            }

            try {
                Log.d("UpdateProfileViewModel", "Entrando al bloque try")  // Log adicional
                val response = apiService.updateProfile("Bearer $token", request)

                Log.d("UpdateProfileViewModel", "Código de respuesta: ${response.code()}")  // Log adicional
                Log.d("UpdateProfileViewModel", "Cuerpo de la respuesta: ${response.body()}")  // Log adicional

                if (response.isSuccessful) {
                    val updateResponse = response.body()
                    if (updateResponse != null) {
                        _updateProfileState.value = UpdateProfileState.Success(
                            message = updateResponse.message,
                            photo = updateResponse.photo
                        )
                    } else {
                        _updateProfileState.value = UpdateProfileState.Error("Respuesta del servidor vacía")
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Error desconocido"
                    _updateProfileState.value = UpdateProfileState.Error(errorBody)
                }
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is HttpException -> "Error HTTP: ${e.message()}"
                    is IOException -> "Error de red: ${e.message}"
                    else -> "Error inesperado: ${e.message}"
                }
                Log.e("UpdateProfileViewModel", "Error de conexión: $errorMessage")  // Log adicional
                _updateProfileState.value = UpdateProfileState.Error(errorMessage)
            }
        }
    }
}

