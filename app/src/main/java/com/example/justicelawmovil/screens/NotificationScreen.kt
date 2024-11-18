package com.example.justicelawmovil.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.justicelawmovil.R
import com.example.justicelawmovil.model.NotificationModel
import com.example.justicelawmovil.navigation.NavigationItem
import com.example.justicelawmovil.viewModel.NotificationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun NotificationsScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Obtén la instancia del NotificationViewModel
    val viewModel: NotificationViewModel = viewModel()

    // Resource icons
    val homeIcon: Painter = painterResource(id = R.drawable.home)
    val searchIcon: Painter = painterResource(id = R.drawable.search)
    val forumIcon: Painter = painterResource(id = R.drawable.forum)
    val helpIcon: Painter = painterResource(id = R.drawable.help)
    val profileIcon: Painter = painterResource(id = R.drawable.profile)
    val notificationIcon: Painter = painterResource(id = R.drawable.notifications)
    val settingsIcon: Painter = painterResource(id = R.drawable.settings)

    val notifications by viewModel.notifications.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    // Mostrar cargando
    if (isLoading) {
        CircularProgressIndicator()
    } else if (error != null) {
        Text(text = error!!)
    } else {
        // Mostrar las notificaciones
        LazyColumn {
            items(notifications) { notification ->
                NotificationItem(notification.title, notification.description)
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent(navController) }
    ) {
        Scaffold(
            topBar = { TopBar(drawerState, scope) },
            bottomBar = { BottomBar(navController, homeIcon, searchIcon, forumIcon) },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Notificaciones",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // LazyColumn for notifications list
                    LazyColumn {
                        items(10) { index ->
                            NotificationItem("Notificación ${index + 1}", "Descripción de la notificación ${index + 1}")
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun DrawerContent(navController: NavController) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(
        modifier = Modifier
            .width(screenWidth / 1.5f)
            .fillMaxHeight()
            .background(Color(0xFF003049))
    ) {
        DrawerHeader() // Suponiendo que tienes un DrawerHeader composable

        DrawerMenuItem(icon = painterResource(id = R.drawable.home), label = "Home") {
            navController.navigate(NavigationItem.Home.route)
        }
        DrawerMenuItem(icon = painterResource(id = R.drawable.profile), label = "Perfil") {
            navController.navigate(NavigationItem.Profile.route)
        }
        DrawerMenuItem(icon = painterResource(id = R.drawable.notifications), label = "Notificaciones") {
            navController.navigate(NavigationItem.Notification.route) // Asegúrate de tener esta ruta definida
        }
        DrawerMenuItem(icon = painterResource(id = R.drawable.settings), label = "Configuración") {
            navController.navigate(NavigationItem.Configuracion.route)
        }
        DrawerMenuItem(icon = painterResource(id = R.drawable.help), label = "Ayuda") {
            // navController.navigate(NavigationItem.Help.route) // Asegúrate de tener esta ruta definida
        }
    }
}

@Composable
fun TopBar(drawerState: DrawerState, scope: CoroutineScope) {
    TopAppBar(
        title = { Text(text = "Justice Law") }, // Título de la App
        navigationIcon = {
            IconButton(onClick = {
                scope.launch { drawerState.open() }
            }) {
                Icon(painter = painterResource(id = R.drawable.menu), contentDescription = "Menu")
            }
        },
        backgroundColor = Color.White,
        elevation = 4.dp
    )
}

@Composable
fun BottomBar(navController: NavController, homeIcon: Painter, searchIcon: Painter, forumIcon: Painter) {
    BottomAppBar(
        containerColor = Color(0xFF003049),
        contentColor = Color.White,
        modifier = Modifier.clip(RoundedCornerShape(25.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()
        ) {
            IconButton(onClick = { navController.navigate(NavigationItem.Home.route) }) {
                Icon(painter = homeIcon, contentDescription = "Home")
            }
            IconButton(onClick = { /* Acción del botón de búsqueda */ }) {
                Icon(painter = searchIcon, contentDescription = "Search")
            }
            IconButton(onClick = { /* Acción del botón del foro */ }) {
                Icon(painter = forumIcon, contentDescription = "Foro")
            }
        }
    }
}

@Composable
fun NotificationItem(title: String, description: String) {
    var isExpanded by remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) } // Estado para el menú desplegable
    var isLiked by remember { mutableStateOf(false) } // Estado para el corazón

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Encabezado con título, corazón y menú
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Título
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Corazón para "Me gusta"
                    IconButton(onClick = { isLiked = !isLiked }) {
                        Icon(
                            imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Me gusta",
                            tint = if (isLiked) MaterialTheme.colorScheme.primary else Color.Gray
                        )
                    }

                    // Menú desplegable con tres puntos
                    Box {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.menu), // Reemplazar con tu ícono de tres puntos
                                contentDescription = "Más opciones"
                            )
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                onClick = { /* Marcar como leído */ },
                                text = { Text("Marcar como leído") }
                            )
                            DropdownMenuItem(
                                onClick = { /* Archivar notificación */ },
                                text = { Text("Archivar notificación") }
                            )
                            DropdownMenuItem(
                                onClick = { /* Eliminar notificación */ },
                                text = { Text("Eliminar notificación") }
                            )
                        }
                    }
                }
            }

            // Descripción principal
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            // Contenido desplegable
            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Información adicional de la notificación.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}