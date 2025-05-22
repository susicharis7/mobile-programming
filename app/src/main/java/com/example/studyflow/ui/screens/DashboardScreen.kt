package com.example.studyflow.ui.screens

// Scrollable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.studyflow.R
import com.example.studyflow.model.User
import com.example.studyflow.ui.theme.AVGStudyTimeGradientColor
import com.example.studyflow.ui.theme.AVGStudyTimeHours
import com.example.studyflow.ui.theme.BackgroundColor
import com.example.studyflow.ui.theme.CardBackgroundColor
import com.example.studyflow.ui.theme.CurrentSessionGradientColor
import com.example.studyflow.ui.theme.CurrentSessionHours
import com.example.studyflow.ui.theme.StreakColor
import com.example.studyflow.ui.theme.TaskCompletedGradientColor
import com.example.studyflow.ui.theme.TaskCompletedNumber
import com.example.studyflow.ui.theme.TextGray
import com.example.studyflow.ui.theme.TextWhite
import com.example.studyflow.ui.theme.UpcomingExamCardColor
import com.example.studyflow.ui.theme.UpcomingTasksBackground
import com.example.studyflow.ui.viewmodel.TaskViewModel

@Composable
fun DashboardScreen(loggedUser: User, taskViewModel: TaskViewModel, navController: NavHostController) { // trebace i viewmodele dodati
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
                        modifier = Modifier.size(34.dp)
                    )

                    Text(
                        "Dashboard",
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
        modifier = Modifier.background(BackgroundColor)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(horizontal = 14.dp)
                .padding(paddingValues)
        ) {
//            item {  // Top Bar - Icons, Dashboard
//                Spacer(modifier = Modifier.height(36.67.dp + 34.dp + 46.dp))
//            }

            item { // Second Row, TasksScreen Completed;Current Session
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TasksCompletedCard(modifier = Modifier.weight(1f))
                    CurrentSessionCard(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Row( // Third Row, Average Study Time;Streak
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AverageStudyTimeCard(modifier = Modifier.weight(1f))
                    StreakCard(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            item { // Upcoming TasksScreen Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(CardBackgroundColor, shape = RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Upcoming TasksScreen",
                            color = TextWhite,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        // Pops up TasksScreen in Dashboard ("View All")
                        Text(
                            "View All",
                            color = Color(0xFF818CF8),
                            fontSize = 13.sp,
                            modifier = Modifier.clickable {
                                navController.navigate("tasks") {
                                    popUpTo("dashboard") { inclusive = false }
                                }

                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    UpcomingTaskItem(
                        title = "Complete Web Programming Milestone",
                        category = "Web Programming",
                        time = "May 5, 08:00 PM",
                        priorityColor = Color(0xFFF87171),
                        priorityLabel = "High",
                        iconRes = R.drawable.tick
                    )

                    UpcomingTaskItem(
                        title = "Prepare for Statistics Quiz",
                        category = "Statistics",
                        time = "Apr 19, 11:59 PM",
                        priorityColor = Color(0xFFFFCB44),
                        priorityLabel = "Medium",
                        iconRes = R.drawable.tick
                    )

                    UpcomingTaskItem(
                        title = "Prepare Presentation",
                        category = "Operating Systems",
                        time = "Apr 14, 08:00 PM",
                        priorityColor = Color(0xFF4ADE80),
                        priorityLabel = "Low",
                        iconRes = R.drawable.tick
                    )

                } // Column
            } // item


            item { // Upcoming Exams Section

                Spacer(modifier = Modifier.height(20.dp))


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(CardBackgroundColor, shape = RoundedCornerShape(12.dp))
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp,
                            bottom = 0.dp
                        )
                ) {
                    Text(
                        "Upcoming Exams",
                        color = TextWhite,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(12.dp))


                    UpcomingExamItem(
                        title = "Mobile Programming Exam",
                        date = "Apr 23",
                        time = "09:00 - 11:00",
                        subject = "Mobile Programming",
                        colorStripe = Color(0xFF60A5FA),
                        daysLeft = "13"
                    )

                    UpcomingExamItem(
                        title = "Statistics Exam",
                        date = "Apr 26",
                        time = "11:00 - 15:00",
                        subject = "Probability & Statistics",
                        colorStripe = Color(0xFFBB86FC),
                        daysLeft = "16"
                    )

                    UpcomingExamItem(
                        title = "Microprocessors Exam",
                        date = "Apr 27",
                        time = "10:00 - 12:00",
                        subject = "Microprocessors",
                        colorStripe = Color(0xFFFACC15),
                        daysLeft = "17"
                    )

                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            item { // Study Activity
                StudyActivitySection(
                    studyData = listOf(
                        "30" to "4.5 hours", "31" to "2 hours", "1" to "1 hour", "2" to "2.5 hours", "3" to "4 hours", "4" to "30 min", "5" to "0 hours",
                        "6" to "4.5 hours", "7" to "2 hours", "8" to "1.5 hours", "9" to "6 hours", "10" to "2.5 hours", "11" to "1 hour", "12" to "2 hours",
                        "13" to "45 min", "14" to "0 hours", "15" to "6 hours", "16" to "1.5 hours", "17" to "2 hours", "18" to "5 hours"
                    ),
                    monthLabel = "April 2025",
                    totalHours = "48",
                    daysStudied = "20"
                )
            }




        }
    }
}

@Composable
fun TasksCompletedCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .height(100.dp)
            .background(CardBackgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Text(
            "Tasks Completed",
            color = TextWhite,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Text( // `12/20 tasks`
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = TaskCompletedNumber,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                ) {
                    append("12")
                }
                withStyle(
                    style = SpanStyle(
                        color = TextWhite,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append(" / 20 tasks")
                }
            },
            modifier = Modifier.padding(top = 5.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box( /* Dynamic Progression */
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .background(TaskCompletedGradientColor, shape = RoundedCornerShape(6.dp))
        )
    }
}

@Composable
fun CurrentSessionCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .height(100.dp)
            .background(CardBackgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Text(
            "Current Session",
            color = TextWhite,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = CurrentSessionHours,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                ) {
                    append("1.5 ")
                }
                withStyle(
                    style = SpanStyle(
                        color = TextWhite,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append(" hours today")
                }
            },
            modifier = Modifier.padding(top = 5.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box( /* Dynamic Progression */
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .background(CurrentSessionGradientColor, shape = RoundedCornerShape(6.dp))
        )
    }
}

@Composable
fun AverageStudyTimeCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .height(100.dp)
            .background(CardBackgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Text(
            "Average Study Time",
            color = TextWhite,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = AVGStudyTimeHours,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                ) {
                    append("2.5 ")
                }
                withStyle(
                    style = SpanStyle(
                        color = TextWhite,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append(" / 5 hours today")
                }
            },
            modifier = Modifier.padding(top = 5.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Box( /* Dynamic Progression */
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .background(AVGStudyTimeGradientColor, shape = RoundedCornerShape(12.dp))
        )
    }
}

@Composable
fun StreakCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .height(100.dp)
            .background(CardBackgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Text(
            "Streak",
            color = TextWhite,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )

        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = StreakColor,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                ) {
                    append("7 ")
                }
                withStyle(
                    style = SpanStyle(
                        color = TextWhite,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append(" days")
                }
            },
            modifier = Modifier.padding(top = 5.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        /*
        Box(
            // Add later design from figma
        )
        */
    }
}


@Composable
fun UpcomingTaskItem(
    title: String,
    category: String,
    time: String,
    priorityColor: Color,
    priorityLabel: String,
    iconRes: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(32.dp)
                .padding(end = 10.dp, top = 4.dp)
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(
                title,
                color = TextWhite,
                fontSize = 14.5.sp,
                fontWeight = FontWeight.Normal
            )

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
                            text = category,
                            color = TextGray,
                            fontSize = 11.5.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Schedule,
                            contentDescription = null,
                            tint = Color(0xFFF9E79F),
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = time,
                            color = Color(0xFFF9E79F),
                            fontSize = 10.sp
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Flag,
                        contentDescription = null,
                        tint = priorityColor,
                        modifier = Modifier.size(13.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        priorityLabel,
                        color = priorityColor,
                        fontSize = 11.sp
                    )
                }
            }
        }


    }
}


@Composable
fun UpcomingExamItem(
    title: String,
    date: String,
    time: String,
    subject: String,
    colorStripe: Color,
    daysLeft: String
) {
    Box( // Outer Container - Space between Cards
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp)
    ) {
        Box( // Entire Card Container (With Background)
            modifier = Modifier
                .fillMaxWidth()
                .background(UpcomingExamCardColor, shape = RoundedCornerShape(12.dp))
        ) {
            Row( // Horizontal Layout inside Cards
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box( // Vertical Colored Bar (first thing on the left)
                    modifier = Modifier
                        .width(4.dp)
                        .fillMaxHeight()
                        .background(colorStripe, shape = RoundedCornerShape(2.dp))
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column( // Column >> title,date,time,subject
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        title,
                        color = TextWhite,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Row w/ Calendar Icon + Date, Clock Icon + Time
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.schedule),
                            contentDescription = null,
                            tint = TextGray,
                            modifier = Modifier.size(14.dp)
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            date,
                            color = TextGray,
                            fontSize = 12.sp
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Icon(
                            Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = TextGray,
                            modifier = Modifier.size(14.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            time,
                            color = TextGray,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(0.dp))

                    // Row w/ Subject Icon + Subject Name
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.subjects),
                            contentDescription = null,
                            tint = TextGray,
                            modifier = Modifier.size(14.dp)
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Text(
                            subject,
                            color = TextGray,
                            fontSize = 12.sp
                        )
                    }
                }

                Column( // Day Counter (Right Aligned)
                    modifier = Modifier
                        .padding(end = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        daysLeft,
                        color = Color(0xFFF59E0B),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    )

                    Text(
                        "days left",
                        color = TextWhite,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )
                }

            }
        }

    }
}


@Composable
fun StudyActivitySection(
    studyData: List<Pair<String,String>>,
    monthLabel: String = "April 2025",
    totalHours: String = "48",
    daysStudied: String = "18"
) {
    val fullData = buildList {
        addAll(studyData)
        val existingDays = studyData.map { it.first.toIntOrNull() ?: 0 }.toSet()
        for (day in 19..30) {
            if (!existingDays.contains(day)) add(day.toString() to "")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CardBackgroundColor, shape = RoundedCornerShape(12.dp))
            .padding(14.dp)

    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Study Activity",
                color = TextWhite,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Less",
                    color = TextGray,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.width(5.dp))

                repeat(5) {
                    Box(
                        modifier = Modifier
                            .size(11.dp)
                            .padding(horizontal = 1.dp)
                            .background(Color(0xFF4CAF50).copy(alpha = 0.2f + it * 0.15f), shape = RoundedCornerShape(3.dp))
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))
                Text("More", color = TextGray, fontSize = 13.sp)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Month Label
        Text(
            monthLabel,
            color = TextWhite,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Weekday Headers
        val weekdays = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            weekdays.forEach {
                Text(
                    it,
                    color = TextWhite.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Grid (7 columns)
        val columns = 7
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier.height(240.dp),
            userScrollEnabled = false
        ) {
            items(fullData.size) { index ->
                val (day, hours) = fullData[index]
                StudyDayCell(day = day, hours = hours)
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp), // Space above and below line
            thickness = 1.dp, // Line thickness
            color = TextWhite.copy(alpha = 0.2f) // Adjust color and transparency
        )

        // Footer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("$totalHours Total Hours", color = TextWhite)
            Text("$daysStudied Days Studied", color = TextWhite)
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}


@Composable // Composable for a single day cell
fun StudyDayCell(
    day: String,
    hours: String
) {
    val hourValue = hours.split(" ").firstOrNull()?.toFloatOrNull() ?: 0f
    val backgroundColor = when {
        hours.isBlank() -> Color(0xFF0D1B2A) // Full empty for future days
        hourValue == 0f -> Color(0xFF1B263B)
        hourValue < 1 -> Color(0xFF4CAF50).copy(alpha = 0.3f)
        hourValue < 3 -> Color(0xFF4CAF50).copy(alpha = 0.6f)
        else -> Color(0xFF4CAF50)
    }

    Box(
        modifier = Modifier
            .padding(2.dp)
            .aspectRatio(1f)
            .background(backgroundColor, shape = RoundedCornerShape(6.dp)),
        contentAlignment = Alignment.Center
    ) {
        if (hours.isNotBlank()) {
            Box(contentAlignment = Alignment.Center) {
                Column {
                    Text(
                        day,
                        fontSize = 12.sp,
                        modifier = Modifier.offset(y = 2.dp).align(alignment = Alignment.CenterHorizontally) // Manual positioning

                    )
                    Text(
                        hours,
                        fontSize = 7.sp,
                        modifier = Modifier.offset(y = (-4).dp) // Manual positioning
                    )
                }
            }
        }
    }
}
