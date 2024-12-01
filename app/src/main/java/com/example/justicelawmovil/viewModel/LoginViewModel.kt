package com.example.justicelawmovil.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justicelawmovil.data.ApiClient
import com.example.justicelawmovil.model.UserModel
import com.example.justicelawmovil.service.LoginRequest
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    private val apiService = ApiClient.userApiService

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            try {
                val response = apiService.login(LoginRequest(email, password))
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    loginResponse?.let {
                        val token = it.access_token
                        val role = it.role
                        val expiresIn = it.expires_in
                        val user = it.user
                        // Aquí puedes almacenar todos los valores que quieras
                        _loginState.value = LoginState.Success(token, role, expiresIn, user)
                    }
                } else {
                    _loginState.value = LoginState.Error("Credenciales incorrectas")
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Error de conexión")
            }
        }
    }
}

sealed class LoginState {
    object Loading : LoginState()
    data class Success(
        val token: String?,
        val role: String?,
        val expiresIn: Int,
        val user: UserModel?
    ) : LoginState()

    data class Error(val message: String) : LoginState()
}
