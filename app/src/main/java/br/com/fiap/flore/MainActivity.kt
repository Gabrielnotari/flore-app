package br.com.fiap.flore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.fiap.flore.navigation.NavigationRoutes
import br.com.fiap.flore.screens.InitialScreen
import br.com.fiap.flore.screens.LoginScreen
import br.com.fiap.flore.screens.SignupScreen
import br.com.fiap.flore.ui.theme.FloreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FloreTheme {
                NavigationRoutes()
            }
        }
    }
}



