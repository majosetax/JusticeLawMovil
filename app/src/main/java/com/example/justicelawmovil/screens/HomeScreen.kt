@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.justicelawmovil.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import com.example.justicelawmovil.R
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import com.example.justicelawmovil.navigation.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val helpIcon: Painter = painterResource(id = R.drawable.help) // Icono personalizado de ayuda
    val homeIcon: Painter = painterResource(id = R.drawable.home) // Icono personalizado para Home
    val searchIcon: Painter = painterResource(id = R.drawable.search) // Icono personalizado para Search
    val profileIcon: Painter = painterResource(id = R.drawable.forum) // Icono personalizado para Profile
    val notificationIcon: Painter = painterResource(id = R.drawable.notifications)
    val settingsIcon: Painter = painterResource(id = R.drawable.settings)
    val menuIcon: Painter = painterResource(id = R.drawable.menu)


    val drawerContent = @Composable {
        Column( modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF003049))
        ){
            DrawerHeader()
            DrawerMenuItem(icon = homeIcon, label = "Home")
            DrawerMenuItem(icon = profileIcon, label = "Perfil")
            DrawerMenuItem(icon = notificationIcon, label = "Notificaciones")
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            DrawerMenuItem(icon = settingsIcon, label = "Configuración")
            DrawerMenuItem(icon = helpIcon, label = "Ayuda") // Usando el ícono personalizado
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = drawerContent
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Box(modifier = Modifier)},
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                        ) {
                            Icon(painter = menuIcon, contentDescription = "Menu") // Usando tu ícono personalizado
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier
                        .padding(16.dp)
                        .height(60.dp)
                        .clip(RoundedCornerShape(25.dp)),
                    containerColor = Color(0xFFffffff),
                    contentColor = Color.White,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        IconButton(onClick = { /* Navegar a Home */ }) {
                            Icon(painter = homeIcon, contentDescription = "Home")
                        }
                        IconButton(onClick = { /* Navegar a Search */ }) {
                            Icon(painter = searchIcon, contentDescription = "Search")
                        }
                        IconButton(onClick = { navController.navigate(NavigationItem.Profile.route) }) {
                            Icon(painter = profileIcon, contentDescription = "Profile")
                        }


                    }
                }
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(24.dp)
                ) {
                    // Título y descripción
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

                    // Carrusel de imágenes
                    MyCarousel()

                    Text(
                        text = "En JusticeLaw, estamos dedicados a proporcionar soluciones legales de alta calidad, combinando experiencia y compromiso para defender tus derechos. Nos especializamos en brindar asesoría y representación en una amplia gama de áreas legales, asegurando que cada cliente reciba la atención personalizada que merece.",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 16.sp,
                            color = Color(0xFF001C36)
                        ),
                        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                    )
                }
            }
        )
    }
}

@Composable
fun MyCarousel() {
    val carouselItems = listOf(R.drawable.img_main, R.drawable.img_main_dos) // Asegúrate de que estas imágenes existan
    LazyRow(modifier = Modifier.padding(vertical = 16.dp)) {
        items(carouselItems) { image ->
            Box(modifier = Modifier
                .size(300.dp)
                .padding(horizontal = 8.dp)) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun DrawerHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile), // Reemplaza con tu imagen de usuario
            contentDescription = "Avatar",
            modifier = Modifier.size(72.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text("Alfonso Juan", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text("Ver perfil", fontSize = 14.sp, color = Color.Gray)
    }
}

@Composable
fun DrawerMenuItem(icon: Painter, label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = icon, contentDescription = null, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = label, fontSize = 16.sp, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}