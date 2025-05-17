package com.example.studyflow.repository

import com.example.studyflow.model.Exam

interface ExamRepository : BaseRepository<Exam> {
    suspend fun getExamsByUserId(userId: Int): List<Exam>
}