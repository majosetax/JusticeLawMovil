package com.example.justicelawmovil.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.justicelawmovil.navigation.NavigationItem

@Composable
fun RegisterScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 50.dp)
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

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            OutlinedButton(
                onClick = {
                    navController.navigate( // Navigate to a route in the current NavGraph
                        NavigationItem.Login.route
                    )
                    // Route defined in AppNavHost
                },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .weight(1f)
                .height(40.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFF001C36)
            ),
            border = BorderStroke(1.dp, Color(0xFF001C36))
        ) {
            Text(text = "Iniciar Sesión")
        }

            Spacer(modifier = Modifier.width(8.dp))


            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(Color(0xFF001C37)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
            ) {
                Text(text = "Registrarse", color = Color.White)
            }


        }


        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Apellidos") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Tipo de documento") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Numero de documento") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Confirmar contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color(0xFF001C36), fontWeight = FontWeight.Bold)) {
                    append("¿Quieres trabajar con nosotros?")
                }
                withStyle(style = SpanStyle(color = Color(0xFFCF9E3E), fontWeight = FontWeight.Bold)) {
                    append("Registrate como abogado.")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate( // Navigate to a route in the current NavGraph
                NavigationItem.Home.route
            )
            // Route defined in AppNavHost
        },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(Color(0xFF001C36))
        ) {
            Text("Registrarse")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController())
}