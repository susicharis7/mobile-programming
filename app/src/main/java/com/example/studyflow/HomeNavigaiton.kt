package com.example.studyflow

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.graphics.Color

import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.studyflow.screens.DashboardScreen
import com.example.studyflow.screens.PomodoroTimer
import com.example.studyflow.screens.Schedule
import com.example.studyflow.screens.Subjects
import com.example.studyflow.screens.Tasks
import com.example.studyflow.ui.theme.BackgroundColor
import com.example.studyflow.ui.theme.CardBackgroundColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeNavigation(mainNavController: NavHostController) {
    val homeNavController = rememberNavController()

//    // Set system status bar color (top notification area)
//    val systemUiController = rememberSystemUiController()
//    SideEffect {
//        systemUiController.setSystemBarsColor(
//            color = BackgroundColor,
//            darkIcons = false
//        )
//    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xed0C2637)
            ) {
                val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            homeNavController.navigate(item.route) {
                                // Navigation handling:
                                popUpTo(homeNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(
                                    if (currentRoute == item.route)
                                        item.selectedIcon
                                    else
                                        item.unselectedIcon
                                ),
                                contentDescription = item.title
                            )
                        },
                        label = { Text(item.title) }
                    )
                }
            }
        }
    ) { padding ->
        // THIS IS WHAT WAS MISSING - The actual content!
        NavHost(
            navController = homeNavController,
            startDestination = "dashboard",
            modifier = Modifier.padding(padding)
        ) {
            composable("dashboard") { DashboardScreen() }
            composable("study_timer") { PomodoroTimer() }
            composable("tasks") { Tasks() }
            composable("subjects") { Subjects() }
            composable("schedule") { Schedule() }
        }
    }
}

val bottomNavItems = listOf(
    NavItem(
        route = "subjects",
        title = "Subjects",
        selectedIcon = R.drawable.subjects_selected,
        unselectedIcon = R.drawable.subjects
    ),
    NavItem(
        route = "tasks",
        title = "Tasks",
        selectedIcon = R.drawable.tasks_selected,
        unselectedIcon = R.drawable.tasks
    ),
    NavItem(
        route = "dashboard",
        title = "Dashboard",
        selectedIcon = R.drawable.dashboard_selected,
        unselectedIcon = R.drawable.dashboard
    ),
    NavItem(
        route = "study_timer",
        title = "Timer",
        selectedIcon = R.drawable.study_timer_selected,
        unselectedIcon = R.drawable.study_timer
    ),
    NavItem(
        route = "schedule",
        title = "Schedule",
        selectedIcon = R.drawable.schedule_selected,
        unselectedIcon = R.drawable.schedule
    )
)

data class NavItem(
    val route: String,
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int
)