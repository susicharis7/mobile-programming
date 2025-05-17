package com.example.studyflow.repository

import com.example.studyflow.model.Task

interface TaskRepository : BaseRepository<Task> {
    suspend fun getTasksByUserId(userId: Int): List<Task>
}