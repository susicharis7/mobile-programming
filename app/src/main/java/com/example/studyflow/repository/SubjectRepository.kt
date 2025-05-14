package com.example.studyflow.repository

import com.example.studyflow.model.Subject

interface SubjectRepository : BaseRepository<Subject> {
    suspend fun getSubjectByUser(userId: Int): List<Subject>
}