package com.example.studyflow.ui.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@Serializable data class DashboardNav(val user: User)
@Serializable data class TasksNav(val user: User)
@Serializable data class SubjectsNav(val user: User)
@Serializable data class StudyTimerNav(val user: User)
@Serializable data class ScheduleNav(val user: User)

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

//    val showBottomBar = navBackStackEntry?.destination?.route?.let { route ->
//        route.split('/')[0] !in listOf(
//            Login::class.qualifiedName,
//            Register::class.qualifiedName
//        )
//    } ?: false
    val showBottomBar = (currentRoute?.split('/')?.get(0)) !in listOf(
        Login::class.qualifiedName,     // or whatever string your Home route serializes to
        Register::class.qualifiedName   // same for Workout
        //same for profile
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                navBackStackEntry?.toRoute<DashboardNav>()?.user?.let { user ->
                    BottomNavigationBar(
                        user,
                        currentRoute,
                        onDashboardNav = { navController.navigate(DashboardNav(user)) {
                            popUpTo(Main) { saveState = true }
                            launchSingleTop = true
                        } },
                        onTasksNav = { navController.navigate(TasksNav(user)) },
                        onTimerNav = { navController.navigate(StudyTimerNav(user)) },
                        onScheduleNav = { navController.navigate(ScheduleNav(user)) },
                        onSubjectsNav = { navController.navigate(SubjectsNav(user)) }
                    )
                }

            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Auth, modifier = Modifier.padding(padding)
        ) {
            navigation<Auth>(startDestination = Login) {
                composable<Login> { backStackEntry ->
                    val authViewModel: UserViewModel = hiltViewModel(backStackEntry)
                    LoginScreen(authViewModel, onRegisterNav = {
                        navController.navigate(route = Register)
                    }, onLoginSuccess = {user: User ->
                        navController.navigate(route = DashboardNav(user = user)) {
                            popUpTo(route = Auth) { inclusive = true }
                        }
                    })
                }
                composable<Register> { backStackEntry ->
                    val authViewModel: UserViewModel = hiltViewModel(backStackEntry)
                    RegisterScreen(authViewModel, onLoginNav = {
                        navController.navigate(route = Login)
                    }, onRegisterSuccess = {user: User ->
                        navController.navigate(route = DashboardNav(user = user)) {
                            popUpTo(route = Auth) { inclusive = true }
                        }
                    })
                }
            }
            navigation<Main>(startDestination = DashboardNav(user = User(0, "", "", ""))) {
                composable<DashboardNav>(
                    typeMap = mapOf(
                        typeOf<User>() to CustomNavType.UserType
                    )
                ) { backStackEntry ->
                    val taskViewModel: TaskViewModel = hiltViewModel(backStackEntry)
//                    val timersViewModel: TimersViewModel = hiltViewModel(backStackEntry) // not sure if we'll need separate for each timer for the timer screens
//                    examsViewModel
//                    might need something for study activity thing
                    val loggedUser = backStackEntry.toRoute<DashboardNav>().user
                    DashboardScreen(loggedUser = loggedUser, taskViewModel, navController) // will have other viewmodels too
                }
                composable<TasksNav>(
                    typeMap = mapOf(
                        typeOf<User>() to CustomNavType.UserType
                    )
                ) { backStackEntry ->
                    val taskViewModel: TaskViewModel = hiltViewModel(backStackEntry)
                    val loggedUser = backStackEntry.toRoute<TasksNav>().user
                    TasksScreen(loggedUser, taskViewModel)
                }
                composable<SubjectsNav>(
                    typeMap = mapOf(
                        typeOf<User>() to CustomNavType.UserType
                    )
                ) { backStackEntry ->
                    val subjectViewModel: SubjectViewModel = hiltViewModel(backStackEntry)
                    val loggedUser = backStackEntry.toRoute<SubjectsNav>().user
                    SubjectsScreen(loggedUser, subjectViewModel)
                }
                composable<StudyTimerNav>(
                    typeMap = mapOf(
                        typeOf<User>() to CustomNavType.UserType
                    )
                ) { backStackEntry ->
                    val timerViewModel: TimerViewModel = hiltViewModel(backStackEntry)
                    val loggedUser = backStackEntry.toRoute<StudyTimerNav>().user
                    TimersScreen(loggedUser, timerViewModel)
                }
                composable<ScheduleNav>(
                    typeMap = mapOf(
                        typeOf<User>() to CustomNavType.UserType
                    )
                ) { backStackEntry ->
                    val taskViewModel: TaskViewModel = hiltViewModel(backStackEntry)
                    val examViewModel: ExamViewModel = hiltViewModel(backStackEntry)
                    val loggedUser = backStackEntry.toRoute<ScheduleNav>().user
                    ScheduleScreen(loggedUser, taskViewModel, examViewModel)
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