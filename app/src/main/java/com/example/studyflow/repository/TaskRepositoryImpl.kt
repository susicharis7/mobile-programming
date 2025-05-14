package com.example.studyflow.repository

import com.example.studyflow.dao.TaskDao
import com.example.studyflow.model.Task
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao): TaskRepository {
    override suspend fun insert(entity: Task) {
        return taskDao.insert(entity)
    }

    override suspend fun getTasksByUser(userId: Int): List<Task> {
        return taskDao.getTasksByUser(userId)
    }

    override suspend fun delete(entity: Task) {
        return taskDao.delete(entity)
    }

    override suspend fun update(entity: Task) {
        return taskDao.update(entity)
    }
}