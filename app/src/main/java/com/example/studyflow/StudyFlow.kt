package com.example.studyflow

import android.app.Application
import android.util.Log
import com.example.studyflow.database.AppDatabase
import com.example.studyflow.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class StudyFlow : Application() {
    @Inject
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {

            database.userDao().insert(User(username = "Test User 1", email = "user1@test.com", password = "password1"))
            Log.d("DatabaseTest", "Database initialized")
        }
    }
}