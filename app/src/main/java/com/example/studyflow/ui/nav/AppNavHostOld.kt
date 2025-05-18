package com.example.studyflow.ui.nav
//
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.hilt.navigation.compose.hiltViewModel
////import androidx.navigation.NavHost
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.navigation
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.toRoute
//import com.example.studyflow.model.Task
//import com.example.studyflow.model.User
//import com.example.studyflow.ui.screens.TasksScreen
//import com.example.studyflow.ui.screens.DashboardScreen
//import com.example.studyflow.ui.screens.auth.LoginScreen
//import com.example.studyflow.ui.screens.auth.RegisterScreen
//import com.example.studyflow.ui.screens.navigations.BottomNavigationBar
//import com.example.studyflow.ui.viewmodel.TaskViewModel
//import com.example.studyflow.ui.viewmodel.UserViewModel
//import com.example.studyflow.ui.nav.types.CustomNavType
//import kotlinx.serialization.Serializable
//import kotlin.reflect.typeOf
//
//@Serializable object Auth
//
//@Serializable object Login
//@Serializable object Register
//
//@Serializable object Main
//
//@Serializable data class DashboardScreen(val user: User)
//@Serializable data class TasksScreen(val user: User)
////@Serializable data class SubjectsScreen(val user: User)
////@Serializable data class StudyTimerScreen(val user: User)
////@Serializable data class ScheduleScreen(val user: User)
//
//@Composable
//fun AppNavHostOld() {
//    val navController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//
//    val showBottomBar = (currentRoute?.split('/')?.get(0)) !in listOf(
//        Login::class.qualifiedName,
//        Register::class.qualifiedName
//    )
//
//    Scaffold(
//        bottomBar = {
//            if (showBottomBar) {
//                val user = remember { navBackStackEntry!!.toRoute<DashboardScreen>().user }
//                BottomNavigationBar(
//                    user,
//                    onDashboardNav = { navController.navigate(DashboardScreen(user)) {
//                        popUpTo(Main) { saveState = true }
//                        launchSingleTop = true
//                    } },
//                    onTasksNav = { navController.navigate(TasksScreen(user)) },
////                    onTimerNav = { navController.navigate(StudyTimerScreen(user)) },
////                    onScheduleNav = { navController.navigate(ScheduleScreen(user)) },
////                    onSubjectsNav = { navController.navigate(SubjectsScreen(user)) }
//                )
//            }
//        }
//    ) { padding ->
//        NavHost(
//            navController = navController,
//            startDestination = Auth, modifier = Modifier.padding(padding)
//        ) {
//            navigation<Auth>(startDestination = Login) {
//                composable<Login> { backStackEntry ->
//                    val authViewModel: UserViewModel = hiltViewModel(backStackEntry)
//                    LoginScreen(authViewModel, onRegisterNav = {
//                        navController.navigate(route = Register)
//                    }, onLoginSuccess = {user: User ->
//                        navController.navigate(route = DashboardScreen(user = user)) {
//                            popUpTo(route = Auth) { inclusive = true }
//                        }
//                    })
//                }
//                composable<Register> { backStackEntry ->
//                    val authViewModel: UserViewModel = hiltViewModel(backStackEntry)
//                    RegisterScreen(authViewModel, onLoginNav = {
//                        navController.navigate(route = Login)
//                    }, onRegisterSuccess = {user: User ->
//                        navController.navigate(route = DashboardScreen(user = user)) {
//                            popUpTo(route = Auth) { inclusive = true }
//                        }
//                    })
//                }
//            }
//            navigation<Main>(startDestination = DashboardScreen(user = User(0, "", "", ""))) {
//                composable<DashboardScreen>(
//                    typeMap = mapOf(
//                        typeOf<User>() to CustomNavType.UserType
//                    )
//                ) { backStackEntry ->
//                    val taskViewModel: TaskViewModel = hiltViewModel(backStackEntry)
////                    val timersViewModel: TimersViewModel = hiltViewModel(backStackEntry) // not sure if we'll need separate for each timer for the timer screens
////                    examsViewModel
////                    might need something for study activity thing
//                    val loggedUser = backStackEntry.toRoute<DashboardScreen>().user
//                    DashboardScreen(loggedUser = loggedUser, taskViewModel, navController) // will have other viewmodels too
//                }
//                composable<TasksScreen>(
//                    typeMap = mapOf(
//                        typeOf<User>() to CustomNavType.UserType
//                    )
//                ) { backStackEntry ->
//                    val taskViewModel: TaskViewModel = hiltViewModel(backStackEntry)
//                    val loggedUser = backStackEntry.toRoute<TasksScreen>().user
//                    TasksScreen(loggedUser, taskViewModel)
//                }
////                data class Dashboard(val user: User) DONE
////                data class TasksScreen(val user: User) DONE
////                data class Subjects(val user: User)
////                data class StudyTimer(val user: User)
////                data class Schedule(val user: User)
//            }
//        }
//    }
//}