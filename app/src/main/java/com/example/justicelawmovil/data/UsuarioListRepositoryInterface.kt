package com.example.justicelawmovil.data

import android.util.Log
import androidx.compose.ui.geometry.Offset
import com.example.justicelawmovil.model.UsersListModel
import com.example.justicelawmovil.service.ApiService
import com.example.justicelawmovil.service.getRetrofitClient
import retrofit2.Response

interface UsuarioListRepositoryInterface {
    suspend fun obtenerUsuarios (offset: Int, limit: Int) : Response<UsersListModel>
}

class UsuarioListRepository(
    private val apiService: ApiService = getRetrofitClient()
): UsuarioListRepositoryInterface{

    override suspend fun obtenerUsuarios(offset: Int, limit: Int): Response<UsersListModel> {
        Log.d("Repositorio Usuarios List","$offset, $limit")
        return apiService.obtenerUsuarios(offset = offset, limit = limit)
    }
}
