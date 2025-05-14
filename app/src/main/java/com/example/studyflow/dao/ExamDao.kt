package com.example.studyflow.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.studyflow.model.Exam

@Dao
interface ExamDao : BaseDao<Exam> {
    @Query("SELECT * FROM exams WHERE userId = :userId AND isActive = 1")
    suspend fun getExamsByUser(userId: Int): List<Exam>

    // get by date
}