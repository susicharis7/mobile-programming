package com.example.studyflow.repository

import com.example.studyflow.dao.SubjectDao
import com.example.studyflow.model.Subject
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(private val subjectDao: SubjectDao): SubjectRepository {
    override suspend fun insert(entity: Subject) : Long {
        return subjectDao.insert(entity)
    }

    override suspend fun getSubjectByUserId(userId: Long): List<Subject> {
        return subjectDao.getSubjectByUserId(userId)
    }

    override suspend fun delete(entity: Subject) {
        return subjectDao.delete(entity)
    }

    override suspend fun update(entity: Subject) {
        return subjectDao.update(entity)
    }
}