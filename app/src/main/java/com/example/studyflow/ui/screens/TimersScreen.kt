package com.example.studyflow.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.studyflow.R
import com.example.studyflow.model.TimerStats
import com.example.studyflow.model.TimerType
import com.example.studyflow.model.User
import com.example.studyflow.ui.screens.navigations.SettingsOverlay
import com.example.studyflow.ui.theme.BackgroundColor
import com.example.studyflow.ui.theme.ButtonFocusSelectedColor
import com.example.studyflow.ui.theme.ButtonUnselectedColor
import com.example.studyflow.ui.theme.ButtonlongBreakColor
import com.example.studyflow.ui.theme.ButtonshortBreakColor
import com.example.studyflow.ui.theme.CardBackgroundColor
import com.example.studyflow.ui.theme.TextWhite
import com.example.studyflow.ui.theme.thirdTimerBgColor
import com.example.studyflow.ui.theme.thirdTimerBgColorv2
import com.example.studyflow.ui.viewmodel.TimerViewModel
import com.example.studyflow.ui.viewmodel.UserViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimersScreen(
    loggedUser: User?,
    userViewModel: UserViewModel,
    navController : NavController,
    timerViewModel: TimerViewModel,
    onLogoutSuccess: () -> Unit
) {
    val timerStats by timerViewModel.timerStats.collectAsState()
    LaunchedEffect(Unit) {
        timerViewModel.loadTimerStats(loggedUser!!.id, TimerType.POMODORO)
    }
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }
    var showFullMusicPage by remember { mutableStateOf(false) }
    var showEnergizingSongs by remember { mutableStateOf(false) }
    var showRelaxSongs by remember { mutableStateOf(false) }
    var showFocusSongs by remember { mutableStateOf(false) }


    var selectedTab = remember { mutableStateOf("Pomodoro") }

    // SettingsOverlay
    val showOverlay = remember { mutableStateOf(false) }
    val loggedUser by userViewModel.loggedUser.collectAsState()

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
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        tint = TextWhite,
                        modifier = Modifier
                            .size(34.dp)
                            .clickable { showOverlay.value = true }

                    )

                    Text(
                        "Study Timer",
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
                // Tab bar (Pomodoro, Timer, Stopwatch)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 0.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    listOf("Pomodoro", "Timer", "Stopwatch").forEach { label ->
                        Text(
                            text = label,
                            fontSize = 18.sp,
                            fontWeight = if (selectedTab.value == label) FontWeight.Bold else FontWeight.Light,
                            color = if (selectedTab.value == label) TextWhite else TextWhite.copy(alpha = 0.4f),
                            modifier = Modifier
                                .clickable { selectedTab.value = label }
                                .background(
                                    //if (selectedTab.value == label) TextWhite.copy(alpha = 0.1f) else BackgroundColor, // mozda ovo da skolim
                                    BackgroundColor,
                                    shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }

                // Show selected timer screen
                when (selectedTab.value) {
                    "Pomodoro" -> PomodoroTimer(loggedUser, timerViewModel)
                    "Timer" -> BasicTimer()
                    "Stopwatch" -> StopwatchTimer()
                }
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
fun PomodoroTimer(loggedUser: User?, timerViewModel: TimerViewModel) {
    var selectedMode by remember { mutableStateOf("Focus") }

    var showEditDialog by remember { mutableStateOf(false) }
    var focusDurationMinutes by remember { mutableStateOf(25) }
    var shortBreakMinutes by remember { mutableStateOf(5) }
    var longBreakMinutes by remember { mutableStateOf(10) }

    val focusDuration = focusDurationMinutes * 60
    val shortBreakDuration = shortBreakMinutes * 60
    val longBreakDuration = longBreakMinutes * 60


    val currentDuration = when (selectedMode) {
        "Focus" -> focusDuration
        "Short Break" -> shortBreakDuration
        "Long Break" -> longBreakDuration
        else -> focusDuration
    }

    var timeLeft by remember(selectedMode) { mutableStateOf(currentDuration) }
    var isRunning by remember { mutableStateOf(false) }
    var hours by remember { mutableStateOf(0) }
    var distractions by remember { mutableStateOf(0) }
    var cycles by remember { mutableStateOf(0) }

    LaunchedEffect(isRunning, selectedMode) {
        while (isRunning && timeLeft > 0) {
            delay(1000L)
            timeLeft--
            if ((currentDuration - timeLeft) % 3600 == 0 && (currentDuration - timeLeft) > 0) {
                hours++
            }
        }
        if (timeLeft == 0) {
            if (selectedMode == "Focus") cycles++
            isRunning = false
            timeLeft = currentDuration
        }
    }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60
    val formattedTime = String.format("%02d:%02d", minutes, seconds)


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBackgroundColor, RoundedCornerShape(12.dp))
                .padding(vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Focus", "Short Break", "Long Break").forEach { mode ->
                Text(
                    text = mode,
                    color = if (selectedMode == mode) TextWhite else TextWhite.copy(alpha = 0.5f),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(
                            if (selectedMode == mode){
                                when (mode) {
                                    "Focus" -> ButtonFocusSelectedColor
                                    "Short Break" -> ButtonshortBreakColor
                                    "Long Break" -> ButtonlongBreakColor
                                    else -> ButtonUnselectedColor
                                }
                            } else ButtonUnselectedColor
                        )
                        .clickable {
                            selectedMode = mode
                            timeLeft = when (mode) {
                                "Focus" -> focusDuration
                                "Short Break" -> shortBreakDuration
                                "Long Break" -> longBreakDuration
                                else -> focusDuration
                            }
                            isRunning = false
                        }
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBackgroundColor, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(24.dp)) {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .border(width = 2.dp, color = TextWhite, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = formattedTime,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextWhite
                        )
                        Text(
                            text = "$selectedMode Time",
                            fontSize = 14.sp,
                            color = TextWhite.copy(alpha = 0.7f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isRunning) {
                        Image(
                            painter = painterResource(id = R.drawable.stop),
                            contentDescription = null,
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .clickable {
                                    timeLeft = currentDuration
                                    isRunning = false
                                    hours = 0
                                    distractions = 0
                                    cycles = 0
                                }
                        )

                        Image(
                            painter = painterResource(id = R.drawable.pause),
                            contentDescription = null,
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .clickable {
                                    isRunning = false
                                    distractions++
                                }
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.play),
                            contentDescription = null,
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .clickable {
                                    isRunning = true
                                }
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = null,
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .clickable {
                                showEditDialog = true
                            }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBackgroundColor, RoundedCornerShape(12.dp))
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem(title = "Cycles", value = cycles.toString(), valueColor = Color(0xFF818CF8))
            StatItem(title = "Hours", value = hours.toString(), valueColor = Color(0xFF4ADE80))
            StatItem(title = "Distractions", value = distractions.toString(), valueColor = Color(0xFF60A5FA))
        }

        Spacer(modifier = Modifier.height(14.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBackgroundColor, RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Text(
                "Pomodoro Technique",
                color = TextWhite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Boost productivity by working in focused 25-minute intervals (Pomodoros), separated by short breaks. After 4 cycles, take a longer break. Distractions are logged when you pause or exit a Pomodoro early.",
                color = TextWhite.copy(alpha = 0.7f),
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }

    if (showEditDialog) {
        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = {
                Text("Edit Timer Durations")
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    DurationInputField("Focus", focusDurationMinutes) {
                        focusDurationMinutes = it
                    }
                    DurationInputField("Short Break", shortBreakMinutes) {
                        shortBreakMinutes = it
                    }
                    DurationInputField("Long Break", longBreakMinutes) {
                        longBreakMinutes = it
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    showEditDialog = false
                    timeLeft = when (selectedMode) {
                        "Focus" -> focusDuration
                        "Short Break" -> shortBreakDuration
                        "Long Break" -> longBreakDuration
                        else -> focusDuration
                    }
                }) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }


}

@Composable
fun DurationInputField(label: String, value: Int, onValueChange: (Int) -> Unit) {
    OutlinedTextField(
        value = value.toString(),
        onValueChange = {
            it.toIntOrNull()?.let { minutes ->
                onValueChange(minutes.coerceIn(1, 180)) // max 3 hours
            }
        },
        label = { Text("$label (min)") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}


// For PomodoroTimer()
@Composable
fun StatItem(title: String, value: String, valueColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.widthIn(min = 120.dp)) {
        Text(
            text = value,
            color = valueColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
        Text(
            text = title,
            color = TextWhite,
            fontSize = 10.sp
        )
    }
}


@Composable
fun BasicTimer() {
    var durationInSeconds by remember { mutableStateOf(60 * 60) }
    var timeLeft by remember { mutableStateOf(durationInSeconds) }
    var isRunning by remember { mutableStateOf(false) }
    var completedTimers by remember { mutableStateOf(0) }
    var distractions by remember { mutableStateOf(0) }
    var hours by remember { mutableStateOf(0) }
    var showEditDialog by remember { mutableStateOf(false) }


    LaunchedEffect(isRunning) {
        while (isRunning && timeLeft > 0) {
            delay(1000L)
            timeLeft--
            val totalElapsed = durationInSeconds - timeLeft
            hours = totalElapsed / 3600
        }

        if (timeLeft == 0 && durationInSeconds > 0) {
            completedTimers++
        }
    }

    // Updated - now we have "hours" part
    val hoursPart = timeLeft / 3600
    val minutesPart = (timeLeft % 3600) / 60
    val secondsPart = timeLeft % 60
    val formattedTime = String.format("%d:%02d:%02d", hoursPart, minutesPart, secondsPart)


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        // Timer presets
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBackgroundColor, RoundedCornerShape(12.dp))
                .padding(vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf(
                "1 hour" to {
                    timeLeft = 60 * 60
                    durationInSeconds = timeLeft
                },
                "+10 minutes" to {
                    timeLeft += 10 * 60
                    durationInSeconds = timeLeft
                },
                "-5 minutes" to {
                    timeLeft = maxOf(0, durationInSeconds - 5 * 60)
                    durationInSeconds = timeLeft
                }
            ).forEach { (label, action) ->
                Text(
                    text = label,
                    color = TextWhite,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (label == "1 hour") ButtonFocusSelectedColor else ButtonUnselectedColor)
                        .clickable { action() }
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Timer circle
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBackgroundColor, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(24.dp)) {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .border(width = 2.dp, color = TextWhite, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = formattedTime,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextWhite
                        )
                        Text(
                            text = "Focus Time",
                            fontSize = 14.sp,
                            color = TextWhite.copy(alpha = 0.7f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isRunning) {
                        Image(
                            painter = painterResource(id = R.drawable.stop),
                            contentDescription = null,
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .clickable {
                                    timeLeft = durationInSeconds
                                    isRunning = false
                                    hours = 0
                                    distractions = 0
                                }
                        )

                        Image(
                            painter = painterResource(id = R.drawable.pause),
                            contentDescription = null,
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .clickable {
                                    isRunning = false
                                    distractions++
                                }
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.play),
                            contentDescription = null,
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .clickable { isRunning = true }
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = null,
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .clickable { showEditDialog = true }
                    )
                }
            }
        }
        EditDurationDialog(
            showDialog = showEditDialog,
            currentDuration = durationInSeconds,
            onDismiss = { showEditDialog = false },
            onSave = { newDuration ->
                durationInSeconds = newDuration
                timeLeft = newDuration
                showEditDialog = false
            }
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Stats section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBackgroundColor, RoundedCornerShape(12.dp))
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem(title = "Completed Timers", value = completedTimers.toString(), valueColor = Color(0xFF818CF8))
            StatItem(title = "Hours", value = hours.toString(), valueColor = Color(0xFF4ADE80))
            StatItem(title = "Distractions", value = distractions.toString(), valueColor = Color(0xFF60A5FA))
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Description
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBackgroundColor, RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Text(
                "Focus Timer",
                color = TextWhite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Set a custom duration for uninterrupted work or study. Track your distractions—any pauses or early stops—to identify focus patterns. Ideal for flexible task lengths.",
                color = TextWhite.copy(alpha = 0.7f),
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
fun EditDurationDialog(
    showDialog: Boolean,
    currentDuration: Int,
    onDismiss: () -> Unit,
    onSave: (newDuration: Int) -> Unit
) {
    if (showDialog) {
        var newDurationMinutes by remember { mutableStateOf(currentDuration / 60) }

        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Edit Duration") },
            text = {
                Column {
                    OutlinedTextField(
                        value = newDurationMinutes.toString(),
                        onValueChange = {
                            newDurationMinutes = it.toIntOrNull() ?: newDurationMinutes
                        },
                        label = { Text("Duration (minutes)") },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    onSave(newDurationMinutes * 60)
                }) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        )
    }
}


@Composable
fun StopwatchTimer() {
    var timeInSeconds by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }
    var hours by remember { mutableStateOf(0) }
    var distractions by remember { mutableStateOf(0) }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(1000L)
            timeInSeconds++
            if (timeInSeconds % 3600 == 0) {
                hours++
            }
        }
    }

    val formattedTime = String.format("%02d:%02d", timeInSeconds / 60, timeInSeconds % 60)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        // Timer circle
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBackgroundColor, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(24.dp)) {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .border(width = 2.dp, color = TextWhite, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = formattedTime,
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextWhite
                        )
                        Text(
                            text = "Focus Time",
                            fontSize = 14.sp,
                            color = TextWhite.copy(alpha = 0.7f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isRunning) {
                        Image(
                            painter = painterResource(id = R.drawable.pause),
                            contentDescription = null,
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .clickable {
                                    isRunning = false
                                    distractions++
                                }
                        )

                        Image(
                            painter = painterResource(id = R.drawable.stop),
                            contentDescription = null,
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .clickable {
                                    timeInSeconds = 0
                                    isRunning = false
                                    hours = 0
                                    distractions = 0
                                }
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.play),
                            contentDescription = null,
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .clickable { isRunning = true }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Stats section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBackgroundColor, RoundedCornerShape(12.dp))
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem(title = "Hours", value = hours.toString(), valueColor = Color(0xFF4ADE80))
            StatItem(title = "Distractions", value = distractions.toString(), valueColor = Color(0xFF60A5FA))
        }

        Spacer(modifier = Modifier.height(14.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardBackgroundColor, RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Text(
                "Stopwatch Tracker",
                color = TextWhite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Measure total time spent on tasks with no fixed intervals. Distractions are logged when you stop mid-session. Use this to analyze raw effort or open-ended activities.",
                color = TextWhite.copy(alpha = 0.7f),
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}