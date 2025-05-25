package com.example.studyflow.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.studyflow.dao.ExamDao
import com.example.studyflow.dao.SubjectDao
import com.example.studyflow.dao.TaskDao
import com.example.studyflow.dao.TimersessionDao
import com.example.studyflow.dao.UserDao
import com.example.studyflow.model.Subject
import com.example.studyflow.model.Task
import com.example.studyflow.model.Timersession
import com.example.studyflow.model.Exam
import com.example.studyflow.model.User

@Database(
    entities = [User::class, Subject::class, Task::class, Exam::class, Timersession::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao
    abstract fun subjectDao(): SubjectDao
    abstract fun examDao(): ExamDao
    abstract fun timersessionDao(): TimersessionDao
}