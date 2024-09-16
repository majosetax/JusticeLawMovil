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


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier, // The modifier to be applied to the layout
    navController: NavHostController, // The navController for this host
    startDestination: String = NavigationItem.Login.route // Start route
) {
    NavHost( // Provides in place in the Compose hierarchy for self contained navigation to occur.
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable( // This method adds the composable to the NavGraphBuilder
            route = NavigationItem.Login.route // Route for the destination
        ) {
            LoginScreen(navController) // Composable for the destination
        }

        composable( // This method adds the composable to the NavGraphBuilder
            route = NavigationItem.Register.route // Route for the destination
        ) {
            RegisterScreen(navController) // Composable for the destination
        }

        composable( // This method adds the composable to the NavGraphBuilder
            route = NavigationItem.Home.route // Route for the destination
        ) {
            HomeScreen(navController) // Composable for the destination
        }


    }
}