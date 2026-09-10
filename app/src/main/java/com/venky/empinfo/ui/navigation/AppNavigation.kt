package com.venky.empinfo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.venky.empinfo.ui.home.MainScreen
import com.venky.empinfo.ui.login.Loginscreen
import com.venky.empinfo.ui.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Routes.Splash.route){

        composable(Routes.Splash.route){
            SplashScreen(onSplashFinished = {
                navController.navigate(Routes.Login.route){
                popUpTo(Routes.Splash.route){
                    inclusive = true
                }
            }
            })
                }
        composable(Routes.Login.route){
            Loginscreen( onLoginSuccess =  {
                navController.navigate(Routes.Home.route){
                    popUpTo(Routes.Login.route){
                        inclusive = true
                    }
                }

            })
            }
        composable(Routes.Home.route){
            MainScreen()
        }
        }

}