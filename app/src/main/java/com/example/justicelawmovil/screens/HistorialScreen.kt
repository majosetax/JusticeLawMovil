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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
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
fun Historial(navController: NavController) {

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
                    text = "Historial",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = { /* Lógica de búsqueda */ }) {
                    Icon(
                        painter = searchIcon,
                        contentDescription = "Buscar",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black
                    )
                }
            }
        }
    }



    @Composable
    fun BottomBar(
        navController: NavController,
        homeIcon: Painter,
        searchIcon: Painter,
        forumIcon: Painter
    ) {
        androidx.compose.material3.BottomAppBar(
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
                androidx.compose.material3.IconButton(onClick = {
                    navController.navigate(
                        NavigationItem.Home.route
                    )
                }) {
                    androidx.compose.material3.Icon(
                        painter = homeIcon,
                        contentDescription = "Home",
                        tint = Color.White
                    )
                }
                androidx.compose.material3.IconButton(onClick = {
                    navController.navigate(
                        NavigationItem.Informacion.route
                    )
                }) {
                    androidx.compose.material3.Icon(
                        painter = searchIcon,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
                androidx.compose.material3.IconButton(onClick = {
                    navController.navigate(
                        NavigationItem.Foro.route
                    )
                }) {
                    androidx.compose.material3.Icon(
                        painter = forumIcon,
                        contentDescription = "Foro",
                        tint = Color.White
                    )
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


                Text(text = "Borrar datos de navegación...", color = Color(0xFFCF9E3E))
                Spacer(modifier = Modifier.height(14.dp))


                Text(text = "12 de abril, 2024", fontSize = 14.sp, color = Color.Gray)

                for (i in 1..4) {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color.Gray, shape = RoundedCornerShape(20.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "I", color = Color.White, fontWeight = FontWeight.Bold)
                        }

                        Row(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Se ha visitado ",
                                color = Color.Black,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "La tutela",
                                color = Color(0xFF00A8FF),
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .clickable(onClick = {
                                        // Lógica para manejar el clic
                                    })
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(onClick = { /* Lógica para eliminar */ }) {
                            Image(
                                painter = painterResource(id = R.drawable.cancelar),
                                contentDescription = "Eliminar",
                                modifier = Modifier.size(24.dp),

                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "11 de abril, 2024", fontSize = 14.sp, color = Color.Gray)

                for (i in 1..4) {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color.Gray, shape = RoundedCornerShape(20.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "I", color = Color.White, fontWeight = FontWeight.Bold)
                        }

                        Row(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Se ha visitado ",
                                color = Color.Black,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "La tutela",
                                color = Color(0xFF00A8FF),
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .clickable(onClick = {
                                        // Lógica para manejar el clic
                                    })
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        IconButton(onClick = { /* Lógica para eliminar */ }) {
                            Image(
                                painter = painterResource(id = R.drawable.cancelar),
                                contentDescription = "Eliminar",
                                modifier = Modifier.size(24.dp),

                                )
                        }
                    }
                }
            }



        }
    )
}



