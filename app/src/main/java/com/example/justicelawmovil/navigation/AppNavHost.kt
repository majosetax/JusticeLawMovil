package com.example.justicelawmovil.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.justicelawmovil.R
import com.example.justicelawmovil.screens.HomeScreen
import com.example.justicelawmovil.screens.LoginScreen
import com.example.justicelawmovil.screens.RegisterScreen
import com.example.justicelawmovil.screens.UsuarioDetailScreen
import com.example.justicelawmovil.screens.UsuariosScreen


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
            route = NavigationItem.UserList.route
        )

        {
            UsuariosScreen(navController)
        }

        composable(
            route = "userDetail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val usuarioId = backStackEntry.arguments?.getInt("id")
            if (usuarioId != null) {
                UsuarioDetailScreen(usuarioId)
            }
        }
    }
}