package com.example.studyflow

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.studyflow.screens.DashboardScreen
import com.example.studyflow.screens.PomodoroTimer
import com.example.studyflow.screens.Schedule
import com.example.studyflow.screens.Subjects
import com.example.studyflow.screens.Tasks

@Composable
fun HomeNavigation(mainNavController: NavHostController) {
    val homeNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
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





//    Scaffold(
//        bottomBar = {
//            NavigationBar {
//                val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
//                val currentRoute = navBackStackEntry?.destination?.route
//
//                items.forEach { item ->
//                    BottomNavigationItem(
//                        icon = { Icon(item.icon, contentDescription = item.title) },
//                        label = { Text(item.title) },
//                        selected = currentRoute == item.route,
//                        onClick = {
//                            homeNavController.navigate(item.route) {
//                                popUpTo(homeNavController.graph.findStartDestination().id) {
//                                    saveState = true
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
//                        }
//                    )
//                }
//            }
//        }
//    ) { padding ->
//        NavHost(
//            navController = homeNavController,
//            startDestination = NavItem.Dashboard.route,
//            modifier = Modifier.padding(padding)
//            {
//                composable(NavItem.Dashboard.route) { DashboardScreen() }
//                composable(NavItem.Pomodoro.route) { PomodoroTimer() }
//            }
//    }