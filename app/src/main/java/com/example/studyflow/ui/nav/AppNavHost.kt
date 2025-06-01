package com.example.studyflow.ui.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.studyflow.model.User
import com.example.studyflow.ui.screens.SubjectsScreen
import com.example.studyflow.ui.screens.TasksScreen
import com.example.studyflow.ui.screens.DashboardScreen
import com.example.studyflow.ui.screens.auth.LoginScreen
import com.example.studyflow.ui.screens.auth.RegisterScreen
import com.example.studyflow.ui.screens.navigations.BottomNavigationBar
import com.example.studyflow.ui.viewmodel.SubjectViewModel
import com.example.studyflow.ui.viewmodel.TaskViewModel
import com.example.studyflow.ui.viewmodel.UserViewModel
import com.example.studyflow.ui.nav.types.CustomNavType
import com.example.studyflow.ui.screens.MyProfileScreen
import com.example.studyflow.ui.screens.ScheduleScreen
import com.example.studyflow.ui.screens.TimersScreen
import com.example.studyflow.ui.viewmodel.ExamViewModel
import com.example.studyflow.ui.viewmodel.TimerViewModel
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable object Auth

@Serializable object Login
@Serializable object Register

@Serializable object Main

@Serializable object DashboardNav
@Serializable object TasksNav
@Serializable object SubjectsNav
@Serializable object StudyTimerNav
@Serializable object ScheduleNav


// SettingsOverlay
@Serializable object ProfileNav

@Composable
fun AppNavHost(userViewModel: UserViewModel = hiltViewModel<UserViewModel>()) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isLoading by userViewModel.isLoading.collectAsState()
    val loggedUser by userViewModel.loggedUser.collectAsState()
    val startDestination: Any = Auth

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    LaunchedEffect(loggedUser) {
        if (loggedUser == null) {
            navController.navigate(Login) {
                popUpTo(Main) { inclusive = true }
                launchSingleTop = true
            }
        } else {
            userViewModel.setLoadingFalse()
            navController.navigate(DashboardNav) {
                popUpTo(Auth) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    val showBottomBar = (currentRoute?.split('/')?.get(0)) !in listOf( // !in to not have to type all 5 other classes
        Login::class.qualifiedName,
        Register::class.qualifiedName
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                    BottomNavigationBar(
                        currentRoute,
                        onDashboardNav = { navController.navigate(DashboardNav) {
                            popUpTo(Main)
                            launchSingleTop = true
                        } },
                        onTasksNav = { navController.navigate(TasksNav) },
                        onTimerNav = { navController.navigate(StudyTimerNav) },
                        onScheduleNav = { navController.navigate(ScheduleNav) },
                        onSubjectsNav = { navController.navigate(SubjectsNav) }
                    )


            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = startDestination, modifier = Modifier.padding(padding)
        ) {
            navigation<Auth>(startDestination = Login) {
                composable<Login> {
                    if (loggedUser == null) {
                        LoginScreen(userViewModel, onRegisterNav = {
                            navController.navigate(route = Register)
                        }, onLoginSuccess = {
                            navController.navigate(route = DashboardNav) {
                                popUpTo(route = Auth) { inclusive = true }
                            }
                        })
                    } else {
                        // TODO ovdje nesto
                    }

                }
                composable<Register> {
                    RegisterScreen(userViewModel, onLoginNav = {
                        navController.navigate(route = Login)
                    }, onRegisterSuccess = {
                        navController.navigate(route = DashboardNav) {
                            popUpTo(route = Auth) { inclusive = true }
                        }
                    })
                }
            }
            navigation<Main>(startDestination = DashboardNav) {
                composable<DashboardNav>(
                    typeMap = mapOf(
                        typeOf<User>() to CustomNavType.UserType
                    )
                ) { backStackEntry ->
                    val taskViewModel: TaskViewModel = hiltViewModel(backStackEntry)
                    val timerViewModel: TimerViewModel = hiltViewModel(backStackEntry)
                    val examViewModel: ExamViewModel = hiltViewModel(backStackEntry)
//                    might need something for study activity thing
                    DashboardScreen(loggedUser, userViewModel, taskViewModel, timerViewModel, examViewModel, navController, onLogoutSuccess = {})
                }
                composable<TasksNav>(
                    typeMap = mapOf(
                        typeOf<User>() to CustomNavType.UserType
                    )
                ) { backStackEntry ->
                    val taskViewModel: TaskViewModel = hiltViewModel(backStackEntry)
                    val subjectViewModel: SubjectViewModel = hiltViewModel(backStackEntry)
                    TasksScreen(loggedUser, navController, userViewModel, taskViewModel, subjectViewModel , onLogoutSuccess = {
                        navController.navigate(Login) {
                            popUpTo(Main) { inclusive = true }
                        }
                    })
                }
                composable<SubjectsNav>(
                    typeMap = mapOf(
                        typeOf<User>() to CustomNavType.UserType
                    )
                ) { backStackEntry ->
                    val subjectViewModel: SubjectViewModel = hiltViewModel(backStackEntry)
                    val taskViewModel: TaskViewModel = hiltViewModel(backStackEntry)
                    SubjectsScreen(userViewModel, navController, loggedUser, subjectViewModel, taskViewModel, onLogoutSuccess = {
                        navController.navigate(Login) {
                            popUpTo(Main) { inclusive = true }
                        }
                    })
                }
                composable<StudyTimerNav>(
                    typeMap = mapOf(
                        typeOf<User>() to CustomNavType.UserType
                    )
                ) { backStackEntry ->
                    val timerViewModel: TimerViewModel = hiltViewModel(backStackEntry)
                    TimersScreen(loggedUser, userViewModel,navController, timerViewModel, onLogoutSuccess = {
                        navController.navigate(Login) {
                            popUpTo(Main) { inclusive = true }
                        }
                    })
                }
                composable<ScheduleNav>(
                    typeMap = mapOf(
                        typeOf<User>() to CustomNavType.UserType
                    )
                ) { backStackEntry ->
                    val taskViewModel: TaskViewModel = hiltViewModel(backStackEntry)
                    val examViewModel: ExamViewModel = hiltViewModel(backStackEntry)
                    ScheduleScreen(loggedUser, userViewModel,navController, taskViewModel, examViewModel, onLogoutSuccess = {
                        navController.navigate(Login) {
                            popUpTo(Main) { inclusive = true }
                        }
                    })
                }

                composable<ProfileNav>(
                    typeMap = mapOf(
                        typeOf<User>() to CustomNavType.UserType
                    )
                ) {
                    MyProfileScreen(userViewModel = userViewModel, navController = navController)
                }


//                data class Dashboard(val user: User) DONE
//                data class TasksScreen(val user: User) DONE
//                data class Subjects(val user: User) DONE
//                data class StudyTimer(val user: User)
//                data class Schedule(val user: User)
            }
        }
    }
}