package com.example.justicelawmovil.service

import com.example.justicelawmovil.model.CityModel
import com.example.justicelawmovil.model.CountryModel
import com.example.justicelawmovil.model.StateModel
import com.example.justicelawmovil.model.TypeDocumentModel
import com.example.justicelawmovil.model.UserModel
import com.example.justicelawmovil.model.UserProfileModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


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

    @POST("auth/me")
    suspend fun getMe(@Header("Authorization") token: String): Response<UserModel>

    @GET("countries")
    suspend fun getCountries(): List<CountryModel>

    @GET("states/{countryId}")
    suspend fun getStatesByCountry(@Path("countryId") countryId: Int): List<StateModel>

    @GET("cities/{stateId}")
    suspend fun getCitiesByState(@Path("stateId") stateId: Int): List<CityModel>

    @GET("getprofile")
    suspend fun getProfile(@Header("Authorization") token: String): Response<UserProfileModel>

    @POST("profile") suspend fun updateUserProfile (@Header("Authorization") token: String,
        @Body userProfileModel: UserProfileModel
    ): Response<UserProfileModel>

}