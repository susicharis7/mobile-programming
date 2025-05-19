package com.example.studyflow.di

import android.content.Context
import androidx.room.Room
import com.example.studyflow.dao.ExamDao
import com.example.studyflow.dao.SubjectDao
import com.example.studyflow.dao.TaskDao
import com.example.studyflow.dao.TimersessionDao
import com.example.studyflow.dao.UserDao
import com.example.studyflow.database.AppDatabase
import com.example.studyflow.repository.ExamRepository
import com.example.studyflow.repository.ExamRepositoryImpl
import com.example.studyflow.repository.SubjectRepository
import com.example.studyflow.repository.SubjectRepositoryImpl
import com.example.studyflow.repository.UserRepository
import com.example.studyflow.repository.UserRepositoryImpl
import com.example.studyflow.repository.TaskRepository
import com.example.studyflow.repository.TaskRepositoryImpl
import com.example.studyflow.repository.TimersessionRepository
import com.example.studyflow.repository.TimersessionRepositoryImpl
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
            "study_flow_v4.db",
        ).fallbackToDestructiveMigration(true).build()
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

    @Provides
    @Singleton
    fun provideUserRepository(
        userDao: UserDao
    ): UserRepository = UserRepositoryImpl(userDao)

    @Provides
    @Singleton
    fun provideTaskRepository(
        taskDao: TaskDao
    ): TaskRepository = TaskRepositoryImpl(taskDao)

    @Provides
    @Singleton
    fun provideSubjectRepository(
        subjectDao: SubjectDao
    ): SubjectRepository = SubjectRepositoryImpl(subjectDao)

    @Provides
    @Singleton
    fun provideExamRepository(
        examDao: ExamDao
    ): ExamRepository = ExamRepositoryImpl(examDao)

    @Provides
    @Singleton
    fun provideTimersessionRepository(
        timersessionDao: TimersessionDao
    ): TimersessionRepository = TimersessionRepositoryImpl(timersessionDao)

}