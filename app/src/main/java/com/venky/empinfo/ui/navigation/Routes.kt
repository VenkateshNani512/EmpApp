package com.venky.empinfo.ui.navigation

sealed class Routes(val route:String) {
    data object Splash : Routes("splash")
    data object Login : Routes("login")
    data object Home : Routes("home")
    data object Profile : Routes("profile")
    data object Settings : Routes("settings")
}
