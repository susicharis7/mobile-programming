package com.example.studyflow.repository

import com.example.studyflow.model.Task

interface TaskRepository : BaseRepository<Task> {
    suspend fun getTasksByUser(userId: Int): List<Task>
}