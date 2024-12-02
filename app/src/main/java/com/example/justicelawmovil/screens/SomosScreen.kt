package com.example.justicelawmovil.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.justicelawmovil.R

@Composable
fun AboutUsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Fondo claro
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .padding(top = 40.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Sección de Encabezado
        Text(
            text = "¿Quiénes Somos?",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF003049), // Azul oscuro profesional
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Nuestra misión es proteger tus derechos y brindarte asesoría legal con la más alta calidad y profesionalismo.",
            fontSize = 18.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Imagen de Encabezado
        Image(
            painter = painterResource(id = R.drawable.equipo1),
            contentDescription = "Equipo de trabajo",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(vertical = 16.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        // Sección de Descripción
        Text(
            text = "Nuestro Equip0",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF003049),
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        Text(
            text = "En JusticeLaw, contamos con un equipo de abogados altamente capacitados en diversas áreas del derecho. Nos dedicamos a proporcionar soluciones legales efectivas y personalizadas, asegurando que cada cliente reciba el respeto, atención y profesionalismo que merece.",
            fontSize = 16.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Valores y Misión
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ValueCard("Integridad", "Actuamos con ética y transparencia en cada caso.", R.drawable.despido)
            ValueCard("Compromiso", "Nos dedicamos a la defensa de tus derechos.", R.drawable.despido)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ValueCard("Confianza", "Construimos relaciones basadas en la confianza.", R.drawable.despido)
            ValueCard("Excelencia", "Ofrecemos un servicio de calidad superior.", R.drawable.despido)
        }
    }
}

@Composable
fun ValueCard(title: String, description: String, iconResId: Int) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(180.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                modifier = Modifier.size(40.dp),
                tint = Color(0xFF003049)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF003049)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutUsScreenPreview() {
    AboutUsScreen(rememberNavController())
}
