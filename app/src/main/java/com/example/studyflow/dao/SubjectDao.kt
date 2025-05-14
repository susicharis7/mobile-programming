package com.example.studyflow.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.studyflow.model.Subject

@Dao
interface SubjectDao : BaseDao<Subject> {
    @Query("SELECT * FROM subjects WHERE userId = :userId AND isActive = 1")
    suspend fun getSubjectByUser(userId: Int): List<Subject>
}