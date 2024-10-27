@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.justicelawmovil.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import com.example.justicelawmovil.navigation.NavigationItem
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val helpIcon: Painter = painterResource(id = R.drawable.help)
    val homeIcon: Painter = painterResource(id = R.drawable.home)
    val searchIcon: Painter = painterResource(id = R.drawable.search)
    val forumIcon: Painter = painterResource(id = R.drawable.forum)
    val quienesSomos: Painter = painterResource(id = R.drawable.quienes_somos)
    val historialIcon: Painter = painterResource(id = R.drawable.historial)
    val profileIcon: Painter = painterResource(id = R.drawable.profile)
    val notificationIcon: Painter = painterResource(id = R.drawable.notifications)
    val settingsIcon: Painter = painterResource(id = R.drawable.settings)
    val menuIcon: Painter = painterResource(id = R.drawable.menu)


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
                .padding(top = 20.dp)
                .padding(16.dp),
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


    @Composable
    fun TopBar(drawerState: DrawerState, menuIcon: Painter, scope: CoroutineScope) {
        androidx.compose.material.TopAppBar(
            title = {
                Box(modifier = Modifier.fillMaxSize())
            },
            navigationIcon = {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth(Alignment.Start)
                        .padding(vertical = 24.dp)
                ) {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }) {
                        androidx.compose.material3.Icon(
                            painter = menuIcon,
                            contentDescription = "Menu",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            },
            backgroundColor = Color.White,
            modifier = Modifier.height(90.dp),
            elevation = 0.dp
        )
    }


    @Composable
    fun BottomBar(navController: NavController, homeIcon: Painter, searchIcon: Painter, forumIcon: Painter) {
        BottomAppBar(
            modifier = Modifier
                .padding(16.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(25.dp)),
            containerColor = Color(0xFF003049),
            contentColor = Color.White,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {
                IconButton(onClick = { navController.navigate(NavigationItem.Home.route) }) {
                    Icon(painter = homeIcon, contentDescription = "Home", tint = Color.White)
                }
                IconButton(onClick = { navController.navigate(NavigationItem.Informacion.route) }) {
                    Icon(painter = searchIcon, contentDescription = "Search", tint = Color.White)
                }
                IconButton(onClick = { /* Acción del botón de foro */ }) {
                    Icon(painter = forumIcon, contentDescription = "Foro", tint = Color.White)
                }
            }
        }
    }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent(navController) }
    ) {
        Scaffold(

            topBar = { TopBar(drawerState = drawerState, menuIcon = menuIcon, scope = scope) },
            bottomBar = { BottomBar(navController = navController, homeIcon = homeIcon, searchIcon = searchIcon, forumIcon = forumIcon) },

            content = { innerPadding ->

                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(start = 20.dp, end = 20.dp)
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

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}