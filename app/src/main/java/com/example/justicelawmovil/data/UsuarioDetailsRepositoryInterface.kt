package com.example.justicelawmovil.data

import com.example.justicelawmovil.model.UsersDetailModel
import com.example.justicelawmovil.service.ApiService
import com.example.justicelawmovil.service.getRetrofitClient
import retrofit2.Response

interface UsuarioDetailsRepositoryInterface {
    suspend fun obtenerUsuarioPorId(name:String): Response<UsersDetailModel>
}

class UsuarioDetailsRepository(
private val apiService: ApiService = getRetrofitClient()
): UsuarioDetailsRepositoryInterface{
    override suspend fun obtenerUsuarioPorId(name: String): Response<UsersDetailModel> {
        return apiService.obtenerUsuarioPorId(name)
    }
}