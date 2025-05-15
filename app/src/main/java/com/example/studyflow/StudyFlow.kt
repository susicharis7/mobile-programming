package com.example.studyflow

import android.app.Application
import android.util.Log
import com.example.studyflow.database.AppDatabase
import com.example.studyflow.model.User
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class StudyFlow : Application() {
    @Inject
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {

            database.userDao().getUserById(1)
            Log.d("DatabaseTest", "Database initialized")
        }
    }
}