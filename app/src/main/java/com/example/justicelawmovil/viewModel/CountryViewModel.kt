package com.example.justicelawmovil.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justicelawmovil.data.ApiClient
import com.example.justicelawmovil.model.CountryModel
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val _countries = MutableLiveData<List<CountryModel>>()
    val countries: LiveData<List<CountryModel>> = _countries

    private val _selectedCountry = MutableLiveData<String>()
    val selectedCountry: LiveData<String> = _selectedCountry

    private val apiService = ApiClient.userApiService

    fun fetchCountries() {
        viewModelScope.launch {
            try {
                val response = apiService.getCountries()
                _countries.value = response
            } catch (e: Exception) {
                Log.e("CountryViewModel", "Error fetching countries: ${e.message}")
                _countries.value = emptyList()
            }
        }
    }

    fun selectCountry(country: String) {
        _selectedCountry.value = country
    }
}
