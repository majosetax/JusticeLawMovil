package com.example.justicelawmovil.service

import com.example.justicelawmovil.model.TypeDocumentModel
import com.example.justicelawmovil.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
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


interface UserApiService {
    @GET("users")
    suspend fun getUsers():ArrayList<UserModel>

    @GET("typeDocuments")
    suspend fun getTypeDocuments(): List<TypeDocumentModel>

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}