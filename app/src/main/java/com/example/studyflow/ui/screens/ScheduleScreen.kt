package com.example.studyflow.ui.screens

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.studyflow.R
import com.example.studyflow.model.User
import com.example.studyflow.ui.theme.BackgroundColor
import com.example.studyflow.ui.theme.TextWhite
import com.example.studyflow.ui.viewmodel.ExamViewModel
import com.example.studyflow.ui.viewmodel.TaskViewModel
import androidx.compose.material3.Card
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.CardDefaults
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.studyflow.ui.screens.navigations.SettingsOverlay
import com.example.studyflow.ui.viewmodel.UserViewModel
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ModalBottomSheet



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    loggedUser: User?,
    userViewModel: UserViewModel,
    navController : NavController,
    taskViewModel: TaskViewModel,
    examViewModel: ExamViewModel,
    onLogoutSuccess: () -> Unit
) {
    val tasks by taskViewModel.tasks.collectAsState()
    val exams by examViewModel.exams.collectAsState()

    // SettingsOverlay
    val showOverlay = remember { mutableStateOf(false) }
    val loggedUser by userViewModel.loggedUser.collectAsState()

    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }
    var showFullMusicPage by remember { mutableStateOf(false) }
    var showEnergizingSongs by remember { mutableStateOf(false) }
    var showRelaxSongs by remember { mutableStateOf(false) }
    var showFocusSongs by remember { mutableStateOf(false) }


    val taskSections = listOf(
        TaskSection("Jun 1", listOf(
            Task("Mobile Programming Midterm Exam", Color(0xFF6BA2FF)),
            Task("Prepare Presentation", Color(0xFF2E3D4F))
        )),
        TaskSection("Jun 6", listOf(
            Task("Probability & Statistics", Color(0xFFB083FF))
        )),
        TaskSection("Jun 8", listOf(
            Task("Operating Systems", Color(0xFF60C56F)),
            Task("Web Programming", Color(0xFFFF6B6B)),
            Task("Project Submission", Color(0xFF2E3D4F))
        )),
        TaskSection("Jul 1", listOf(
            Task("DS Homework", Color(0xFF2E3D4F)),
            Task("OS Homework", Color(0xFF2E3D4F))
        ))
    )
    when {
        showEnergizingSongs -> {
            EnergizingSongsPage(onBack = { showEnergizingSongs = false })
            return
        }

        showRelaxSongs -> {
            RelaxSongsPage(onBack = { showRelaxSongs = false })
            return
        }

        showFocusSongs -> {
            FocusSongsPage(onBack = { showFocusSongs = false })
            return
        }

        showFullMusicPage -> {
            FullMusicPage(
                onBack = { showFullMusicPage = false },
                onEnergiseClick = {
                    showFullMusicPage = false
                    showEnergizingSongs = true
                },
                onRelaxClick = {
                    showFullMusicPage = false
                    showRelaxSongs = true
                },
                onFocusClick = {
                    showFullMusicPage = false
                    showFocusSongs = true
                }
            )
            return
        }
    }
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(BackgroundColor)
                    .zIndex(1f)
            ) {
                Spacer(modifier= Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = null,
                        tint = TextWhite,
                        modifier = Modifier
                            .size(34.dp)
                            .clickable { showOverlay.value = true }
                    )

                    Text(
                        "Schedule",
                        color = TextWhite,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.05.em
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.headphones),
                        contentDescription = null,
                        tint = TextWhite,
                        modifier = Modifier.size(24.dp).clickable { showSheet =true }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        modifier = Modifier.background(BackgroundColor)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(horizontal = 14.dp)
                .padding(paddingValues)
        ) {
            items(taskSections.size) { index ->
                TaskSectionCard(section = taskSections[index])
            }
        }
    }

    // SettingsOverlay.kt
    SettingsOverlay(
        navController = navController, // ✅ OVO DODAJ
        visible = showOverlay.value,
        onDismiss = { showOverlay.value = false },
        onAccountClick = { /* ili ostavi prazno */ },
        onLogoutClick = {
            userViewModel.logout()
            onLogoutSuccess()
        }
    )
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
            containerColor = Color(0xFF1B263B),
            scrimColor = Color.Black.copy(alpha = 0.5f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                AudioPlayerCard("Rain", R.raw.song)
                AudioPlayerCard("Birds", R.raw.song)
                AudioPlayerCard("Campfire", R.raw.song)

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        showSheet = false
                        showFullMusicPage = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6A5ACD)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    Text("Full Music Version", color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { showSheet = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6A5ACD)
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Close", color = Color.White)
                }
            }
        }
    }
}
data class Task(val title: String, val subjectColor: Color? = null)
data class TaskSection(val date: String, val tasks: List<Task>)

@Composable
fun TaskSectionCard(section: TaskSection) {
    var expanded by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF183040)), // Dashboard-like dark
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = section.date,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 14.dp)
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(end = 14.dp)
                )
            }

            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    section.tasks.forEach { task ->
                        DashboardStyleTaskItem(task)
                    }
                }
            }
        }
    }
}

@Composable
fun DashboardStyleTaskItem(task: Task) {
    Row(
        modifier = Modifier
            .padding(horizontal = 14.dp, vertical = 4.dp) // Reduced padding
            .fillMaxWidth()
            .height(44.dp)
            .background(
                color = Color(0xFF174459),
                shape = RoundedCornerShape(10.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val showTick = task.title in listOf(
            "Prepare Presentation",
            "Project Submission",
            "DS Homework",
            "OS Homework"
        )

        if (showTick) {
            Icon(
                painter = painterResource(id = R.drawable.tick),
                contentDescription = "Tick Icon",
                modifier = Modifier
                    .size(28.dp)
                    .padding(start = 8.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(10.dp))
        } else {
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .fillMaxHeight()
                    .background(
                        color = task.subjectColor ?: Color.Gray,
                        shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                    )
            )
            Spacer(modifier = Modifier.width(10.dp))
        }

        Text(
            text = task.title,
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier.weight(1f) // Make the text take up remaining space
        )
    }
}


