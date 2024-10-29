package com.example.justicelawmovil.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.justicelawmovil.R // Cambia 'justicelawmovil' por el nombre correcto de tu paquete
import com.example.justicelawmovil.navigation.NavigationItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ForumScreen(navController: NavController) {

    val drawerState = androidx.compose.material3.rememberDrawerState(DrawerValue.Closed)
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

            DrawerMenuItem(icon = notificationIcon, label = "Notification") {
                navController.navigate(NavigationItem.Notification.route)
            }

            DrawerMenuItem(icon = historialIcon, label = "Historial") {
                navController.navigate(NavigationItem.Historial.route)
            }

            androidx.compose.material3.Divider(modifier = Modifier.padding(vertical = 8.dp))

            DrawerMenuItem(icon = settingsIcon, label = "Configuración") {
                navController.navigate(NavigationItem.Configuracion.route)
            }

            DrawerMenuItem(icon = quienesSomos, label = "Quienes Somos") {
                navController.navigate(NavigationItem.QuienesSomos.route)
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
                    androidx.compose.material3.IconButton(onClick = {
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

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent(navController) }
    ) {
        androidx.compose.material3.Scaffold(

            topBar = { TopBar(drawerState = drawerState, menuIcon = menuIcon, scope = scope) },
            bottomBar = {
                BottomBar(
                    navController = navController,
                    homeIcon = homeIcon,
                    searchIcon = searchIcon,
                    forumIcon = forumIcon
                )
            },

            content = { innerPadding ->



                val questions = listOf(
                    ForumQuestion(
                        date = "30 de marzo, 2024",
                        question = "¿Qué pasos debo seguir si me lesiono en un accidente de tráfico y el culpable se da a la fuga?",
                        category = "Legal",
                        answers = 2
                    ),
                    ForumQuestion(
                        date = "30 de marzo, 2024",
                        question = "¿Cómo puedo proteger mis derechos de autor en línea?",
                        category = "Propiedad intelectual",
                        answers = 1
                    ),
                    ForumQuestion(
                        date = "30 de marzo, 2024",
                        question = "¿Qué acciones legales puedo tomar si soy víctima de acoso en el lugar de trabajo?",
                        category = "Legal",
                        answers = 3
                    )
                )

                val categories = listOf("General", "Consumidor", "Laboral", "Familiar", "Civil")

                Column(modifier = Modifier.fillMaxSize().padding(16.dp)   .padding(innerPadding)) {


                    androidx.compose.material3.Button(
                        onClick = {
                            navController.navigate(
                                NavigationItem.Calendario.route
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(50),
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(Color(0xFF001C36))
                    ) {
                        androidx.compose.material3.Text("Calendario", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    androidx.compose.material3.Button(
                        onClick = {
                            navController.navigate(
                                NavigationItem.VerPerfilAbogado.route
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(Color(0xFF001C36))
                    ) {
                        androidx.compose.material3.Text("Ver perfil abogado", color = Color.White)
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = "¡Bienvenidos al Foro!",
                            style = MaterialTheme.typography.h6
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Icon(
                            painter = painterResource(R.drawable.nn),
                            contentDescription = "Buscar",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }


                    Spacer(modifier = Modifier.height(16.dp))


                    SearchBar()

                    Spacer(modifier = Modifier.height(16.dp))


                    CategoryRow(categories)

                    Spacer(modifier = Modifier.height(16.dp))


                    LazyColumn {
                        items(questions) { question ->
                            ForumQuestionCard(question = question)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }

        )
    }
}

@Composable
fun SearchBar() {
    val searchQuery = remember { mutableStateOf(TextFieldValue("")) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp)) // Borde negro y redondeado

            .padding(8.dp)

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Ícono de lupa
            Icon(
                painter = painterResource(R.drawable.search), // Asegúrate de que 'search' sea el nombre correcto del ícono
                contentDescription = "Buscar",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            BasicTextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.body1.copy(color = Color.Black),
                decorationBox = { innerTextField ->
                    if (searchQuery.value.text.isEmpty()) {
                        Text(
                            text = "Buscar...",
                            style = MaterialTheme.typography.body1.copy(color = Color.Gray)
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}

@Composable
fun CategoryRow(categories: List<String>) {
    LazyRow {
        items(categories) { category ->
            CategoryChip(category = category)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun CategoryChip(category: String) {
    Box(
        modifier = Modifier
            .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = category, style = MaterialTheme.typography.body2)
    }
}

@Composable
fun ForumQuestionCard(question: ForumQuestion) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = question.date, style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = question.question, style = MaterialTheme.typography.h6.copy(fontSize = 16.sp))
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = question.category,
                    style = MaterialTheme.typography.body2.copy(color = Color.White),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(Color(0xFF0A3B55), shape = RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                )
                Text(text = "Respuestas: ${question.answers}")



            }
        }
    }
}

data class ForumQuestion(
    val date: String,
    val question: String,
    val category: String,
    val answers: Int
)

@Preview(showBackground = true)
@Composable
fun PreviewForumScreen() {
    ForumScreen(rememberNavController())
}