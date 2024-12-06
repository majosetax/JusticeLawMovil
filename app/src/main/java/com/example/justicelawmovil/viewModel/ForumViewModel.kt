package com.example.justicelawmovil.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justicelawmovil.model.ForumQuestion
import com.example.justicelawmovil.service.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForumViewModel : ViewModel() {

    // StateFlow para gestionar el estado de las preguntas del foro
    private val _questions = MutableStateFlow<List<ForumQuestion>>(emptyList())
    val questions: StateFlow<List<ForumQuestion>> get() = _questions

    // StateFlow para gestionar el estado de carga y errores
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    // Función para obtener preguntas del foro
    fun fetchForumQuestions() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = RetrofitClient.apiService.getForumQuestions()
                _questions.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar las preguntas: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
