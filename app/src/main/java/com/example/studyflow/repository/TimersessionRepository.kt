package com.example.studyflow.repository

import com.example.studyflow.model.Timersession

interface TimersessionRepository : BaseRepository<Timersession> {
    suspend fun getTimersessionsByUserId(userId: Int): List<Timersession>
}