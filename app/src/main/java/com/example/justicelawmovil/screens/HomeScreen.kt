@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.justicelawmovil.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.justicelawmovil.navigation.NavigationItem
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.justicelawmovil.R

@Composable
fun HomeScreen(navController: NavController) {
    val homeIcon: Painter = painterResource(id = R.drawable.home)
    val searchIcon: Painter = painterResource(id = R.drawable.search)
    val forumIcon: Painter = painterResource(id = R.drawable.forum)
    val profileIcon: Painter = painterResource(id = R.drawable.profile)

    Column () {
Text(
    text = "Preguntas recientes", style = MaterialTheme.typography.bodyLarge.copy(
        fontSize = 16.sp,
        color = Color(0xFF001C36)
    ),  )
    }
  Box() {               Row(


      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceEvenly,
      modifier = Modifier.fillMaxSize()


  )

  {

  IconButton(onClick = { /* TODO: profile button click */ }) {
          Icon(
              painter = profileIcon,
              contentDescription = "Profile"
          )
      }
      Text(  text = "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el...vermas",
style = MaterialTheme.typography.bodyLarge.copy( fontSize = 8.sp, color = Color(0XFF001C36)  ),
        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp) .width(200.dp) )

      Text(  text = "+3",
          style = MaterialTheme.typography.bodyLarge.copy( fontSize = 50.sp, color = Color(0XFF001C36)  )
      )


  }

    Box(
         modifier = Modifier
           .fillMaxSize()

    ) {

        BottomAppBar(
            modifier = Modifier
                .padding(16.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(25.dp))
                .align(Alignment.BottomCenter),
            containerColor =  Color(0xFF001C36),
            contentColor = Color(0xFFFFFFFF),


        ) {

            Row(

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()


            )

            {


                IconButton(onClick = { /* TODO: Handle home button click */ }) {

                    Icon(
                        painter = homeIcon,
                        contentDescription = "Home",

                        )
                }

               // Spacer(modifier = Modifier.height(50.dp))
                IconButton(onClick = { /* TODO: Handle search button click */ }) {
                    Icon(
                        painter = searchIcon,
                        contentDescription = "Search"
                    )
                }
                IconButton(onClick = { /* TODO: Handle profile button click */ }) {
                    Icon(
                        painter = forumIcon,
                        contentDescription = "Profile"
                    )
                }
            } }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}



