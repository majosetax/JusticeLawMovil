package com.example.justicelawmovil.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.justicelawmovil.navigation.NavigationItem
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

import com.example.justicelawmovil.R

@Composable
fun HomeScreen(navController: NavController) {
    val homeIcon: Painter = painterResource(id = R.drawable.home)
    val searchIcon: Painter = painterResource(id = R.drawable.search)
    val profileIcon: Painter = painterResource(id = R.drawable.forum)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

    ) {

        Text(
            modifier = Modifier.padding(top = 40.dp, bottom = 16.dp),
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
            text = "¡Bienvenidos a JusticeLaw!",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 20.sp,
                color = Color(0xFF001C36)

            ),
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        )

        Text(
            text = "Tu confianza es nuestra prioridad, y trabajamos incansablemente para obtener los mejores resultados posibles en cada caso. Explora nuestros servicios y descubre cómo podemos ayudarte a navegar por el complejo mundo legal con confianza y tranquilidad.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                color = Color(0xFF001C36)
            ),
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        )

        Image(painter = painterResource(id = R.drawable.img_main), contentDescription = "home",

        )

        Text(
            text = "En JusticeLaw, estamos dedicados a proporcionar soluciones legales de alta calidad, combinando experiencia y compromiso para defender tus derechos. Nos especializamos en brindar asesoría y representación en una amplia gama de áreas legales, asegurando que cada cliente reciba la atención personalizada que merece.",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                color = Color(0xFF001C36)
            ),
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        )


    }


    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {

        BottomAppBar(
            modifier = Modifier
                .padding(16.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(25.dp))
                .align(Alignment.BottomCenter),
            containerColor =  Color(0xFF001C36),
            contentColor = Color(0xFFFFFFFF),


        ) {

            Row(

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()


            )

            {




                IconButton(onClick = { /* TODO: Handle home button click */ }) {

                    Icon(
                        painter = homeIcon,
                        contentDescription = "Home",

                        )
                }

               // Spacer(modifier = Modifier.height(50.dp))
                IconButton(onClick = { /* TODO: Handle search button click */ }) {
                    Icon(
                        painter = searchIcon,
                        contentDescription = "Search"
                    )
                }
                IconButton(onClick = { /* TODO: Handle profile button click */ }) {
                    Icon(
                        painter = profileIcon,
                        contentDescription = "Profile"
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
