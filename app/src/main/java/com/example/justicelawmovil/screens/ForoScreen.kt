package com.example.justicelawmovil.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.justicelawmovil.model.ForumQuestion
import com.example.justicelawmovil.viewModel.ForumViewModel

@Composable
fun ForumScreen(navController: NavController, viewModel: ForumViewModel = viewModel()) {
    val questions by viewModel.questions.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    var selectedTabIndex by remember { mutableStateOf(0) } // Para las pestañas
    var searchText by remember { mutableStateOf("") } // Para el buscador

    // Llama a fetchForumQuestions al iniciar la pantalla
    LaunchedEffect(Unit) {
        viewModel.fetchForumQuestions()
    }

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        // Encabezado
        Text(
            text = "¡Bienvenido al Foro!",
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = Color.Black
        )

        // Buscador
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(48.dp)
                .background(Color(0xFFF5F5F5), MaterialTheme.shapes.small)
        ) {
            BasicTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true,
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black)
            )
        }

        // Pestañas de categorías
        val tabs = listOf("General", "Consumidor", "Laboral", "Familiar", "Civil")
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White,
            contentColor = MaterialTheme.colors.primary,
            edgePadding = 16.dp
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = tab,
                            style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (selectedTabIndex == index) Color(0xFFFFC107) else Color.Gray
                            )
                        )
                    }
                )
            }
        }

        // Cuerpo principal
        Box(modifier = Modifier.weight(1f)) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                errorMessage != null -> {
                    Text(
                        text = errorMessage!!,
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.error
                    )
                }
                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(questions.filter { it.affair.contains(searchText, true) }) { question ->
                            QuestionItem(question = question)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuestionItem(question: ForumQuestion) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = question.datePublication,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Light
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = question.affair,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = question.content,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Respuestas: ${question.likes}",
                    style = MaterialTheme.typography.caption.copy(color = Color.Gray)
                )
            }
        }
    }
}
