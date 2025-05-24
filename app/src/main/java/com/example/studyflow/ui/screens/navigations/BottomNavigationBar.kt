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
    currentRoute: String?, // added for fixing bottom bar not being updated when swiping back
    onDashboardNav: () -> Unit,
    onTimerNav: () -> Unit,
    onTasksNav: () -> Unit,
    onSubjectsNav: () -> Unit,
    onScheduleNav: () -> Unit
) {
    var selectedItem by remember { mutableStateOf("Dashboard") }

    LaunchedEffect(currentRoute) {
        selectedItem = when {
            currentRoute?.contains("Subjects") == true -> "Subjects"
            currentRoute?.contains("Tasks") == true -> "Tasks"
            currentRoute?.contains("Dashboard") == true -> "Dashboard"
            currentRoute?.contains("StudyTimer") == true -> "Study-Timer"
            currentRoute?.contains("Schedule") == true -> "Schedule"
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
                    onSubjectsNav()
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
                    onTasksNav()
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
                    onDashboardNav()
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
                    onTimerNav()
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(painter = painterResource(R.drawable.schedule), contentDescription = "Schedule", tint = TextWhite)
            },
            label = { Text("Schedule") },
            selected = selectedItem == "Schedule",
            onClick = {
                if (selectedItem != "Schedule") {
                    selectedItem = "Schedule"
                    onScheduleNav()
                }
            }
        )

    }
}