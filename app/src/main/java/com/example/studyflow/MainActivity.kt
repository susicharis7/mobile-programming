package com.example.studyflow

import android.annotation.SuppressLint
import android.app.Activity
//import android.graphics.Color
import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import com.example.studyflow.screens.LoginScreen
import com.example.studyflow.screens.RegisterScreen
import com.example.studyflow.ui.theme.StudyFlowTheme
import com.example.studyflow.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            // =============== nije radilo (probao sam prvo onaj dark) ===============
//            statusBarStyle = SystemBarStyle.light(
//                android.graphics.Color.TRANSPARENT,
//                android.graphics.Color.TRANSPARENT
//            )
        )
        setContent {
            StudyFlowTheme {
                setStatusBarColor(color = Color.Red)
                AppNavigation()
            }
        }
    }
}

// =============== isto ne radi ===============
// https://www.youtube.com/watch?v=Ruu44ZUhkBM
@SuppressLint("ComposableNaming")
@Composable
fun setStatusBarColor(color: Color) {
    val view = LocalView.current

    if (!view.isInEditMode) {
        LaunchedEffect(key1 = true) {
            val window = (view.context as Activity).window
            window.statusBarColor = color.toArgb()
        }
    }
}

