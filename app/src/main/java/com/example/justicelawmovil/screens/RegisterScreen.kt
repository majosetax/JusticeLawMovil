package com.example.justicelawmovil.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.justicelawmovil.model.TypeDocumentModel
import com.example.justicelawmovil.navigation.NavigationItem
import com.example.justicelawmovil.service.RegisterRequest
import com.example.justicelawmovil.viewModel.RegisterState
import com.example.justicelawmovil.viewModel.RegisterViewModel

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val state = viewModel.registerState.collectAsState()
    val typeDocuments by viewModel.typeDocuments.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 50.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 50.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var name by remember { mutableStateOf("") }
            var lastName by remember { mutableStateOf("") }
            var selectedTypeDocument by remember { mutableStateOf<TypeDocumentModel?>(null) }
            var documentNumber by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            // Campos de texto
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") })
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Apellidos") })
            Spacer(modifier = Modifier.height(16.dp))

            // Dropdown para Tipo de Documento
            var expanded by remember { mutableStateOf(false) }
            Box {
                OutlinedTextField(
                    value = selectedTypeDocument?.description ?: "Seleccionar tipo de documento",
                    onValueChange = { },
                    label = { Text("Tipo de documento") },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true } // Asegúrate de que esto abre el menú
                )
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    typeDocuments.forEach { document ->
                        DropdownMenuItem(onClick = {
                            selectedTypeDocument = document
                            expanded = false
                        }) {
                            Text(text = document.description)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = documentNumber,
                onValueChange = { documentNumber = it },
                label = { Text("Número de documento") })
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") })
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Botón de registro
            Button(onClick = {
                if (selectedTypeDocument != null) {
                    viewModel.registerUser(
                        RegisterRequest(
                            name = name,
                            last_name = lastName,
                            type_document_id = selectedTypeDocument!!.id.toString(),
                            document_number = documentNumber,
                            email = email,
                            password = password
                        )
                    )
                }
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Registrarse")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Estado del registro
            when (val registerState = state.value) {
                is RegisterState.Loading -> Text("Cargando...")
                is RegisterState.Success -> {
                    Text(registerState.message, color = Color.Green)
                    navController.navigate(NavigationItem.Home.route)
                }

                is RegisterState.Error -> Text(registerState.error, color = Color.Red)
                else -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController())
}
