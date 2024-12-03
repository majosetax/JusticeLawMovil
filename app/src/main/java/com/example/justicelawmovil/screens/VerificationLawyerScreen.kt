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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.justicelawmovil.viewModel.CityViewModel
import com.example.justicelawmovil.viewModel.CountryViewModel
import com.example.justicelawmovil.viewModel.StateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationLawyerScreen(navController: NavController,
                             viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
                                     countryViewModel: CountryViewModel = viewModel(),
                             stateViewModel: StateViewModel = viewModel(),
                             cityViewModel: CityViewModel = viewModel())
{

    val state by viewModel.registerState.collectAsState()
    val typeDocuments by viewModel.typeDocuments.collectAsState()

    val countries by countryViewModel.countries.observeAsState(emptyList())

    var selectedCountry by remember { mutableStateOf("Seleccionar país") }

    val states by stateViewModel.states.collectAsState()
    var selectedState by remember { mutableStateOf("Seleccionar estado") }

    val cities by cityViewModel.cities.collectAsState()
    var selectedCity by remember { mutableStateOf("Seleccionar ciudad") }

    var cell_phone by remember { mutableStateOf("") }


    var level by remember { mutableStateOf("") }

    var training_place by remember { mutableStateOf("") }


    var lastName by remember { mutableStateOf("") }
    var selectedTypeDocument by remember { mutableStateOf<TypeDocumentModel?>(null) }
    var documentNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }

    var stateDropdownExpanded by remember { mutableStateOf(false) }

    var cityDropdownExpanded by remember { mutableStateOf(false) }

    var showSuccessMessage by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {

        countryViewModel.fetchCountries()


    }


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
                        append("Verificación Abogado")
                    }
                },
                fontSize = 24.sp // Puedes ajustar el tamaño del texto según tu necesidad
            )

            Text(
                text = "Ingresa tus datos para poder aprobar tu cuenta.",
                color = Color(0xFF001C36), // El mismo color
                fontSize = 14.sp // Texto más pequeño
            )

            Spacer(modifier = Modifier.height(16.dp))



            OutlinedTextField(
                value = cell_phone,
                onValueChange = { cell_phone = it },
                label = { Text("Numero de Telefono") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            androidx.compose.material3.ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedCountry,
                    onValueChange = { },
                    label = { Text("Seleccione el país") },
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                        .background(Color.White)
                        .clickable {
                            expanded = !expanded
                        }
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    countries.forEach { country ->
                        DropdownMenuItem(
                            onClick = {
                                selectedCountry = country.name
                                countryViewModel.selectCountry(country.name)
                                stateViewModel.fetchStatesByCountry(country.id)
                                expanded = false
                            },
                            modifier = Modifier.background(Color.White)
                        ) {
                            androidx.compose.material.Text(text = country.name)
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            androidx.compose.material3.ExposedDropdownMenuBox(
                expanded = stateDropdownExpanded,
                onExpandedChange = { stateDropdownExpanded = !stateDropdownExpanded }
            ) {
                OutlinedTextField(
                    value = selectedState,
                    onValueChange = { },
                    label = { Text("Seleccione el estado") },
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = stateDropdownExpanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                        .background(Color.White)
                        .clickable { stateDropdownExpanded = !stateDropdownExpanded }
                )
                ExposedDropdownMenu(
                    expanded = stateDropdownExpanded,
                    onDismissRequest = { stateDropdownExpanded = false }
                ) {
                    states.forEach { state ->
                        DropdownMenuItem(
                            onClick = {
                                selectedState = state.name
                                stateViewModel.selectState(state.name)
                                cityViewModel.fetchCitiesByState(state.id)
                                stateDropdownExpanded = false
                            },
                            modifier = Modifier.background(Color.White)
                        ) {
                            androidx.compose.material.Text(text = state.name)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            androidx.compose.material3.ExposedDropdownMenuBox(
                expanded = cityDropdownExpanded,
                onExpandedChange = { cityDropdownExpanded = !cityDropdownExpanded }
            ) {
                OutlinedTextField(
                    value = selectedCity,
                    onValueChange = { },
                    label = { Text("Seleccione la ciudad") },
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = cityDropdownExpanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                        .background(Color.White)
                        .clickable { cityDropdownExpanded = !cityDropdownExpanded }
                )
                ExposedDropdownMenu(
                    expanded = cityDropdownExpanded,
                    onDismissRequest = { cityDropdownExpanded = false }
                ) {
                    cities.forEach { city ->
                        DropdownMenuItem(
                            onClick = {
                                selectedCity = city.name
                                cityViewModel.selectCity(city.name)
                                cityDropdownExpanded = false
                            },
                            modifier = Modifier.background(Color.White)
                        ) {
                            androidx.compose.material.Text(text = city.name)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = level,
                onValueChange = { level = it },
                label = { Text("Nivel Educativo") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = training_place,
                onValueChange = { training_place = it },
                label = { Text("Lugar de formación") }
            )
            Spacer(modifier = Modifier.height(16.dp))



            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Apellidos") }
            )
            Spacer(modifier = Modifier.height(16.dp))



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
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (selectedTypeDocument != null) {
                        Log.d("Register", "Registering user with document type ID: ${selectedTypeDocument!!.id}")
                        viewModel.registerUser(
                            RegisterRequest(
                                name = cell_phone,
                                last_name = lastName,
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

            Text(
                text = "¿Quieres trabajar con nosotros?",
                style = TextStyle(
                    color = Color(0xFFCF9E3E),
                    fontSize = 18.sp
                ),
                modifier = androidx.compose.ui.Modifier
                    .clickable {

                        navController.navigate(NavigationItem.RegisterLawyer.route)
                    }
            )

            Spacer(modifier = Modifier.height(16.dp))

            when (val registerState = state) {
                is RegisterState.Loading -> Text("Cargando...")
                is RegisterState.Success -> {
                    showSuccessMessage = true
                    LaunchedEffect(Unit) {
                        kotlinx.coroutines.delay(2000) // Esperar 2 segundos
                        showSuccessMessage = false
                        navController.navigate(NavigationItem.Login.route) // Navegar al login después de mostrar el mensaje
                    }
                }
                is RegisterState.Error -> Text(registerState.error, color = Color.Red)
                else -> {}
            }

            if (showSuccessMessage) {
                Text("Registro exitoso", color = Color.Green)
            }
        }
    }
}

