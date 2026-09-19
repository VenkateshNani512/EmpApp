package com.venky.empinfo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.venky.empinfo.presentation.MainScreen
import com.venky.empinfo.presentation.login.LoginScreen
import com.venky.empinfo.presentation.login.RegisterScreen
import com.venky.empinfo.presentation.navigation.Routes.Register
import com.venky.empinfo.presentation.splash.SplashScreen
import com.venky.empinfo.presentation.login.LoginViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.venky.empinfo.presentation.employee.EmployeeViewModel

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
        composable(Routes.Register.route){
            RegisterScreen(
                onRegisterSuccess = {
                   navController.navigate(Routes.Login.route){
                       popUpTo(Register.route){
                           inclusive = true
                       }
                   }

            },
                onLoginClick = {
                navController.navigate(Routes.Login.route)
            })
        }
        composable(Routes.Login.route){
            val viewmodel: LoginViewModel = hiltViewModel()

            LoginScreen( viewModel = viewmodel,onLoginSuccess =  {
                navController.navigate(Routes.Home.route){
                    popUpTo(Routes.Login.route){
                        inclusive = true
                    }
                }

            })
            }
        composable(Routes.Home.route){
           val viewmodel: EmployeeViewModel = hiltViewModel()
           MainScreen(viewmodel)

           }

        }

}