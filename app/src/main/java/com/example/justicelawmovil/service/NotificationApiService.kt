package com.example.justicelawmovil.service

import android.app.Notification
import com.example.justicelawmovil.model.NotificationModel
import com.google.rpc.context.AttributeContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface NotificationApiService {
    @GET("notificaciones") // Cambia esto a la ruta correspondiente en tu API
    suspend fun getNotifications(): List<NotificationModel> // Método para obtener notificaciones
}

// Objeto que gestiona la instancia de Retrofit

object NotificationApiClient {
    private const val BASE_URL = "http://api.justicelaw.test/v1/" // Cambia a tu URL base

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val notificationApiService: NotificationApiService by lazy {
        retrofit.create(NotificationApiService::class.java)
    }
}