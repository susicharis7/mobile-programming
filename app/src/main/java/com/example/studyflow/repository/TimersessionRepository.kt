package com.example.studyflow.repository

import com.example.studyflow.model.Timersession

interface TimersessionRepository : BaseRepository<Timersession> {
    suspend fun getTimersessionsByUser(userId: Int): List<Timersession>
}