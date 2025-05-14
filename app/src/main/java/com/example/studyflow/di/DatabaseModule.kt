package com.example.studyflow.di

import android.content.Context
import androidx.room.Room
import com.example.studyflow.dao.ExamDao
import com.example.studyflow.dao.SubjectDao
import com.example.studyflow.dao.TaskDao
import com.example.studyflow.dao.TimersessionDao
import com.example.studyflow.dao.UserDao
import com.example.studyflow.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "study_flow.db",
        ).build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

    @Provides
    fun provideTaskDao(appDatabase: AppDatabase): TaskDao = appDatabase.taskDao()

    @Provides
    fun provideExamDao(appDatabase: AppDatabase): ExamDao = appDatabase.examDao()

    @Provides
    fun provideSubjectDao(appDatabase: AppDatabase): SubjectDao = appDatabase.subjectDao()

    @Provides
    fun provideTimersessionDao(appDatabase: AppDatabase): TimersessionDao = appDatabase.timersessionDao()
}