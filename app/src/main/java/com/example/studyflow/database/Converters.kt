package com.example.studyflow.database

import androidx.room.TypeConverter
import com.example.studyflow.model.Priority
import com.example.studyflow.model.TimerType
import java.util.Date

class Converters {
    // for date
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time


    // for priority enum
    @TypeConverter
    fun fromPriority(priority: Priority): String = priority.name

    @TypeConverter
    fun toPriority(priority: String): Priority = Priority.valueOf(priority)


    // for timerType enum
    fun fromTimerType(timerType: TimerType): String = timerType.name

    fun toTimerType(timerType: String): TimerType = TimerType.valueOf(timerType)
}