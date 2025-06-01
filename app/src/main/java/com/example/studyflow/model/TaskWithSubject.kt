package com.example.studyflow.model

import java.util.Date

data class TaskWithSubject(
    val taskName: String,
    val subjectName: String,
    val deadline: Date,
    val priority: Priority
)
