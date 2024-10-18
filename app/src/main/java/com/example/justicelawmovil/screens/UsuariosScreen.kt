package com.example.justicelawmovil.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.justicelawmovil.data.UsuarioRepository
import com.example.justicelawmovil.model.UsersListModel
import com.example.justicelawmovil.navigation.NavigationItem

@Composable
fun UsuariosScreen(
navController: NavController,
viewModel: UsersListViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
){

}