package com.example.justicelawmovil.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.justicelawmovil.navigation.NavigationItem


@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
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
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(Color(0xFF001C36)),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
            ) {
                Text(text = "Iniciar sesión", color = Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(
                onClick = {
                    navController.navigate( // Navigate to a route in the current NavGraph
                        NavigationItem.Register.route
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
                Text(text = "Registrarse")
            }
        }


        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))


        Text(

            text = "¿Olvidaste tu contraseña?",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color(0xFFCF9E3E),
                fontSize = 14.sp,
                textAlign = TextAlign.End
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .align(Alignment.End)
                .clickable { navController.navigate(NavigationItem.OlvidoContrasena.route) }

        )
        Button(
            onClick = {
                navController.navigate(
                    NavigationItem.Home.route
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(Color(0xFF001C36))
        ) {
            Text("Iniciar Sesión", color = Color.White)
        }

//        Button(
//            onClick = {
//                navController.navigate(
//                    NavigationItem.Profile.route
//                )
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp),
//            shape = RoundedCornerShape(50),
//            colors = ButtonDefaults.buttonColors(Color(0xFF001C36))
//        ) {
//            Text("Perfil", color = Color.White)
//        }




    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}