package com.example.justicelawmovil.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.justicelawmovil.R
import com.example.justicelawmovil.navigation.NavigationItem
import kotlinx.coroutines.launch

@Composable
fun Informacion(navController: NavController){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val menuIcon: Painter = painterResource(id = R.drawable.menu)
    val homeIcon: Painter = painterResource(id = R.drawable.home)
    val searchIcon: Painter = painterResource(id = R.drawable.search)
    val forumIcon: Painter = painterResource(id = R.drawable.forum)
    val profileIcon: Painter = painterResource(id = R.drawable.profile)
    val notificationIcon: Painter = painterResource(id = R.drawable.notifications)
    val settingsIcon: Painter = painterResource(id = R.drawable.settings)
    val quienesSomos: Painter = painterResource(id = R.drawable.quienes_somos)
    val historialIcon: Painter = painterResource(id = R.drawable.historial)
    val helpIcon: Painter = painterResource(id = R.drawable.help)

    @Composable
    fun DrawerContent(navController: NavController) {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        Column(
            modifier = Modifier
                .width(screenWidth / 1.5f)
                .fillMaxHeight()
                .background(Color(0xFF003049))
        ) {
            DrawerHeader()
            DrawerMenuItem(icon = homeIcon, label = "Home") {
                navController.navigate(NavigationItem.Home.route)
            }
            DrawerMenuItem(icon = profileIcon, label = "Perfil") {
                navController.navigate(NavigationItem.Profile.route)
            }
            DrawerMenuItem(icon = notificationIcon, label = "Notificaciones") {
                navController.navigate(NavigationItem.Profile.route)
            }
            DrawerMenuItem(icon = historialIcon, label = "Historial") {
                navController.navigate(NavigationItem.Historial.route)
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            DrawerMenuItem(icon = settingsIcon, label = "Configuración") {
                navController.navigate(NavigationItem.Configuracion.route)
            }
            DrawerMenuItem(icon = quienesSomos, label = "Quienes Somos") {
                navController.navigate(NavigationItem.Configuracion.route)
            }
            DrawerMenuItem(icon = helpIcon, label = "Ayuda") {
                navController.navigate(NavigationItem.Profile.route)
            }
        }
    }
    @Composable
    fun DrawerHeader() {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        Column(
            modifier = Modifier
                .width(screenWidth / 1.5f)
                .padding(top = 20.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Avatar",
                modifier = Modifier.size(72.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text("Alfonso Juan", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
            Text("Ver perfil", fontSize = 14.sp, color = Color.White)
        }
    }

    @Composable
    fun DrawerMenuItem(icon: Painter, label: String, onClick: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            androidx.compose.material3.Icon(painter = icon, contentDescription = null, modifier = Modifier.size(24.dp), tint = Color.White)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = label, fontSize = 16.sp, color = Color.White)
        }
    }

    ModalNavigationDrawer(
        drawerState=drawerState,
        drawerContent = { DrawerContent(navController) }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Información") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            androidx.compose.material3.Icon(painter = menuIcon, contentDescription = "Menu", modifier = Modifier.size(24.dp))
                        }
                    },
                    backgroundColor = Color.White,
                    modifier = Modifier.height(90.dp),
                    elevation = 0.dp
                )
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier
                        .padding(16.dp)
                        .height(60.dp)
                        .clip(RoundedCornerShape(25.dp)),
                    containerColor = Color(0xFF003049),
                    contentColor = Color.White
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        IconButton(onClick = { navController.navigate(NavigationItem.Home.route) }) {
                            androidx.compose.material3.Icon(painter = homeIcon, contentDescription = "Home", tint = Color.White)
                        }
                        IconButton(onClick = { /* Acción de búsqueda */ }) {
                            androidx.compose.material3.Icon(painter = searchIcon, contentDescription = "Search", tint = Color.White)
                        }
                        IconButton(onClick = { /* Acción del foro */ }) {
                            androidx.compose.material3.Icon(painter = forumIcon, contentDescription = "Foro", tint = Color.White)
                        }
                    }
                }
            },
            content = { innerPadding ->
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(20.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    TopBar()  // Barra superior de búsqueda y filtros

                    // Lista de artículos
                    val articles = listOf(
                        Article(R.drawable.accidente, "Guía completa sobre los pasos a seguir", "Entra y descubre la información necesaria...", "12 Sept, 2023"),
                        Article(R.drawable.consumidor, "Los derechos del consumidor", "Conoce tus derechos como consumidor y aprende cómo...", "11 Sept, 2023"),
                        Article(R.drawable.negocio, "Aspectos legales para iniciar", "Aprende los aspectos legales fundamentales al iniciar un negocio...", "10 Sept, 2023"),
                        Article(R.drawable.despido, "Qué hacer en caso de un despido", "Explora tus opciones legales y los pasos a seguir...", "9 Sept, 2023")
                    )

                    LazyColumn(contentPadding = innerPadding) {
                        items(articles) { article ->
                            ArticleItem(article = article) {
                                // Acción al hacer clic en un artículo, puedes personalizar esto si necesitas navegación adicional
                            }
                            Divider(color = Color.LightGray, thickness = 0.5.dp)
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun TopBar() {
    var searchQuery by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Buscar...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            listOf("Todos", "Comercial", "Laboral", "Familiar", "Penal", "Civil", "Inmobiliario").forEach { category ->
                Text(
                    text = category,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.clickable { /* Acción al hacer clic en la categoría */ }
                )
            }
        }
    }
}

@Composable
fun ArticleItem(article: Article, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = article.imageRes), contentDescription = null, modifier = Modifier.size(56.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = article.title, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = article.description, fontSize = 14.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Text(text = article.date, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

data class Article(val imageRes: Int, val title: String, val description: String, val date: String)