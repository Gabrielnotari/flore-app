package br.com.fiap.flore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.flore.screens.HomeScreen
import br.com.fiap.flore.screens.InitialScreen
import br.com.fiap.flore.screens.LoginScreen
import br.com.fiap.flore.screens.SignupScreen

@Composable
fun NavigationRoutes() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.InitialScreen.route
    ){
        composable(Destination.InitialScreen.route){
            InitialScreen(navController)
        }
        composable(
            route = Destination.HomeScreen.route,
            arguments = listOf(navArgument("email"){
                type = NavType.StringType
            })
        ){ backStackEntry ->
            var email = backStackEntry.arguments?.getString("email")
            HomeScreen(navController, email)
        }
        composable(Destination.SignupScreen.route){
            SignupScreen(navController)
        }
        composable(Destination.LoginScreen.route){
            LoginScreen(navController)
        }
    }
}