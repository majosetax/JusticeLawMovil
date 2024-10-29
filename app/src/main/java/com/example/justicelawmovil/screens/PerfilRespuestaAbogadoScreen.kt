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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.justicelawmovil.R
import com.example.justicelawmovil.navigation.NavigationItem


@Composable
fun PerfilRespuestaAbogado(navController: NavController) {

    val homeIcon: Painter = painterResource(id = R.drawable.home)
    val searchIcon: Painter = painterResource(id = R.drawable.search)
    val forumIcon: Painter = painterResource(id = R.drawable.forum)
    val backIcon: Painter = painterResource(id = R.drawable.volver)


    @Composable
    fun TopBar(navController: NavController, backIcon: Painter, searchIcon: Painter) {
        TopAppBar(
            backgroundColor = Color.White,
            modifier = Modifier
                .height(100.dp)
                .padding(0.dp),
            elevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        painter = backIcon,
                        contentDescription = "Volver",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black
                    )
                }

                Text(
                    text = "Mis respuestas y categorias",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )


            }
        }
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
                    androidx.compose.material3.Icon(painter = homeIcon, contentDescription = "Home", tint = Color.White)
                }
                IconButton(onClick = { /* Boton informaciones */ }) {
                    androidx.compose.material3.Icon(painter = searchIcon, contentDescription = "Search", tint = Color.White)
                }
                IconButton(onClick = { /* Acción del botón de perfil */ }) {
                    androidx.compose.material3.Icon(painter = forumIcon, contentDescription = "Foro", tint = Color.White)
                }
            }
        }
    }

    Scaffold(
        topBar = { TopBar(navController = navController, backIcon = backIcon, searchIcon = searchIcon) },
        bottomBar = { BottomBar(navController = navController, homeIcon = homeIcon, searchIcon = searchIcon, forumIcon = forumIcon) },
        content = { innerPadding ->
            val scrollState = rememberScrollState()


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp)
                    .verticalScroll(scrollState)
                    .padding(innerPadding),
                horizontalAlignment = Alignment.Start
            ) {


                // Filtro tipo select
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Más recientes",
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Abrir filtro",
                        modifier = Modifier.clickable {
                            // Acción para abrir el menú desplegable
                        }
                    )
                }

                // Contenido de las respuestas
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    // Foto de perfil
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.Gray, CircleShape)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Andres Lopez",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Usuario",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }

                // Título y contenido de la pregunta
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text(
                        text = "¿Qué pasos debo seguir en un proceso de divorcio?",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "María y Juan están casados desde hace diez años, pero su relación se ha deteriorado. Juan trabaja mucho y apenas están juntos. Discuten frecuentemente y María se siente sola y abandonada emocionalmente. Además, descubrió que Juan tiene una aventura emocional con una compañera de trabajo. Considera el divorcio como la mejor opción para seguir adelante.",
                        fontSize = 14.sp
                    )
                    Text(
                        text = "30 de marzo, 2024",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    text = "Respuestas:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )


                Spacer(modifier = Modifier.width(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    // Foto de perfil
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.Gray, CircleShape)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "David Astrada",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Abogado",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }



            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text(
                        text = "María y Juan están casados desde hace diez años, pero su relación se ha deteriorado. Juan trabaja mucho y apenas están juntos. Discuten frecuentemente y María se siente sola y abandonada emocionalmente. Además, descubrió que Juan tiene una aventura emocional con una compañera de trabajo. Considera el divorcio como la mejor opción para seguir adelante.",
                        fontSize = 14.sp
                    )
                    Text(
                        text = "30 de marzo, 2024",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }


            }
            }




    )
}



