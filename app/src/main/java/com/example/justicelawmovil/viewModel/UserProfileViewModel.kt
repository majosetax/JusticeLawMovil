package com.example.justicelawmovil.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justicelawmovil.data.ApiClient
import com.example.justicelawmovil.model.UserModel
import com.example.justicelawmovil.model.UserProfileModel
import kotlinx.coroutines.launch


class UserProfileViewModel : ViewModel() {

    private val _userProfileState = MutableLiveData<UserProfileState>()
    val userProfileState: LiveData<UserProfileState> = _userProfileState

    private val apiService = ApiClient.userApiService

    fun getProfile(context: Context) {
        viewModelScope.launch {
            _userProfileState.value = UserProfileState.Loading

            val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val token = sharedPreferences.getString("token", null)

            if (token == null) {
                _userProfileState.value = UserProfileState.Error("Token no encontrado")
                return@launch
            }

            try {
                val response = apiService.getProfile("Bearer $token")
                if (response.isSuccessful) {
                    val profile = response.body()
                    if (profile != null) {
                        _userProfileState.value = UserProfileState.Success(profile)
                    } else {
                        _userProfileState.value = UserProfileState.Error("Error en la respuesta del servidor")
                    }
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Error desconocido"
                    _userProfileState.value = UserProfileState.Error(errorMessage)
                }
            } catch (e: Exception) {
                Log.e("UserProfileViewModel", "Error de conexión: ${e.message}")
                _userProfileState.value = UserProfileState.Error("Error de conexión")
            }
        }
    }

    fun updateUserProfile(context: Context, userProfileModel: UserProfileModel) {
        viewModelScope.launch {
            _userProfileState.value = UserProfileState.Loading

            val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val token = sharedPreferences.getString("token", null)

            if (token == null) {
                _userProfileState.value = UserProfileState.Error("Token no encontrado")
                return@launch
            }

            try {
                val response = apiService.updateUserProfile("Bearer $token", userProfileModel)
                if (response.isSuccessful) {
                    val profile = response.body()
                    if (profile != null) {
                        _userProfileState.value = UserProfileState.Success(profile)
                    } else {
                        _userProfileState.value = UserProfileState.Error("Error al actualizar perfil")
                    }
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Error desconocido"
                    _userProfileState.value = UserProfileState.Error(errorMessage)
                }
            } catch (e: Exception) {
                Log.e("UserProfileViewModel", "Error de conexión: ${e.message}")
                _userProfileState.value = UserProfileState.Error("Error de conexión")
            }
        }
    }

}



sealed class UserProfileState {
    object Loading : UserProfileState()
    data class Success(val user: UserProfileModel) : UserProfileState()
    data class Error(val message: String) : UserProfileState()
}
