package com.example.studyflow.repository

import com.example.studyflow.dao.ExamDao
import com.example.studyflow.model.Exam
import javax.inject.Inject

class ExamRepositoryImpl @Inject constructor(private val examDao: ExamDao): ExamRepository {
    override suspend fun insert(entity: Exam) {
        return examDao.insert(entity)
    }

    override suspend fun getExamsByUserId(userId: Int): List<Exam> {
        return examDao.getExamsByUserId(userId)
    }

    override suspend fun delete(entity: Exam) {
        return examDao.delete(entity)
    }

    override suspend fun update(entity: Exam) {
        return examDao.update(entity)
    }
}