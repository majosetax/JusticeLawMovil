package com.example.justicelawmovil.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.painterResource
import com.example.justicelawmovil.R // Cambia 'justicelawmovil' por el nombre correcto de tu paquete

@Composable
fun ForumScreen() {
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

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

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
    ForumScreen()
}