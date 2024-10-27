package com.example.justicelawmovil.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.justicelawmovil.model.UserModel
import com.example.justicelawmovil.viewModel.UserViewModel
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController

@Composable
fun UserListScreen(navController: NavController, userViewModel: UserViewModel = viewModel()) {
    val users = userViewModel.users.collectAsState()
    val error = userViewModel.error.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        if (error.value != null) {
            Text("Error: ${error.value}")
        } else if (users.value.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(users.value) { user ->
                    UserItem(user)
                }
            }
        }
    }
}

@Composable
fun UserItem(user: UserModel) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text(text = "ID: ${user.id}")
        Text(text = "Name: ${user.name}")
        Text(text = "Last Name: ${user.last_name}")
        Text(text = "Email: ${user.email}")
        Text(text = "Created At: ${user.created_at}")
    }
}