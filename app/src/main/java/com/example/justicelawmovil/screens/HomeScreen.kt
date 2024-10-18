@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.justicelawmovil.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import com.example.justicelawmovil.R

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

    val drawerContent = @androidx.compose.runtime.Composable {
        Column(modifier = Modifier.fillMaxSize()) {
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
                    title = { Text(text = "JusticeLaw") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
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
                    containerColor = Color(0xFF001C36),
                    contentColor = Color.White
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
                        IconButton(onClick = { /* Navegar a Profile */ }) {
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
                ) {
                    Text(
                        text = "Preguntas recientes",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 16.sp,
                            color = Color(0xFF001C36)
                        ),
                        modifier = Modifier.padding(16.dp)
                    )
                    // Puedes agregar más contenido aquí según lo necesites
                }
            }
        )
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
        Text(text = label, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
