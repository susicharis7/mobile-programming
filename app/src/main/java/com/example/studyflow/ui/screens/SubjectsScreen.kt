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
import com.example.studyflow.model.Subject
import com.example.studyflow.model.User
import com.example.studyflow.ui.screens.navigations.SettingsOverlay
import com.example.studyflow.ui.theme.BackgroundColor
import com.example.studyflow.ui.theme.*
import com.example.studyflow.ui.theme.CardBackgroundColor
import com.example.studyflow.ui.theme.CardForegroundColor
import com.example.studyflow.ui.theme.FirstSubjectProgressColor
import com.example.studyflow.ui.theme.FloatingButtonColor
import com.example.studyflow.ui.theme.ProgressBackgroundColor
import com.example.studyflow.ui.theme.SecondSubjectProgressColor
import com.example.studyflow.ui.theme.TextGray
import com.example.studyflow.ui.theme.TextWhite
import com.example.studyflow.ui.theme.ThirdSubjectProgressColor
import com.example.studyflow.ui.theme.ViewAllColor
import com.example.studyflow.ui.viewmodel.SubjectViewModel
import com.example.studyflow.ui.viewmodel.TaskViewModel
import com.example.studyflow.ui.viewmodel.UserViewModel
import kotlin.random.Random

@Composable
fun SubjectCard(
    subjectName: String,
    completed: Int,
    remaining: Int,
    progressColor: Brush
) {
    val progress = completed.toFloat()/(completed+remaining).toFloat()
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


            } // Box

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
            } // Row

            // Progress Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(13.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ProgressBackgroundColor)// Background bar
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

            // Completed / Remaining
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



        } // Column
    } // Card
} // SubjectCard

@Composable
fun CompletedRemainingCards(
    number: Int,
    label: String
) {
    Column(
        modifier = Modifier
            .width(165.dp)
            .background(CardForegroundColor, RoundedCornerShape(10.dp))
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

@OptIn(ExperimentalMaterial3Api::class)
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
    val subjectTaskCounts by taskViewModel.subjectTaskCounts.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    // SettingsOverlay
    val showOverlay = remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }
    var showFullMusicPage by remember { mutableStateOf(false) }
    var showEnergizingSongs by remember { mutableStateOf(false) }
    var showRelaxSongs by remember { mutableStateOf(false) }
    var showFocusSongs by remember { mutableStateOf(false) }


    if (showDialog) {
        val colors = arrayOf(
            BlueColorStripe,
            PurpleColorStripe,
            YellowColorStripe,
            GreenColorStripe,
            RedColorStripe,
            CyanColorStripe,
            DeepOrangeColorStripe,
            LightOrangeColorStripe,
            PinkColorStripe,
            FuchsiaColorStripe,
            LightBlueColorStripe
        )
        AddSubjectDialog(
            onAdd = { name ->
                subjectViewModel.insertSubject(Subject(name = name, userId = loggedUser!!.id, isActive = true, color = colors[Random.nextInt(colors.size)]))
                // You can call subjectViewModel.addSubject(name) here
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }

    LaunchedEffect(Unit) {
        subjectViewModel.loadSubjects(loggedUser!!.id)
    }

    LaunchedEffect(loggedUser, subjects) {
        if (subjects.isNotEmpty()) {
            taskViewModel.loadSubjectTaskCounts(loggedUser!!.id, subjects.map { it.id })
        }
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
                        modifier = Modifier.size(24.dp).clickable{showSheet=true}
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = FloatingButtonColor,
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
        fun getGradientForColor(color: Color): Brush {
            return when (color) {
                BlueColorStripe -> BlueColorGradient
                PurpleColorStripe -> PurpleColorGradient
                YellowColorStripe -> YellowColorGradient
                GreenColorStripe -> GreenColorGradient
                RedColorStripe -> RedColorGradient
                CyanColorStripe -> CyanColorGradient
                DeepOrangeColorStripe -> DeepOrangeColorGradient
                LightOrangeColorStripe -> LightOrangeColorGradient
                PinkColorStripe -> PinkColorGradient
                FuchsiaColorStripe -> FuchsiaColorGradient
                LightBlueColorStripe -> LightBlueColorGradient
                else -> BlueColorGradient // default fallback
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(horizontal = 14.dp)
                .padding(paddingValues)
        ) {
            subjects.forEach() { subject ->
                item {
                    val counts = subjectTaskCounts[subject.id] ?: TaskViewModel.SubjectTaskCount(0, 0)
                    SubjectCard(
                        subjectName = subject.name,
                        completed = counts.completed,
                        remaining = counts.remaining,
                        progressColor = getGradientForColor(subject.color)
                    )
                }
            }
//            item {
//                SubjectCard(
//                    subjectName = "Mobile Programming",
//                    completed = 3,
//                    remaining = 1,
//                    progressColor = FirstSubjectProgressColor
//                )
//            }
//
//            item {
//                SubjectCard(
//                    subjectName = "Statistics",
//                    completed = 6,
//                    remaining = 2,
//                    progressColor = SecondSubjectProgressColor
//                )
//            }
//
//            item {
//                SubjectCard(
//                    subjectName = "Microprocessors",
//                    completed = 9,
//                    remaining = 3,
//                    progressColor = ThirdSubjectProgressColor
//                )
//            }
//
//            item {
//                SubjectCard(
//                    subjectName = "Data Structures",
//                    completed = 19,
//                    remaining = 12,
//                    progressColor = FirstSubjectProgressColor
//                )
//            }
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

    // to show modal for music
    AudioBottomSheet(
        showSheet = showSheet,
        onDismiss = { showSheet = false },
        sheetState = sheetState,
        onFullMusicClick = { showFullMusicPage = true })
}

@Composable
fun AddSubjectDialog(
    onAdd: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var subjectName by remember { mutableStateOf("") }

    val dialogBg = Color(0xFF234256)
    val borderColor = Color(0xFFB6CBEE)

    val textFieldColors = TextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        cursorColor = Color.White,
        focusedContainerColor = dialogBg,
        unfocusedContainerColor = dialogBg,
        focusedIndicatorColor = borderColor,
        unfocusedIndicatorColor = borderColor
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Subject Name", color = Color.White) },
        text = {
            Column {
                OutlinedTextField(
                    value = subjectName,
                    onValueChange = { subjectName = it },
                    placeholder = { Text("Add Subject Name", color = Color.White.copy(alpha = 0.7f)) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = textFieldColors
                )
            }
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
        containerColor = dialogBg
    )
}



