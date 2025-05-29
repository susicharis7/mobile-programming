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
import com.example.studyflow.ui.theme.TextWhite
import com.example.studyflow.ui.theme.UpcomingTasksBackground
import com.example.studyflow.ui.viewmodel.TaskViewModel

@Composable
fun TaskCard(
    title: String,
    category: String,
    date: String,
    priority: String,
    priorityColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackgroundColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(11.dp)) {


            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.tick),
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = title,
                        color = TextWhite,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp
                    )
                }

                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    tint = TextWhite,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 2.dp, top = 2.dp)
                        .size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Nested Row
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = category,
                        color = TextWhite,
                        fontSize = 12.sp,
                        letterSpacing = 1.sp,
                        modifier = Modifier
                            .background(UpcomingTasksBackground, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        tint = Color(0xFFF9E79F),
                        modifier = Modifier.size(14.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = date,
                        color = Color(0xFFF9E79F),
                        fontSize = 12.sp
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color.Transparent, RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Flag,
                        contentDescription = null,
                        tint = priorityColor,
                        modifier = Modifier.size(14.dp)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = priority,
                        color = priorityColor,
                        fontSize = 14.sp,
                        letterSpacing = 0.9.sp
                    )
                }
            }
        }
    }
}


@Composable
fun TasksScreen(loggedUser: User, taskViewModel: TaskViewModel) {
    val tasks by taskViewModel.tasks.collectAsState()

    Scaffold( // Default Stuff
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
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {/* Later Implement What Will It do */},
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

            // TaskCard Implementation

            items(3) { index ->
                TaskCard(
                    title = when (index) {
                        0 -> "Complete Web Programming Milestone"
                        1 -> "Prepare for Statistics Quiz"
                        else -> "Prepare Presentation"
                    },
                    category = when (index) {
                        0 -> "Web Programming"
                        1 -> "Statistics"
                        else -> "Operating Systems"
                    },
                    date = when (index) {
                        0 -> "May 5, 08:00 PM"
                        1 -> "Apr 19, 11:59 PM"
                        else -> "Apr 14, 08:00 PM"
                    },
                    priority = when (index) {
                        0 -> "High"
                        1 -> "Medium"
                        else -> "Low"
                    },
                    priorityColor = when (index) {
                        0 -> Color(0xFFF87171)
                        1 -> Color(0xFFFFCB44)
                        else -> Color(0xFF4ADE80)
                    }
                )
            }



        }
    }
}