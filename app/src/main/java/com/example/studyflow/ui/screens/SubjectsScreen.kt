package com.example.studyflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.studyflow.R
import com.example.studyflow.model.User
import com.example.studyflow.ui.screens.navigations.SettingsOverlay
import com.example.studyflow.ui.theme.BackgroundColor
import com.example.studyflow.ui.theme.CardBackgroundColor
import com.example.studyflow.ui.theme.FirstSubjectProgressColor
import com.example.studyflow.ui.theme.ProgressBackgroundColor
import com.example.studyflow.ui.theme.SecondSubjectProgressColor
import com.example.studyflow.ui.theme.TextGray
import com.example.studyflow.ui.theme.TextWhite
import com.example.studyflow.ui.theme.ThirdSubjectProgressColor
import com.example.studyflow.ui.theme.ViewAllColor
import com.example.studyflow.ui.viewmodel.SubjectViewModel
import com.example.studyflow.ui.viewmodel.TaskViewModel
import com.example.studyflow.ui.viewmodel.UserViewModel

@Composable
fun SubjectCard(
    subjectName: String,
    completed: Int,
    remaining: Int,
    progress: Float,
    progressColor: Brush
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = subjectName,
                    color = TextWhite,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    letterSpacing = 1.sp,
                    modifier = Modifier.align(Alignment.CenterStart)
                )

                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    tint = TextWhite,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Progress", color = TextWhite, fontSize = 12.sp
                )
                Text(
                    text = "${(progress * 100).toInt()}%",
                    color = TextWhite,
                    fontSize = 12.sp
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(13.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ProgressBackgroundColor)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress.coerceIn(0f, 1f))
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(10.dp))
                        .background(progressColor)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CompletedRemainingCards(number = completed, label = "Completed")
                CompletedRemainingCards(number = remaining, label = "Remaining")
            }

            Spacer(modifier = Modifier.height(10.dp))

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = TextGray.copy(alpha = 0.6f)
            )

            Text(
                text = "View All",
                color = ViewAllColor,
                fontSize = 13.sp,
                modifier = Modifier.clickable { /* TODO : Navigate */ }
            )
        }
    }
}

@Composable
fun CompletedRemainingCards(
    number: Int,
    label: String
) {
    Column(
        modifier = Modifier
            .width(165.dp)
            .background(ProgressBackgroundColor, RoundedCornerShape(10.dp))
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = number.toString(),
            color = TextWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = label,
            color = TextWhite,
            fontSize = 12.sp
        )
    }
}

@Composable
fun SubjectsScreen(
    userViewModel: UserViewModel,
    navController : NavController,
    loggedUser: User?,
    subjectViewModel: SubjectViewModel,
    taskViewModel: TaskViewModel,
    onLogoutSuccess: () -> Unit
) {
    val subjects by subjectViewModel.subjects.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    // SettingsOverlay
    val showOverlay = remember { mutableStateOf(false) }
    val loggedUser by userViewModel.loggedUser.collectAsState()

    if (showDialog) {
        AddSubjectDialog(
            onAdd = { name ->
                // You can call subjectViewModel.addSubject(name) here
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
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
                Spacer(modifier = Modifier.height(16.dp))

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
                        "Subjects",
                        color = TextWhite,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Normal,
                        letterSpacing = 0.05.em
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.headphones),
                        contentDescription = null,
                        tint = TextWhite,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = Color(0xFF5F93AD),
                contentColor = TextWhite,
                shape = RoundedCornerShape(15),
                modifier = Modifier
                    .padding(horizontal = 0.dp, vertical = 0.dp)
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
            item {
                SubjectCard(
                    subjectName = "Mobile Programming",
                    completed = 3,
                    remaining = 1,
                    progress = 0.75f,
                    progressColor = FirstSubjectProgressColor
                )
            }

            item {
                SubjectCard(
                    subjectName = "Statistics",
                    completed = 6,
                    remaining = 2,
                    progress = 0.75f,
                    progressColor = SecondSubjectProgressColor
                )
            }

            item {
                SubjectCard(
                    subjectName = "Microprocessors",
                    completed = 9,
                    remaining = 3,
                    progress = 0.75f,
                    progressColor = ThirdSubjectProgressColor
                )
            }

            item {
                SubjectCard(
                    subjectName = "Data Structures",
                    completed = 19,
                    remaining = 12,
                    progress = 0.86f,
                    progressColor = FirstSubjectProgressColor
                )
            }
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
}

@Composable
fun AddSubjectDialog(
    onAdd: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var subjectName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Subject Name", color = Color.White) },
        text = {
            TextField(
                value = subjectName,
                onValueChange = { subjectName = it },
                placeholder = { Text("Add Subject Name", color = Color.Black) },
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
        },
        confirmButton = {
            TextButton(onClick = {
                if (subjectName.isNotBlank()) onAdd(subjectName.trim())
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


