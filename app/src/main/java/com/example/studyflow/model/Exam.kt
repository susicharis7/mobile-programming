package com.example.studyflow.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.Date

//@Serializable
@Entity(
    tableName = "exams",
    foreignKeys = [
        ForeignKey(
            entity = Subject::class,
            parentColumns = ["id"],
            childColumns = ["subjectId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["userId"]),
        Index(value = ["subjectId"])
    ]
)
data class Exam(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val subjectId: Long,
    val examDate: Date,
    val userId: Long,
    val isActive: Boolean
)