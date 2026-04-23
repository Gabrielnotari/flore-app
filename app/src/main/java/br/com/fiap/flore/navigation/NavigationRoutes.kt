package br.com.fiap.flore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.flore.screens.HomeScreen
import br.com.fiap.flore.screens.InitialScreen
import br.com.fiap.flore.screens.LoginScreen
import br.com.fiap.flore.screens.SignupScreen

@Composable
fun NavigationRoutes() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.InitialScreen
    ){
        composable(Destination.InitialScreen.route){
            InitialScreen()
        }
        composable(Destination.HomeScreen.route){
            HomeScreen()
        }
        composable(Destination.SignupScreen.route){
            SignupScreen()
        }
        composable(Destination.LoginScreen.route){
            LoginScreen()
        }
    }
}