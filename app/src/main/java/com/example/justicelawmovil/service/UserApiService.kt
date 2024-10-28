package com.example.justicelawmovil.service

import com.example.justicelawmovil.model.UserModel
import retrofit2.http.GET

interface UserApiService {
    @GET("users")
    suspend fun getUsers():ArrayList<UserModel>
}