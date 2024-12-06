package com.example.justicelawmovil.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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

import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.input.VisualTransformation
import com.example.justicelawmovil.service.RegisterLawyerRequest
import com.example.justicelawmovil.viewModel.RegisterLawyerState
import com.example.justicelawmovil.viewModel.RegisterLawyerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterLawyerScreen(navController: NavController, viewModel: RegisterLawyerViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val state by viewModel.registerLawyerState.collectAsState()
    val typeDocuments by viewModel.typeDocuments.collectAsState()

    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var selectedTypeDocument by remember { mutableStateOf<TypeDocumentModel?>(null) }
    var documentNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    var showSuccessMessage by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 50.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color(0xFF001C36), fontWeight = FontWeight.Bold)) {
                        append("Justice")
                    }
                    withStyle(style = SpanStyle(color = Color(0xFFCF9E3E), fontWeight = FontWeight.Bold)) {
                        append("Law")
                    }
                },
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 28.sp,
                )
            )

            Text(
                text = "Leyes claras, justicia real",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp,
                    color = Color(0xFF001C36)
                ),
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )

            Text(
                text = "Únete a la Red de Expertos Jurídicos",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    color = Color(0xFF001C36)
                ),
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Apellidos") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                OutlinedTextField(
                    value = selectedTypeDocument?.let { "${it.code} - ${it.description}" }
                        ?: "Tipo de documento",
                    onValueChange = { },
                    label = { Text("Tipo de documento") },
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .clickable {
                            expanded = !expanded
                            Log.d("Dropdown", "Dropdown clicked. Expanded: $expanded")
                        }
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                        Log.d("Dropdown", "Dropdown dismissed")
                    }
                ) {
                    typeDocuments.forEach { document ->
                        DropdownMenuItem(onClick = {
                            selectedTypeDocument = document
                            expanded = false
                            Log.d("Dropdown", "Document selected: ${document.code} - ${document.description}")
                        },
                            modifier = Modifier.background(Color.White)
                        ) {
                            Text(text = "${document.code} - ${document.description}")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = documentNumber,
                onValueChange = { documentNumber = it },
                label = { Text("Número de documento") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (selectedTypeDocument != null) {
                        Log.d("Register", "Registering user with document type ID: ${selectedTypeDocument!!.id}")
                        viewModel.registerLawyer(
                            RegisterLawyerRequest(
                                name = name,
                                last_names = lastName,
                                type_document_id = selectedTypeDocument!!.id.toString(),
                                document_number = documentNumber,
                                email = email,
                                password = password
                            )
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(Color(0xFF001C36))
            ) {
                Text("Registrarse", color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))

            when (val registerState = state) {
                is RegisterLawyerState.Loading -> Text("Cargando...")
                is RegisterLawyerState.Success -> {
                    showSuccessMessage = true
                    LaunchedEffect(Unit) {
                        kotlinx.coroutines.delay(2000) // Esperar 2 segundos
                        showSuccessMessage = false
                        navController.navigate(NavigationItem.Login.route) // Navegar al login después de mostrar el mensaje
                    }
                }
                is RegisterLawyerState.Error -> Text(registerState.error, color = Color.Red)
                else -> {}
            }

            if (showSuccessMessage) {
                Text("Registro exitoso", color = Color.Green)
            }
        }
    }
}