package com.example.studyflow

import android.app.Application
import android.util.Log
import androidx.compose.ui.graphics.Color
import com.example.studyflow.database.AppDatabase
import com.example.studyflow.model.Exam
import com.example.studyflow.model.Priority
import com.example.studyflow.model.Subject
import com.example.studyflow.model.Task
import com.example.studyflow.model.User
import com.example.studyflow.ui.theme.*
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.random.Random

@HiltAndroidApp
class StudyFlow : Application() {
    @Inject
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {

//            database.userDao().getUserById(1)
//            Log.d("DatabaseTest", "Database initialized")

            // If your database is empty, uncomment the below code and run the application
            // Make sure to comment it again because it will do all this code again every time you run the app
            // Use email "default@default.com" and password "defaultpassword1" to log into default user

            /**

            // THIS DELETES ALL USERS, DO NOT UNCOMMENT AND RUN IF YOU DON'T WANT ALL USERS DELETED
            //database.userDao().deleteAllUsers()



            val dateFormat = SimpleDateFormat("MM-dd-yyyy-HH:mm", Locale.getDefault())

            val defaultUser = User( username = "default", email = "default@default.com", password = "defaultpassword1")
            database.userDao().insert(defaultUser)
            val defaultUserId = database.userDao().getUserByEmail("default@default.com")?.id

            val colors = arrayOf(
                BlueColorStripe,
                PurpleColorStripe,
                YellowColorStripe,
                GreenColorStripe,
                RedColorStripe,
                CyanColorStripe,
                DeepOrangeColorStripe,
                LightOrangeColorStripe,
                PinkColorStripe,
                FuchsiaColorStripe,
                LightBlueColorStripe
            )

            val subject1 = Subject(name = "Mobile Programming", userId = defaultUserId!!, isActive = true, color = colors[Random.nextInt(colors.size)])
            val subject2 = Subject(name = "Operating Systems", userId = defaultUserId, isActive = true, color = colors[Random.nextInt(colors.size)])
            val subject1Id = database.subjectDao().insert(subject1)
            val subject2Id = database.subjectDao().insert(subject2)

            val task1 = Task(name = "Finish Mobile Programming Project", subjectId = subject1Id, deadline = dateFormat.parse("05-30-2025-20:00")!!, priority = Priority.HIGH, isCompleted = false, userId = defaultUserId)
            val task2 = Task(name = "Study for Final Exam", subjectId = subject1Id, deadline = dateFormat.parse("05-30-2025-18:30")!!, priority = Priority.MEDIUM, isCompleted = false, userId = defaultUserId)
            val task3 = Task(name = "Study for Midterm Exam", subjectId = subject1Id, deadline = dateFormat.parse("03-18-2025-19:25")!!, priority = Priority.HIGH, isCompleted = true, userId = defaultUserId)
            val task4 = Task(name = "OS Project 3", subjectId = subject2Id, deadline = dateFormat.parse("06-02-2025-23:55")!!, priority = Priority.LOW, isCompleted = false, userId = defaultUserId)
            database.taskDao().insert(task1)
            database.taskDao().insert(task2)
            database.taskDao().insert(task3)
            database.taskDao().insert(task4)

            val exam1 = Exam(name = "OS Final Exam", subjectId = subject2Id, examDate = dateFormat.parse("06-21-2025-10:30")!!, userId = defaultUserId, isActive = true)
            val exam2 = Exam(name = "Mobile Programming Midterm", subjectId = subject1Id, examDate = dateFormat.parse("03-19-2025-11:00")!!, userId = defaultUserId, isActive = false)
            val exam3 = Exam(name = "Mobile Programming Final", subjectId = subject1Id, examDate = dateFormat.parse("06-23-2025-9:00")!!, userId = defaultUserId, isActive = true)
            database.examDao().insert(exam1)
            database.examDao().insert(exam2)
            database.examDao().insert(exam3)
            */
        }
    }
}