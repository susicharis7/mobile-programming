package com.example.studyflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.studyflow.R
import com.example.studyflow.model.User
import com.example.studyflow.ui.theme.BackgroundColor
import com.example.studyflow.ui.theme.CardBackgroundColor
import com.example.studyflow.ui.theme.FloatingButtonColor
import com.example.studyflow.ui.theme.TextWhite
import com.example.studyflow.ui.theme.UpcomingTasksBackground
import com.example.studyflow.ui.viewmodel.TaskViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import com.example.studyflow.model.Priority
import com.example.studyflow.ui.screens.navigations.SettingsOverlay
import com.example.studyflow.ui.theme.PriorityHigh
import com.example.studyflow.ui.theme.PriorityLow
import com.example.studyflow.ui.theme.PriorityMedium
import com.example.studyflow.ui.theme.TextGray
import com.example.studyflow.ui.theme.TextGray2
import com.example.studyflow.ui.viewmodel.UserViewModel
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import java.text.SimpleDateFormat
import java.util.Locale



@Composable
fun CompletedUpcomingTaskItem(
    title: String,
    subject: String,
    deadline: String,
    priorityColor: Color,
    priorityLabel: String,
    showOption: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            painter = painterResource(id = R.drawable.tick),
            contentDescription = null,
            tint = PriorityLow,
            modifier = Modifier
                .size(32.dp)
                .padding(end = 6.dp, top = 4.dp)
        )

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    title,
                    color = TextGray2,
                    fontSize = 14.5.sp,
                    fontWeight = FontWeight.Normal,
                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                )

                if (showOption) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                        tint = TextWhite,
                        modifier = Modifier
                            .padding(end = 2.dp, top = 2.dp)
                            .size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .background(UpcomingTasksBackground, shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = subject,
                            color = TextGray.copy(alpha = 0.5f),
                            fontSize = 11.5.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Schedule,
                            contentDescription = null,
                            tint = TextGray2,
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = deadline,
                            color = TextGray2,
                            fontSize = 10.sp
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Flag,
                        contentDescription = null,
                        tint = priorityColor.copy(alpha = 0.5f),
                        modifier = Modifier.size(13.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        priorityLabel,
                        color = priorityColor.copy(alpha = 0.5f),
                        fontSize = 11.sp
                    )
                }
            }
        }


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    loggedUser: User?,
    navController : NavController,
    userViewModel: UserViewModel,
    taskViewModel: TaskViewModel,
    onLogoutSuccess: () -> Unit
) {
    val remainingTasks by taskViewModel.remainingTasks.collectAsState()
    val completedTasks by taskViewModel.completedTasks.collectAsState()
    val completedTaskCount by taskViewModel.completedTaskCount.collectAsState(0)
    var showDialog by remember { mutableStateOf(false) }

    // SettingsOverlay
    val showOverlay = remember { mutableStateOf(false) }
    val loggedUser by userViewModel.loggedUser.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }
    var showFullMusicPage by remember { mutableStateOf(false) }
    var showEnergizingSongs by remember { mutableStateOf(false) }
    var showRelaxSongs by remember { mutableStateOf(false) }
    var showFocusSongs by remember { mutableStateOf(false) }

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

    LaunchedEffect(Unit) {
        taskViewModel.loadTasks(loggedUser!!.id)
        taskViewModel.loadTaskCounts(loggedUser.id)
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp)
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
                        "Tasks",
                        color = TextWhite,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.05.em
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.headphones),
                        contentDescription = null,
                        tint = TextWhite,
                        modifier = Modifier.size(24.dp).clickable{showSheet=true}                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true }, // ✅ ADDED
                containerColor = FloatingButtonColor,
                contentColor = TextWhite,
                shape = RoundedCornerShape(15),
                modifier = Modifier.padding(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Task",
                    modifier = Modifier.size(28.dp)
                )
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

            // Remaining TaskCard Implementation
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    colors = CardDefaults.cardColors(containerColor = CardBackgroundColor),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(11.dp)) {

                        Text(
                            text = "Remaining",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        if (remainingTasks.isNullOrEmpty()) {
                            Text(
                                text = "You have no tasks"
                            )
                        } else {
                            val dateFormat = SimpleDateFormat("MMMM d, hh:mm a", Locale.getDefault())
                            remainingTasks.forEach() { task ->
                                UpcomingTaskItem(
                                    title = task.taskName,
                                    subject = task.subjectName,
                                    deadline = task.deadline.let { dateFormat.format(it) } ?: "No Date",
                                    priorityLabel = task.priority.toString().lowercase().replaceFirstChar { it.uppercase() },
                                    priorityColor = when (task.priority) {
                                        Priority.HIGH -> PriorityHigh
                                        Priority.MEDIUM -> PriorityMedium
                                        Priority.LOW -> PriorityLow
                                    },
                                    showOption = true
                                )
                            }
                        }
                    }
                }
            }

            // Completed TaskCard Implementation
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    colors = CardDefaults.cardColors(containerColor = CardBackgroundColor),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(11.dp)) {

                        Text(
                            text = "Completed ($completedTaskCount)",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        if (completedTasks.isNullOrEmpty()) {
                            Text(
                                text = "You have no completed tasks"
                            )
                        } else {
                            val dateFormat = SimpleDateFormat("MMMM d, hh:mm a", Locale.getDefault())
                            completedTasks.forEach() { task ->
                                CompletedUpcomingTaskItem(
                                    title = task.taskName,
                                    subject = task.subjectName,
                                    deadline = task.deadline.let { dateFormat.format(it) } ?: "No Date",
                                    priorityLabel = task.priority.toString().lowercase().replaceFirstChar { it.uppercase() },
                                    priorityColor = when (task.priority) {
                                        Priority.HIGH -> PriorityHigh
                                        Priority.MEDIUM -> PriorityMedium
                                        Priority.LOW -> PriorityLow
                                    },
                                    showOption = true
                                )
                            }
                        }
                    }
                }
            }
        }

        if (showDialog) {
            AddTaskDialog(
                onAdd = { name, subject, deadline, priority ->
                    showDialog = false
                },
                onDismiss = { showDialog = false }
            )
        }
    }

    // SettingsOverlay.kt
    SettingsOverlay(
        navController = navController,
        visible = showOverlay.value,
        onDismiss = { showOverlay.value = false },
        onAccountClick = { /* ili ostavi prazno */ },
        onLogoutClick = {
            userViewModel.logout()
            onLogoutSuccess()
        }
    )

    // music overlay
    // to show modal for music
    AudioBottomSheet(
        showSheet = showSheet,
        onDismiss = { showSheet = false },
        sheetState = sheetState,
        onFullMusicClick = { showFullMusicPage = true })
}

@Composable
fun AddTaskDialog(
    onAdd: (String, String, String, String) -> Unit,
    onDismiss: () -> Unit
) {
    var taskName by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf("Add Priority") } // Changed initial value
    val priorities = listOf("High", "Medium", "Low")
    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Task", color = Color.White) },
        text = {
            Column {
                OutlinedTextField(
                    value = taskName,
                    onValueChange = { taskName = it },
                    placeholder = { Text("Task Name", color = Color.Black) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFBBCFE9),
                        unfocusedContainerColor = Color(0xFFBBCFE9),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = subject,
                    onValueChange = { subject = it },
                    placeholder = { Text("Add Subject", color = Color.Black) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFBBCFE9),
                        unfocusedContainerColor = Color(0xFFBBCFE9),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = deadline,
                    onValueChange = { deadline = it },
                    placeholder = { Text("Add Deadline", color = Color.Black) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFBBCFE9),
                        unfocusedContainerColor = Color(0xFFBBCFE9),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        cursorColor = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box {
                    OutlinedTextField(
                        value = priority,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = true },
                        placeholder = { Text("Add Priority", color = Color.Black) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFBBCFE9),
                            unfocusedContainerColor = Color(0xFFBBCFE9),
                            focusedTextColor = if (priority == "Add Priority") Color.Black.copy(alpha = 0.5f) else Color.Black,
                            unfocusedTextColor = if (priority == "Add Priority") Color.Black.copy(alpha = 0.5f) else Color.Black,
                            cursorColor = Color.Black
                        )
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        priorities.forEach {
                            DropdownMenuItem(
                                text = { Text(it) },
                                onClick = {
                                    priority = it
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (taskName.isNotBlank() && subject.isNotBlank() && deadline.isNotBlank() && priority != "Add Priority") {
                    onAdd(taskName.trim(), subject.trim(), deadline.trim(), priority)
                }
            }) {
                Text("Add", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Color.White.copy(alpha = 0.7f))
            }
        },
        containerColor = Color(0xFF234256)
    )
}