package com.example.justicelawmovil.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justicelawmovil.data.ApiClient
import com.example.justicelawmovil.model.StateModel
import com.example.justicelawmovil.service.UserApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StateViewModel() : ViewModel() {

    private val apiService = ApiClient.userApiService

    private val _states = MutableStateFlow<List<StateModel>>(emptyList())
    val states: StateFlow<List<StateModel>> = _states

    private val _selectedState = MutableStateFlow("Seleccionar estado")
    val selectedState: StateFlow<String> = _selectedState

    fun fetchStatesByCountry(countryId: Int) {
        viewModelScope.launch {
            try {
                val response = apiService.getStatesByCountry(countryId)
                _states.value = response
            } catch (e: Exception) {
                // Maneja errores según sea necesario
                _states.value = emptyList()
            }
        }
    }

    fun selectState(stateName: String) {
        _selectedState.value = stateName
    }
}