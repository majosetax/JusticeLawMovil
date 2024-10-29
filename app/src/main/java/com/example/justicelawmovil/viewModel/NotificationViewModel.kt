package com.example.justicelawmovil.viewModel

import android.app.Notification
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.justicelawmovil.data.ApiClient
import com.example.justicelawmovil.model.NotificationModel
import com.example.justicelawmovil.service.NotificationApiClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class NotificationViewModel: ViewModel() {
    // Estado para las notificaciones
    private val _notifications = MutableStateFlow<List<NotificationModel>>(emptyList())
    val notifications: StateFlow<List<NotificationModel>> = _notifications

    // Estado para la carga
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Estado para el control de errores
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchNotifications()
    }

    private fun fetchNotifications() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null // Limpiar cualquier error anterior
            try {
                // Obtiene la lista de notificaciones
                val notificationsList = NotificationApiClient.notificationApiService.getNotifications()
                _notifications.value = notificationsList // Asignar la lista a las notificaciones
            } catch (e: Exception) {
                _error.value = "Error fetching notifications: ${e.message}" // Manejo de errores
            } finally {
                _isLoading.value = false // Finalizar el estado de carga
            }
        }
    }
}