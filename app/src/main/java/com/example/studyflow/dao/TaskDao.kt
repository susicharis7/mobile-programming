package com.example.studyflow.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.studyflow.model.Task
import com.example.studyflow.model.TaskWithSubject

@Dao
interface TaskDao : BaseDao<Task> {
    @Query("SELECT * FROM tasks WHERE userId = :userId AND isCompleted = 0")
    suspend fun getTasksByUserId(userId: Long): List<Task>

    // get completed task count
    @Query("SELECT COUNT(*) completedCount FROM tasks WHERE userId = :userId AND isCompleted = 1")
    suspend fun getCompletedTaskCountByUserId(userId: Long): Int

    // get total task count
    @Query("SELECT COUNT(*) completedCount FROM tasks WHERE userId = :userId")
    suspend fun getTotalTaskCountByUserId(userId: Long): Int

    // get tasks by subject

    // get by priority/deadline (to give list of 3-4 most important tasks

    // task card also contains subject name so we might need some table joins
    @Query("""
        SELECT tasks.name taskName, subjects.name subjectName, tasks.deadline, tasks.priority 
        FROM tasks
        INNER JOIN subjects ON tasks.subjectId=subjects.id
        WHERE tasks.userId = :userId AND tasks.isCompleted = :isCompleted
        ORDER BY
            tasks.deadline ASC,
            CASE tasks.priority
                WHEN 'HIGH' THEN 1
                WHEN 'MEDIUM' THEN 2
                WHEN 'LOW' THEN 3
            END""")
    suspend fun getTasksWithSubjectByUserIdAndIsCompleted(userId: Long, isCompleted: Boolean): List<TaskWithSubject>
}