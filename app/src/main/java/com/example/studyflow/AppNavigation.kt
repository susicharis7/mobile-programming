package com.example.studyflow

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studyflow.screens.LoginScreen
import com.example.studyflow.screens.RegisterScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "register"
    ) {
        composable("register") {
            RegisterScreen(
                onLoginClick = {
                    navController.navigate("login")
                }
            )
        }
        composable("login") {
            LoginScreen(
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }
    }
}
