package com.example.studyflow.repository

import com.example.studyflow.dao.TimersessionDao
import com.example.studyflow.model.Timersession
import javax.inject.Inject

class TimersessionRepositoryImpl @Inject constructor(private val timersessionDao: TimersessionDao): TimersessionRepository {
    override suspend fun insert(entity: Timersession) {
        return timersessionDao.insert(entity)
    }

    override suspend fun getTimersessionsByUser(userId: Int): List<Timersession> {
        return timersessionDao.getTimersessionsByUser(userId)
    }

    override suspend fun delete(entity: Timersession) {
        return timersessionDao.delete(entity)
    }

    override suspend fun update(entity: Timersession) {
        return timersessionDao.update(entity)
    }
}