package com.example.justicelawmovil.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.justicelawmovil.navigation.NavigationItem

@Composable
fun OlvidoContrasena(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Back Arrow
        Icon(
            painter = painterResource(id = android.R.drawable.ic_media_previous), // Cambia esto a tu ícono de flecha hacia atrás
            contentDescription = "Back",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navController.popBackStack() // Navega hacia atrás en la pila de navegación
                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = "¿Olvidaste tu contraseña?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1D2A44), // Color similar al de la imagen
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        // Subtitle
        Text(
            text = "Recibir código de verificación por:",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )

        // Email Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(48.dp)
                .background(
                    color = Color(0xFFF1F1F1),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    navController.navigate(NavigationItem.OlvidoContrasenaEmail.route)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Email",
                color = Color.DarkGray,
                fontSize = 16.sp
            )
        }

        // Phone Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(48.dp)
                .background(
                    color = Color(0xFFF1F1F1),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    navController.navigate(NavigationItem.OlvidoContrasenaTelefn.route)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Número de teléfono",
                color = Color.DarkGray,
                fontSize = 16.sp
            )
        }
    }
}
