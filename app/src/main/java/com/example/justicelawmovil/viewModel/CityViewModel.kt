package com.example.justicelawmovil.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justicelawmovil.data.ApiClient
import com.example.justicelawmovil.model.CityModel
import com.example.justicelawmovil.model.StateModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CityViewModel() : ViewModel() {

    private val apiService = ApiClient.userApiService

    private val _cities = MutableStateFlow<List<CityModel>>(emptyList())
    val cities: StateFlow<List<CityModel>> = _cities

    private val _selectedCity = MutableStateFlow("Seleccionar ciudad")
    val selectedCity: StateFlow<String> = _selectedCity

    fun fetchCitiesByState(stateId: Int) {
        viewModelScope.launch {
            try {
                val response = apiService.getCitiesByState(stateId)
                _cities.value = response
            } catch (e: Exception) {
                // Maneja errores según sea necesario
                _cities.value = emptyList()
            }
        }
    }

    fun selectCity(cityName: String) {
        _selectedCity.value = cityName
    }
}