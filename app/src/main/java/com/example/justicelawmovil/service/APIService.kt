package com.example.justicelawmovil.service

import androidx.compose.ui.geometry.Offset
import com.example.justicelawmovil.model.UsersDetailModel
import com.example.justicelawmovil.model.UsersListModel
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun obtenerUsuarios(
    @Query("offset") offset: Int,
    @Query("limit") limit:Int
    ): Response<UsersListModel>

    @GET("users/{name}")
    suspend fun obtenerUsuarioPorId(@Path("name") name: String): Response<UsersDetailModel>
}

fun getRetrofitClient(): ApiService {

    val client = Retrofit.Builder()
        .baseUrl("http://api.justicelaw.test/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()
    return client.create(ApiService::class.java)
}
