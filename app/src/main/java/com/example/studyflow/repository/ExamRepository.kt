package com.example.studyflow.repository

import com.example.studyflow.model.Exam

interface ExamRepository : BaseRepository<Exam> {
    suspend fun getExamsByUser(userId: Int): List<Exam>
}