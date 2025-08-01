package com.example.beautyhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.beautyhub.ui.theme.BeautyHubTheme
import com.example.beautyhub.view.*
import com.example.beautyhub.viewmodel.UserViewModel
import com.example.beautyhub.viewmodel.ProductViewModel
import com.example.beautyhub.viewmodel.CartViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BeautyHubTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    BeautyHubApp(navController)
                }
            }
        }
    }
}

@Composable
fun BeautyHubApp(navController: NavHostController) {
    // Shared ViewModels across the app
    val userViewModel: UserViewModel = viewModel()
    val productViewModel: ProductViewModel = viewModel()
    val cartViewModel: CartViewModel = viewModel()
    
    NavHost(
        navController = navController, 
        startDestination = "splash"
    ) {
        composable("splash") { 
            SplashScreen(navController) 
        }
        
        composable("login") { 
            LoginScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }
        
        composable("register") { 
            RegisterScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }
        
        composable("home") { 
            HomeScreen(
                navController = navController,
                userViewModel = userViewModel,
                productViewModel = productViewModel,
                cartViewModel = cartViewModel
            )
        }
    }
}
