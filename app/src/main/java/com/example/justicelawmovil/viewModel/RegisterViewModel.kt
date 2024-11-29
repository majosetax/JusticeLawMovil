package com.example.justicelawmovil.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justicelawmovil.data.ApiClient
import com.example.justicelawmovil.model.TypeDocumentModel
import com.example.justicelawmovil.service.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val message: String) : RegisterState()
    data class Error(val error: String) : RegisterState()
}

class RegisterViewModel : ViewModel() {
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    private val _typeDocuments = MutableStateFlow<List<TypeDocumentModel>>(emptyList())
    val typeDocuments: StateFlow<List<TypeDocumentModel>> = _typeDocuments.asStateFlow()

    init {
        getTypeDocuments()
    }

    private fun getTypeDocuments() {
        viewModelScope.launch {
            try {
                val documents = ApiClient.userApiService.getTypeDocuments()
                _typeDocuments.value = documents
                Log.d("TypeDocuments", "Datos obtenidos: $documents")
            } catch (e: Exception) {
                Log.e("TypeDocuments", "Error al obtener los datos", e)
                _typeDocuments.value = emptyList()
            }
        }
    }


    fun registerUser(request: RegisterRequest) {
        _registerState.value = RegisterState.Loading
        viewModelScope.launch {
            try {
                val response = ApiClient.userApiService.register(request)
                if (response.isSuccessful) {
                    _registerState.value = RegisterState.Success("Registro exitoso")
                } else {
                    _registerState.value = RegisterState.Error("Error: ${response.errorBody()?.string()}")
                }
            } catch (e: HttpException) {
                _registerState.value = RegisterState.Error("Error de servidor: ${e.message}")
            } catch (e: IOException) {
                _registerState.value = RegisterState.Error("Error de red: ${e.message}")
            }
        }
    }
}
