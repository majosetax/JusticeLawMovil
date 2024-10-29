package com.example.justicelawmovil.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.justicelawmovil.R
import com.example.justicelawmovil.navigation.NavigationItem

@Composable
fun ReestablecimientoExitoso(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 36.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Icon(
            painter = painterResource(id = R.drawable.volver), // Cambia esto a tu ícono de flecha hacia atrás
            contentDescription = "Back",
            modifier = Modifier
                .align(Alignment.Start)
                .size(24.dp)
                .clickable {
                    navController.popBackStack()
                }
        )

        Spacer(modifier = Modifier.height(16.dp))



        Text(
            text = "¡Reestablecimiento exitoso!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF001C36)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Tu contraseña ha sido restablecida con éxito. Por favor, inicia sesión con tu nueva contraseña para acceder a tu cuenta.",
            fontSize = 14.sp,
            color = Color(0xFF001C36)
        )

        Spacer(modifier = Modifier.height(24.dp))

        androidx.compose.material3.Button(
            onClick = {
                navController.navigate(
                    NavigationItem.Login.route
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(Color(0xFF001C36))
        ) {
            androidx.compose.material3.Text("Iniciar sesión", color = Color.White)
        }
    }
}