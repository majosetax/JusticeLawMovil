package com.example.justicelawmovil.navigation

enum class Screen { // Names to identify the screens
    LOGIN,
    REGISTER,
    HOME
}

// Objects to identify where navigate
sealed class NavigationItem(val route: String) {
    object Login: NavigationItem(Screen.LOGIN.name)
    object Register: NavigationItem(Screen.REGISTER.name)
    object Home: NavigationItem(Screen.HOME.name)
}