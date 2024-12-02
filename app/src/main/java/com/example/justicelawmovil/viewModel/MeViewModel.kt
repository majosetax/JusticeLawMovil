package com.example.justicelawmovil.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justicelawmovil.data.ApiClient
import com.example.justicelawmovil.model.UserModel
import kotlinx.coroutines.launch

class MeViewModel : ViewModel() {
    private val _meState = MutableLiveData<MeState>()
    val meState: LiveData<MeState> = _meState

    private val apiService = ApiClient.userApiService

    fun getUserInfo(context: Context) {
        viewModelScope.launch {
            _meState.value = MeState.Loading

            // Obtener el token guardado en SharedPreferences
            val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val token = sharedPreferences.getString("token", null)

            if (token == null) {
                _meState.value = MeState.Error("Token no encontrado")
                return@launch
            }

            try {
                // Hacer la solicitud GET a "me", pasando el token en el encabezado Authorization
                val response = apiService.getMe("Bearer $token")
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {
                        _meState.value = MeState.Success(user)
                    } else {
                        _meState.value = MeState.Error("Error en la respuesta del servidor")
                    }
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Error desconocido"
                    _meState.value = MeState.Error(errorMessage)
                }
            } catch (e: Exception) {
                Log.e("MeViewModel", "Error de conexión: ${e.message}")
                _meState.value = MeState.Error("Error de conexión")
            }
        }
    }
}

sealed class MeState {
    object Loading : MeState()
    data class Success(val user: UserModel) : MeState()
    data class Error(val message: String) : MeState()
}
