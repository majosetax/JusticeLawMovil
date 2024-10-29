package com.example.justicelawmovil.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
            Icon(painter = icon, contentDescription = null, modifier = Modifier.size(24.dp), tint = Color.White)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = label, fontSize = 16.sp, color = Color.White)
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent(navController) }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Informaciones", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(painter = menuIcon, contentDescription = "Menu", modifier = Modifier.size(24.dp))
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
                            Icon(painter = homeIcon, contentDescription = "Home", tint = Color.White)
                        }
                        IconButton(onClick = { /* Acción de búsqueda */ }) {
                            Icon(painter = searchIcon, contentDescription = "Search", tint = Color.White)
                        }
                        IconButton(onClick = { /* Acción del foro */ }) {
                            Icon(painter = forumIcon, contentDescription = "Foro", tint = Color.White)
                        }
                    }
                }
            },
            content = { innerPadding ->
                Column(modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Color.White)
                ) {
                    SearchBar()
                    Spacer(modifier = Modifier.height(8.dp))
                    CategoriesRow()
                    Spacer(modifier = Modifier.height(8.dp))
                    ArticlesList()
                }
            }
        )
    }
}

@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = { /* Cambia el texto de búsqueda */ },
        placeholder = { Text("Buscar...") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Buscar", tint = Color.Gray) },
        modifier = Modifier
            .width(500.dp)//tamaño
            .height(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(1.dp, Color.Black, RoundedCornerShape(16.dp))
            .padding(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = Color.Black
        )
    )
}

@Composable
fun CategoriesRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val categories = listOf("Todos", "Comercial", "Laboral", "Familiar", "Penal", "Civil", "Inmobiliario")
        categories.forEach { category ->
            Text(
                text = category,
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color.Black
            )
        }
    }
}

data class Article(val title: String, val description: String, val date: String, val imageRes: Int)

@Composable
fun ArticlesList() {
    val articles = listOf(
        Article("Guía completa sobre los pasos a seguir...", "Entra y descubre la información necesaria...", "12 Sept, 2023", R.drawable.accidente),
        Article("Los derechos del consumidor", "Conoce tus derechos como consumidor y aprende cómo...", "11 Sept, 2023", R.drawable.consumidor),
        Article("Aspectos legales para iniciar...", "Aprende los aspectos legales fundamentales al iniciar un negocio...", "11 Sept, 2023", R.drawable.negocio),
        Article("Qué hacer en caso de un despido...", "Explora tus opciones legales y los pasos a seguir...", "10 Sept, 2023", R.drawable.despido),
        Article("Cómo enfrentar problemas de ...","Obtén información sobre tus derechos como inquilino...","11 Sept, 2023",R.drawable.inquilinos),
        Article("Guía completa sobre los pasos a seguir...", "Entra y descubre la información necesaria...", "12 Sept, 2023", R.drawable.accidente),
        Article("Los derechos del consumidor", "Conoce tus derechos como consumidor y aprende cómo...", "11 Sept, 2023", R.drawable.consumidor),
        Article("Aspectos legales para iniciar...", "Aprende los aspectos legales fundamentales al iniciar un negocio...", "11 Sept, 2023", R.drawable.negocio),
        Article("Qué hacer en caso de un despido...", "Explora tus opciones legales y los pasos a seguir...", "10 Sept, 2023", R.drawable.despido),
        Article("Cómo enfrentar problemas de ...","Obtén información sobre tus derechos como inquilino...","11 Sept, 2023",R.drawable.inquilinos)

    )

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(articles) { article ->
            ArticleItem(article)
            Divider(color = Color.Gray, thickness = 1.dp)
        }
    }
}

@Composable
fun ArticleItem(article: Article) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = article.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = article.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.description,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = article.date,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}
