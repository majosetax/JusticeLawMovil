package com.example.justicelawmovil.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.justicelawmovil.R

@Composable
fun CalendarioScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        MonthHeader()
        Spacer(modifier = Modifier.height(16.dp))
        Header()
        Spacer(modifier = Modifier.height(16.dp))
        DaysRow()
        Spacer(modifier = Modifier.height(16.dp))
        ScheduleList()
    }
}

@Composable
fun MonthHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* Acción de regreso */ }) {
            Icon(
                painter = painterResource(id = R.drawable.menor),
                contentDescription = "Volver",
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Abril",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.abogado),
            contentDescription = "Imagen de perfil",
            modifier = Modifier
                .size(50.dp)
                .padding(end = 8.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "David Astrada",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Asesorías de 1 hora",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatusLegend(color = Color(0xFFF5E1C4), label = "Ocupado")
        StatusLegend(color = Color(0xFFD5E8F6), label = "Disponible")
        StatusLegend(color = Color(0xFFF2F2F2), label = "No disponible")
    }
}

@Composable
fun DaysRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DayItem("1", "Lun")
        DayItem("2", "Mar")
        DayItem("3", "Mie")
        DayItem("4", "Jue")
        DayItem("5", "Vie")
        DayItem("6", "Sab")
        DayItem("7", "Dom")
        DayItem("8", "Lun")
        DayItem("9", "Mar")
        DayItem("10", "Mie")
        DayItem("11", "Jue")
        DayItem("12", "Vie")
    }
}

@Composable
fun DayItem(dayNumber: String, dayName: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = dayNumber, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = dayName, fontSize = 14.sp, color = Color.Gray)
    }
}

@Composable
fun StatusLegend(color: Color, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label, fontSize = 14.sp)
    }
}

@Composable
fun ScheduleList() {
    val timeSlots = listOf(
        TimeSlot("08:00", "Ocupado"),
        TimeSlot("09:00", "Ocupado"),
        TimeSlot("10:00", "Disponible"),
        TimeSlot("11:00", "Disponible"),
        TimeSlot("12:00", "No disponible"),
        TimeSlot("13:00", "No disponible"),
        TimeSlot("14:00", "Disponible")
    )

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(timeSlots.size) { index ->
            ScheduleItem(timeSlot = timeSlots[index])
            Spacer(modifier = Modifier.height(8.dp)) // Espaciador entre los elementos
        }
    }
}

@Composable
fun ScheduleItem(timeSlot: TimeSlot) {
    val backgroundColor = when (timeSlot.status) {
        "Ocupado" -> Color(0xFFF5E1C4)
        "Disponible" -> Color(0xFFD5E8F6)
        "No disponible" -> Color(0xFFF2F2F2)
        else -> Color.White
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(backgroundColor)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = timeSlot.time,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

data class TimeSlot(val time: String, val status: String)

@Preview(showBackground = true)
@Composable
fun CalendarioScreenPreview() {
    CalendarioScreen()
}
