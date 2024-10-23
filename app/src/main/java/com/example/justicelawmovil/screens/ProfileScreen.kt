package com.example.justicelawmovil.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.justicelawmovil.R

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Icon(painter = painterResource(id = R.drawable.profile), contentDescription = "Icono")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Mi perfil", fontSize = 20.sp)
        }

        // Entradas de texto
        repeat(5) { index ->
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Label ${index + 1}")
                BasicTextField(
                    value = remember { TextFieldValue("") },
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }
        }

        // Icono de editar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(id = R.drawable.edit), contentDescription = "Editar")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Editar información", fontSize = 16.sp)
        }

        // Botón de guardar
        Button(
            onClick = { /* Acción de guardar */ },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Guardar")
        }
    }
}