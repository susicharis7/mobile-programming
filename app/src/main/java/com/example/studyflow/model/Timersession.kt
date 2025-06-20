package com.example.studyflow.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.Date

enum class TimerType{
    POMODORO, TIMER, STOPWATCH
}

//@Serializable
@Entity(
    tableName = "timersessions",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["userId"])]
)
data class Timersession(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    val date: Date,
    val timerType: TimerType,
    val sessionTime: Int, // in seconds
    val pauseCount: Int = 0,
    val completions: Int = 0 // this is only for pomodoro and timer, not for stopwatch
)

data class TimerStats(
    val totalCycles: Int,
    val totalHours: Int,
    val distractionCount: Int
)
