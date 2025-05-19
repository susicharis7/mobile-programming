package com.example.studyflow

//import androidx.compose.runtime.Composable
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.studyflow.ui.screens.auth.LoginScreen
//import com.example.studyflow.ui.screens.auth.RegisterScreen
//
//
//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = "login"
//    ) {
//        // Auth screens
//        composable("register") {
//            RegisterScreen(
//                onLoginClick = { navController.navigate("login") },
//                onRegisterSuccess = {
//                    navController.navigate("home") {
//                        popUpTo(navController.graph.id) { inclusive = true }
//                    }
//                }
//            )
//        }
//
//        composable("login") {
//            LoginScreen(
//                onRegisterNav = { navController.navigate("register") },
//                onLoginSuccess = {
//                    navController.navigate("home") {
//                        popUpTo(navController.graph.id) { inclusive = true }
//                    }
//                }
//            )
//        }
//
//        // Main app entry point
//        composable("home") {
//            HomeNavigation(navController)
//        }
//    }
//}
