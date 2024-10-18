package com.example.justicelawmovil.data

import com.example.justicelawmovil.model.UsersDetailModel
import com.example.justicelawmovil.model.UsersListModel

class UsuarioRepository {
    suspend fun obtenerUsuarios(): List<UsersListModel> {
        return ApiClient.apiService.obtenerUsuarios()
    }

    suspend fun obtenerUsuarioPorId(usuarioId: Int): UsersDetailModel {
        return ApiClient.apiService.obtenerUsuarioPorId(usuarioId)
    }
}