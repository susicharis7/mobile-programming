package com.example.studyflow.repository

import com.example.studyflow.model.Task
import com.example.studyflow.model.TaskWithSubject

interface TaskRepository : BaseRepository<Task> {
    suspend fun getTasksByUserId(userId: Long): List<Task>

    suspend fun getTasksWithSubjectByUserIdAndIsCompleted(userId: Long, isCompleted: Boolean): List<TaskWithSubject>

    suspend fun getCompletedTaskCountByUserId(userId: Long): Int

    suspend fun getTotalTaskCountByUserId(userId: Long): Int
}