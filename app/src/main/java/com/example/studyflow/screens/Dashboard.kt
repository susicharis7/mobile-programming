package com.example.studyflow.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studyflow.R
import com.example.studyflow.ui.theme.*

// Scrollable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.em

@Composable
fun DashboardScreen() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(14.dp)
    ) {
        item {  // Top Bar - Icons, Dashboard
            Spacer(modifier = Modifier.height(19.dp))

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
            Spacer(modifier = Modifier.height(46.dp))
        }

        item { // Second Row, Tasks Completed;Current Session
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TasksCompletedCard(modifier = Modifier.weight(1f))
                CurrentSessionCard(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(12.dp))
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

        item { // Upcoming Tasks Section
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
                        "Upcoming Tasks",
                        color = TextWhite,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        "View All",
                        color = Color(0xFF818CF8),
                        fontSize = 13.sp
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
                fontSize = 16.sp,
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
                            color = Color.White,
                            fontSize = 13.sp
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
                            fontSize = 12.sp
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Flag,
                        contentDescription = null,
                        tint = priorityColor,
                        modifier = Modifier.size(14.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        priorityLabel,
                        color = priorityColor,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}


