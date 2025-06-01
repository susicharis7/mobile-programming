package com.example.studyflow.model

import androidx.compose.ui.graphics.Color
import java.util.Date

data class ExamWithSubject(
    val examName: String,
    val examDate: Date,
    val subjectName: String,
    val colorStripe: Color
)