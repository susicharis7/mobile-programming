package com.example.studyflow.repository

import com.example.studyflow.dao.TaskDao
import com.example.studyflow.model.Task
import com.example.studyflow.model.TaskWithSubject
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao): TaskRepository {
    override suspend fun insert(entity: Task) : Long {
        return taskDao.insert(entity)
    }

    override suspend fun getTasksByUserId(userId: Long): List<Task> {
        return taskDao.getTasksByUserId(userId)
    }

    override suspend fun getTasksWithSubjectByUserIdAndIsCompleted(
        userId: Long,
        isCompleted: Boolean
    ): List<TaskWithSubject> {
        return taskDao.getTasksWithSubjectByUserIdAndIsCompleted(userId, isCompleted)
    }

    override suspend fun getCompletedTaskCountByUserId(userId: Long): Int {
        return taskDao.getCompletedTaskCountByUserId(userId)
    }

    override suspend fun getTotalTaskCountByUserId(userId: Long): Int {
        return taskDao.getTotalTaskCountByUserId(userId)
    }

    override suspend fun delete(entity: Task) {
        return taskDao.delete(entity)
    }

    override suspend fun update(entity: Task) {
        return taskDao.update(entity)
    }
}