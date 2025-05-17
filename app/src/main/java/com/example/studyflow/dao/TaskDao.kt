package com.example.studyflow.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.studyflow.model.Task

@Dao
interface TaskDao : BaseDao<Task> {
    @Query("SELECT * FROM tasks WHERE userId = :userId AND isCompleted = 0")
    suspend fun getTasksByUserId(userId: Int): List<Task>

    // get tasks by subject

    // get by priority/deadline (to give list of 3-4 most important tasks

    // task card also contains subject name so we might need some table joins
}