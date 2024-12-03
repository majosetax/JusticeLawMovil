package com.example.justicelawmovil.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justicelawmovil.data.ApiClient
import com.example.justicelawmovil.model.TypeDocumentModel
import com.example.justicelawmovil.service.RegisterLawyerRequest
import com.example.justicelawmovil.service.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class RegisterLawyerState {
    object Idle : RegisterLawyerState()
    object Loading : RegisterLawyerState()
    data class Success(val message: String) : RegisterLawyerState()
    data class Error(val error: String) : RegisterLawyerState()
}

class RegisterLawyerViewModel : ViewModel() {
    private val _registerStateLawyer = MutableStateFlow<RegisterLawyerState>(RegisterLawyerState.Idle)
    val registerLawyerState: StateFlow<RegisterLawyerState> = _registerStateLawyer.asStateFlow()

    private val _typeDocuments = MutableStateFlow<List<TypeDocumentModel>>(emptyList())
    val typeDocuments: StateFlow<List<TypeDocumentModel>> = _typeDocuments

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        getTypeDocuments()
    }

    private fun getTypeDocuments() {
        viewModelScope.launch {
            try {
                val response = ApiClient.userApiService.getTypeDocuments()
                _typeDocuments.value = response
                Log.d("TypeDocuments", "Datos obtenidos tipo documentos: $response")
            } catch (e: Exception) {
                Log.e("TypeDocuments", "Error al obtener los datos", e)
                _error.value = "Error fetching tipo documentos: ${e.message}"
            }
        }
    }


    fun registerLawyer(request: RegisterLawyerRequest) {
        _registerStateLawyer.value = RegisterLawyerState.Loading
        viewModelScope.launch {
            try {
                val response = ApiClient.userApiService.registerLawyer(request)
                if (response.isSuccessful) {
                    _registerStateLawyer.value = RegisterLawyerState.Success("Registro exitoso")
                } else {
                    _registerStateLawyer.value = RegisterLawyerState.Error("Error: ${response.errorBody()?.string()}")
                }
            } catch (e: HttpException) {
                _registerStateLawyer.value = RegisterLawyerState.Error("Error de servidor: ${e.message}")
            } catch (e: IOException) {
                _registerStateLawyer.value = RegisterLawyerState.Error("Error de red: ${e.message}")
            }
        }
    }
}