package com.example.justicelawmovil.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.justicelawmovil.R
import com.example.justicelawmovil.navigation.NavigationItem
import com.example.justicelawmovil.screens.HomeScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun VerPerfilAbogado(navController: NavController) {
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
            androidx.compose.material3.Text(
                "Alfonso Juan",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White
            )
            androidx.compose.material3.Text("Ver perfil", fontSize = 14.sp, color = Color.White)
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
            androidx.compose.material3.Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(16.dp))
            androidx.compose.material3.Text(text = label, fontSize = 16.sp, color = Color.White)
        }
    }


    @Composable
    fun TopBar(drawerState: DrawerState, menuIcon: Painter, scope: CoroutineScope) {
        TopAppBar(
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
    fun BottomBar(
        navController: NavController,
        homeIcon: Painter,
        searchIcon: Painter,
        forumIcon: Painter
    ) {
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
                    androidx.compose.material3.Icon(
                        painter = homeIcon,
                        contentDescription = "Home",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { /* Boton informaciones */ }) {
                    androidx.compose.material3.Icon(
                        painter = searchIcon,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { /* Acción del botón de perfil */ }) {
                    androidx.compose.material3.Icon(
                        painter = forumIcon,
                        contentDescription = "Foro",
                        tint = Color.White
                    )
                }
            }
        }
    }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent(navController) }
    ) {
        Scaffold(

            bottomBar = {
                BottomBar(
                    navController = navController,
                    homeIcon = homeIcon,
                    searchIcon = searchIcon,
                    forumIcon = forumIcon
                )
            },
            content = { innerPadding ->

                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    var selectedSection by remember { mutableStateOf("Sobre Mi") }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        // Imagen de la portada
                        Image(
                            painter = painterResource(id = R.drawable.portada),
                            contentDescription = "Portada",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 0.dp) // Asegúrate de que ocupa todo el espacio incluyendo la barra de estado
                        )

                        // Botón de volver
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.volver),
                                contentDescription = "Volver",
                                modifier = Modifier.size(24.dp),
                                tint = Color.White
                            )
                        }

                        // Foto de perfil fuera del Box de la portada
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.White, CircleShape)
                                .align(Alignment.BottomStart)
                                .offset(x = 16.dp, y = -40.dp) // Ajusta el offset para que quede mitad adentro y mitad afuera
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.profile),
                                contentDescription = "Perfil",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape) // Asegúrate de que la imagen también tenga forma de círculo
                            )
                        }

                        // Información adicional debajo de la foto de perfil
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = 100.dp, bottom = 16.dp)
                        ) {
                            Text(
                                text = "David Astrada",
                                fontSize = 16.sp, // Hacer más pequeño el nombre
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Row {
                                repeat(4) { // Hacer las estrellas más pequeñas
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "Estrella",
                                        tint = Color.Yellow,
                                        modifier = Modifier.size(16.dp) // Tamaño más pequeño
                                    )
                                }
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Estrella vacía",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp) // Tamaño más pequeño
                                )
                            }
                        }
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        val sections = listOf("Sobre Mi", "Hoja de Vida", "Reseñas")
                        sections.forEach { section ->
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        selectedSection = section
                                    }
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = section,
                                    textAlign = TextAlign.Center,
                                    color = if (selectedSection == section) Color(0xFF1476A9) else Color.Black
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                if (selectedSection == section) {
                                    Divider(color = Color(0xFF1476A9), thickness = 2.dp)
                                }
                            }
                        }
                    }

                    when (selectedSection) {
                        "Sobre Mi" -> {
                            SobreMiContent(navController = navController)
                        }
                        "Hoja de Vida" -> {
                            HojaDeVidaContent()
                        }
                        "Reseñas" -> {
                            ReseñasContent()
                        }
                    }

                }
            }
        )
    }
}

@Composable
fun SobreMiContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .padding(16.dp)
        ) {
            Text(
                text = "Especializado en derecho civil y familiar, mi objetivo es proporcionar soluciones legales efectivas y comprensibles para mis clientes. Fuera del trabajo, disfruto de la lectura y el tiempo al aire libre. Estoy aquí para ayudarte con tus necesidades legales.",
                fontSize = 14.sp,
                modifier = Modifier.wrapContentHeight()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1.5f).padding(8.dp)) {
                Text(text = "Información Personal", fontSize = 14.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(8.dp))

                // Ajusta el tamaño del texto y permite que se ajuste en múltiples líneas si es necesario
                PersonalInfoItem("Teléfono:", "+57 231313445")
                PersonalInfoItem("Email:", "ejemplo@correo.com")
                PersonalInfoItem("País:", "Colombia")
                PersonalInfoItem("Ciudad:", "Bogotá")
                PersonalInfoItem("Nombre Consultorio:", "Consultorio Legal")
            }

            Column(modifier = Modifier.weight(1f).padding(8.dp)) {
                Text(text = "Areas de Practica", fontSize = 14.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(8.dp))

                PracticaItem(area = "Derecho familiar", iconResId = R.drawable.der_familiar)



            }
        }

        Text(text = "Actividad", fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Agregar")
            Text(text = "Ver el historial de asesoramiento legal", color = Color(0xFF00A8FF), modifier = Modifier.clickable { navController.navigate(NavigationItem.PerfilRespuestaAbogado.route) })
        }
    }
}

@Composable
fun PersonalInfoItem(label: String, value: String) {
    Row(modifier = Modifier
        .padding(vertical = 4.dp)
        .fillMaxWidth()) {
        Text(text = label, fontSize = 12.sp, modifier = Modifier.weight(1f), maxLines = 1, overflow = TextOverflow.Ellipsis) // Asegura que el texto no se desborde
        Text(text = value,  fontSize = 12.sp, modifier = Modifier.weight(2f), maxLines = 1, overflow = TextOverflow.Ellipsis) // Igualmente aquí
    }
}
@Composable
fun PracticaItem(area: String, iconResId: Int) {
    Box(
        modifier = Modifier
            //.border(0.2.dp, Color.Gray, RoundedCornerShape(12.dp)) // Bordes redondeados
            .shadow(0.5.dp, RoundedCornerShape(1.dp)) // Sombra
            .padding(16.dp)
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Column para colocar el icono y el texto
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Centrar el contenido
            verticalArrangement = Arrangement.Center, // Alinear verticalmente
            modifier = Modifier.fillMaxWidth() // Ocupa el ancho total
        ) {
            // Imagen/icono
            Image(
                painter = painterResource(id = iconResId), // Usar el ID del recurso pasado
                contentDescription = "Ícono",
                modifier = Modifier
                    .size(24.dp) // Ajusta el tamaño del ícono
                    .clip(CircleShape) // Hace el ícono circular
            )
            // Texto
            Text(
                text = area,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 8.dp) // Espaciado entre el ícono y el texto
            )
        }
    }
}

@Composable
fun HojaDeVidaContent() {

    Text(text = "Aquí se mostrará la hoja de vida en PDF.", modifier = Modifier.fillMaxSize())
}

@Composable
fun ReseñasContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Reseñas", fontSize = 20.sp)

        Row(modifier = Modifier.padding(vertical = 16.dp)) {
            Image(painter = painterResource(id = R.drawable.profile), contentDescription = "Usuario", modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                TextField(
                    value = "",
                    onValueChange = { /* Acción para cambiar la reseña */ },
                    placeholder = { Text("Escribe tu reseña") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row {
                    repeat(5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Calificación",
                            tint = Color.Gray,
                            modifier = Modifier.clickable { /* Acción para seleccionar estrella */ }
                        )
                    }
                }

                androidx.compose.material3.Button(
                    onClick = {

                    },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(Color(0xFF001C36))
                ) {
                    androidx.compose.material3.Text("Publicar", color = Color.White)
                }


            }
        }


        ReseñaItem("Juan Pérez", "12 octubre, 2024", "Muy buen abogado, me ayudó mucho.")
        ReseñaItem("Ana Gómez", "10 octubre, 2024", "Recomendado, excelente atención.")
        ReseñaItem("Carlos Ruiz", "08 octubre, 2024", "Profesional y muy eficaz.")
    }
}

@Composable
fun ReseñaItem(nombre: String, fecha: String, reseña: String) {
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        Image(painter = painterResource(id = R.drawable.profile), contentDescription = "Usuario", modifier = Modifier.size(40.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = nombre, fontWeight = FontWeight.Bold)
            Text(text = fecha, fontSize = 12.sp, color = Color.Gray)
            Text(text = reseña)
            Text(text = "¿Te pareció útil esta reseña?", fontSize = 12.sp, color = Color.Gray)
            Row {
                TextButton(
                    onClick = { /* Acción para sí */ },
                    colors = androidx.compose.material.ButtonDefaults.textButtonColors(contentColor = Color(0xFF00A8FF)) // Establecer el color del texto
                ) {
                    Text("Sí")
                }
                TextButton(onClick = { /* Acción para no */ },
                    colors = androidx.compose.material.ButtonDefaults.textButtonColors(contentColor = Color(0xFF00A8FF)) ) {
                    Text("No")
                }
            }
        }
    }
}





