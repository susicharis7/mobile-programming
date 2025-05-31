package com.example.studyflow.ui.screens.navigations

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.studyflow.ui.nav.ProfileNav
import com.example.studyflow.ui.theme.TextWhite

@Composable
fun SettingsOverlay(
    navController: NavController,
    visible: Boolean,
    onDismiss: () -> Unit,
    onAccountClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val menuWidth = screenWidth * 0.8f
    val dimWidth = screenWidth * 0.2f

    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(initialOffsetX = { -it }) + fadeIn(),
        exit = slideOutHorizontally(targetOffsetX = { -it }) + fadeOut(),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Dim area
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(dimWidth)
                    .align(Alignment.TopEnd)
                    .background(Color.Black.copy(alpha = 0.4f))
                    .clickable { onDismiss() }
            )

            // Menu
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(menuWidth)
                    .background(Color(0xFF0D2A3C))
                    .padding(20.dp)
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = "StudyFlow",
                    color = TextWhite,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                SettingItemCard("Account", onClick = {
                    onAccountClick()
                    navController.navigate(ProfileNav)
                    onDismiss()
                })

                SettingItemCard("Log out", color = Color(0xFFFF6B6B), onClick = {
                    onLogoutClick()
                    onDismiss()
                })
            }
        }
    }
}

@Composable
fun SettingItemCard(
    label: String,
    color: Color = TextWhite,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F3A4D)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = color,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 16.dp)
        )
    }
}