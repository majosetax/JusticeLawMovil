package com.example.justicelawmovil.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.justicelawmovil.model.NotificationModel
import com.example.justicelawmovil.screens.Configuracion
import com.example.justicelawmovil.screens.Historial
import com.example.justicelawmovil.screens.HomeScreen
import com.example.justicelawmovil.screens.Informacion
import com.example.justicelawmovil.screens.LoginScreen
import com.example.justicelawmovil.screens.NotificationsScreen
import com.example.justicelawmovil.screens.OlvidoContrasena
import com.example.justicelawmovil.screens.OlvidoContrasenaCodEmail
import com.example.justicelawmovil.screens.OlvidoContrasenaCodTel
import com.example.justicelawmovil.screens.OlvidoContrasenaEmail
import com.example.justicelawmovil.screens.OlvidoContrasenaNueva
import com.example.justicelawmovil.screens.OlvidoContrasenaTelefn
import com.example.justicelawmovil.screens.PerfilAbogado
import com.example.justicelawmovil.screens.PerfilRespuestaAbogado
import com.example.justicelawmovil.screens.ProfileScreen
import com.example.justicelawmovil.screens.ReestablecimientoExitoso
import com.example.justicelawmovil.screens.RegisterScreen
import com.example.justicelawmovil.screens.UserListScreen
import com.example.justicelawmovil.screens.VerPerfilAbogado

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = NavigationItem.Login.route
        ) {
            LoginScreen(navController)
        }

        composable(
            route = NavigationItem.Register.route
        ) {
            RegisterScreen(navController)
        }

        composable(
            route = NavigationItem.Home.route
        ) {
            HomeScreen(navController)
        }

        composable(
            route = NavigationItem.Profile.route
        ) {
            ProfileScreen(navController)
        }

        composable(route = NavigationItem.UserList.route) { // Nueva pantalla
            UserListScreen(navController)
        }

        composable(
            route = NavigationItem.VerPerfilAbogado.route
        ) {
            VerPerfilAbogado(navController)
        }

        composable(
            route = NavigationItem.PerfilAbogado.route
        ) {
            PerfilAbogado(navController)
        }

        composable(
            route = NavigationItem.Historial.route
        ) {
            Historial(navController)
        }

        composable(route = NavigationItem.Configuracion.route) { // Nueva pantalla
            Configuracion(navController)
        }

       composable(route=NavigationItem.Informacion.route){
           Informacion(navController)
       }

        composable(
            route = NavigationItem.Notification.route
        ) {
            NotificationsScreen(navController)
        }
        composable(
            route = NavigationItem.OlvidoContrasena.route
        ) {
            OlvidoContrasena(navController)
        }

        composable(
            route = NavigationItem.OlvidoContrasenaEmail.route
        ) {
            OlvidoContrasenaEmail(navController)
        }
        composable(
            route = NavigationItem.OlvidoContrasenaTelefn.route
        ) {
            OlvidoContrasenaTelefn(navController)
        }

        composable(
            route = NavigationItem.OlvidoContrasenaCodEmail.route
        ) {
            OlvidoContrasenaCodEmail(navController)
        }
        composable(
            route = NavigationItem.OlvidoContrasenaCodTel.route
        ) {
            OlvidoContrasenaCodTel(navController)
        }

        composable(
            route = NavigationItem.OlvidoContrasenaNueva.route
        ) {
            OlvidoContrasenaNueva(navController)
        }
        composable(
            route = NavigationItem.ReestablecimientoExitoso.route
        ) {
            ReestablecimientoExitoso(navController)
        }

        composable(
            route = NavigationItem.PerfilRespuestaAbogado.route
        ) {
            PerfilRespuestaAbogado(navController)
        }
        
    }
}