package com.example.justicelawmovil.service

import com.example.justicelawmovil.model.TypeDocumentModel
import com.example.justicelawmovil.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


data class RegisterRequest(
    val name: String,
    val last_name: String,
    val type_document_id: String,
    val document_number: String,
    val email: String,
    val password: String
)

data class RegisterResponse(
    val id: Int, // Ajusta según la respuesta del API
    val name: String,
    val email: String,
    val created_at: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val access_token: String,
    val role: String,
    val expires_in: Int,
    val user: UserModel
)

data class LogoutResponse(
    val message: String
)


interface UserApiService {
    @GET("users")
    suspend fun getUsers():ArrayList<UserModel>

    @GET("typeDocuments")
    suspend fun getTypeDocuments(): ArrayList<TypeDocumentModel>

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("logoutPrueba")
    suspend fun logout(@Header("Authorization") token: String): Response<LogoutResponse>

}