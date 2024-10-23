package com.example.justicelawmovil.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.justicelawmovil.R
import com.example.justicelawmovil.navigation.NavigationItem

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 70.dp), // Margen superior añadido
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen centrada
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Perfil",
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 16.dp)
        )

        // Icono y texto
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth() // Hace que el Row ocupe todo el ancho
                .padding(bottom = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Icono",
                modifier = Modifier.size(24.dp) // Tamaño del icono
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Mi perfil", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { androidx.compose.material3.Text("Nombre Completo") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { androidx.compose.material3.Text("E-mail") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { androidx.compose.material3.Text("Numero telefónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { androidx.compose.material3.Text("Género") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { androidx.compose.material3.Text("Fecha de nacimiento") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        androidx.compose.material3.Button(
            onClick = {
                navController.navigate(
                    NavigationItem.Profile.route
                )
            },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(Color(0xFF001C36))
        ) {
            androidx.compose.material3.Text("Guardar", color = Color.White)
        }
    }
}
