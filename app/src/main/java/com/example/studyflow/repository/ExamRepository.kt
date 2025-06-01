package com.example.studyflow.repository

import com.example.studyflow.model.Exam
import com.example.studyflow.model.ExamWithSubject

interface ExamRepository : BaseRepository<Exam> {
    suspend fun getExamsByUserId(userId: Long): List<Exam>

    suspend fun getExamsWithSubjectByUserId(userId: Long): List<ExamWithSubject>
}