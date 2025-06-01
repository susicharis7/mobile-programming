package com.example.studyflow.repository

import com.example.studyflow.dao.ExamDao
import com.example.studyflow.model.Exam
import com.example.studyflow.model.ExamWithSubject
import javax.inject.Inject

class ExamRepositoryImpl @Inject constructor(private val examDao: ExamDao): ExamRepository {
    override suspend fun insert(entity: Exam) : Long {
        return examDao.insert(entity)
    }

    override suspend fun getExamsByUserId(userId: Long): List<Exam> {
        return examDao.getExamsByUserId(userId)
    }

    override suspend fun getExamsWithSubjectByUserId(userId: Long): List<ExamWithSubject> {
        return examDao.getExamsWithSubjectByUserId(userId)
    }

    override suspend fun delete(entity: Exam) {
        return examDao.delete(entity)
    }

    override suspend fun update(entity: Exam) {
        return examDao.update(entity)
    }
}