package com.example.studyflow.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.studyflow.ui.theme.BackgroundColor
import com.example.studyflow.ui.theme.TextWhite
import com.example.studyflow.ui.viewmodel.UserViewModel

@Composable
fun MyProfileScreen(
    userViewModel: UserViewModel,
    navController: NavController
) {
    val user by userViewModel.loggedUser.collectAsState()

    Box(
        modifier = Modifier
            .background(BackgroundColor)
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F3A4D)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "My Profile",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 8.dp)
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(bottom = 12.dp),
                        thickness = 1.dp,
                        color = TextWhite.copy(alpha = 0.3f)
                    )

                    Column(modifier = Modifier.padding(bottom = 12.dp)) {
                        Text(
                            text = "USERNAME",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Green
                        )
                        Text(
                            text = user?.username ?: "N/A",
                            fontSize = 16.sp,
                            color = TextWhite
                        )
                    }

                    Column {
                        Text(
                            text = "EMAIL",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Green
                        )
                        Text(
                            text = user?.email ?: "N/A",
                            fontSize = 16.sp,
                            color = TextWhite
                        )
                    }
                }

                Text(
                    text = "Back",
                    fontSize = 14.sp,
                    color = Color(0xFF81D4FA),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .clickable { navController.popBackStack() }
                        .padding(4.dp)
                )
            }
        }
    }
}
