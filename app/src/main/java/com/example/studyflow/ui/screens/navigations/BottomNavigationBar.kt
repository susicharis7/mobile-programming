package com.example.studyflow.ui.screens.navigations

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.studyflow.R
import com.example.studyflow.model.User
import com.example.studyflow.ui.theme.TextWhite

@Composable
fun BottomNavigationBar(
    loggedUser: User,
    currentRoute: String?, // added for fixing bottom bar not being updated when swiping back
    onDashboardNav: (User) -> Unit,
    onTimerNav: (User) -> Unit,
    onTasksNav: (User) -> Unit,
    onSubjectsNav: (User) -> Unit,
//    onScheduleNav: (User) -> Unit
) {
    var selectedItem by remember { mutableStateOf("Dashboard") }

    LaunchedEffect(currentRoute) {
        selectedItem = when {
            currentRoute?.contains("Subjects") == true -> "Subjects"
            currentRoute?.contains("Tasks") == true -> "Tasks"
            currentRoute?.contains("Dashboard") == true -> "Dashboard"
            currentRoute?.contains("StudyTimer") == true -> "Study-Timer"
            else -> selectedItem // Keep current if no match
        }
    }

    NavigationBar(containerColor = Color(0xed0C2637)) {
        NavigationBarItem(
            icon = {
                Icon(painter = painterResource(R.drawable.subjects), contentDescription = "Subjects", tint = TextWhite)
            },
            label = { Text("Subjects") },
            selected = selectedItem == "Subjects",
            onClick = {
                if (selectedItem != "Subjects") {
                    selectedItem = "Subjects"
                    onSubjectsNav(loggedUser)
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(painter = painterResource(R.drawable.tasks), contentDescription = "Tasks", tint = TextWhite)
            },
            label = { Text("Tasks") },
            selected = selectedItem == "Tasks",
            onClick = {
                if (selectedItem != "Tasks") {
                    selectedItem = "Tasks"
                    onTasksNav(loggedUser)
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(painter = painterResource(R.drawable.dashboard), contentDescription = "Dashboard", tint = TextWhite)
            },
            label = { Text("Dashboard") },
            selected = selectedItem == "Dashboard",
            onClick = {
                if (selectedItem != "Dashboard") {
                    selectedItem = "Dashboard"
                    onDashboardNav(loggedUser)
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(painter = painterResource(R.drawable.study_timer), contentDescription = "Study-Timer", tint = TextWhite)
            },
            label = { Text("Timer") },
            selected = selectedItem == "Study-Timer",
            onClick = {
                if (selectedItem != "Study-Timer") {
                    selectedItem = "Study-Timer"
                    onTimerNav(loggedUser)
                }
            }
        )
//        NavigationBarItem(
//            icon = {
//                Icon(painter = painterResource(R.drawable.schedule), contentDescription = "Schedule", tint = Color.Magenta)
//            },
//            label = { Text("Schedule") },
//            selected = selectedItem == "Schedule",
//            onClick = {
//                if (selectedItem != "Schedule") {
//                    selectedItem = "Schedule"
//                    onScheduleNav(loggedUser)
//                }
//            }
//        )

    }
}