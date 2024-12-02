package com.example.justicelawmovil.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justicelawmovil.data.ApiClient

import kotlinx.coroutines.launch
import retrofit2.Response

class LogoutViewModel : ViewModel() {
    private val _logoutState = MutableLiveData<LogoutState>()
    val logoutState: LiveData<LogoutState> = _logoutState

    private val apiService = ApiClient.userApiService

    fun logoutUser(context: Context) {
        viewModelScope.launch {
            _logoutState.value = LogoutState.Loading

            // Obtener el token guardado en SharedPreferences
            val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val token = sharedPreferences.getString("token", null)

            if (token == null) {
                _logoutState.value = LogoutState.Error("Token no encontrado")
                return@launch
            }

            try {
                // Agregar el token al encabezado Authorization
                val response = apiService.logout("Bearer $token")
                if (response.isSuccessful) {
                    val message = response.body()?.message ?: "Cierre de sesión exitoso"
                    clearSharedPreferences(context)
                    _logoutState.value = LogoutState.Success
                    Log.d("Logout", message)
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Error desconocido"
                    Log.e("LogoutViewModel", "Error en la respuesta de la API: ${response.message()}")
                    _logoutState.value = LogoutState.Error(errorMessage)
                }
            } catch (e: Exception) {
                Log.e("LogoutViewModel", "Error de conexión: ${e.message}")
                _logoutState.value = LogoutState.Error("Error de conexión")
            }
        }
    }

    private fun clearSharedPreferences(context: Context) {
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }
}

sealed class LogoutState {
    object Loading : LogoutState()
    object Success : LogoutState()
    data class Error(val message: String) : LogoutState()
}
