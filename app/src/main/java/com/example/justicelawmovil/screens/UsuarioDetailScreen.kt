package com.example.justicelawmovil.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.justicelawmovil.data.UsuarioRepository
import com.example.justicelawmovil.model.UsersDetailModel

@Composable
fun UsuarioDetailScreen(usuarioId: Int) {
    val usuarioRepository = UsuarioRepository()
    var usuario by remember { mutableStateOf<UsersDetailModel?>(null) }
    var mensajeError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(usuarioId) {
        // Lógica para obtener el usuario por ID
    }

    if (usuario != null) {
        Text("Usuario: ${usuario!!.nombre} ${usuario!!.apellido}, Correo: ${usuario!!.correo}")
    } else if (mensajeError != null) {
        Text("Error: $mensajeError")
    } else {
        Text("Cargando...")
    }
}