package com.example.studyflow.repository

import com.example.studyflow.model.TimerType
import com.example.studyflow.model.Timersession

interface TimersessionRepository : BaseRepository<Timersession> {
    suspend fun getTimersessionsByUserId(userId: Long): List<Timersession>

    suspend fun getTimerSessionsByUserIdAndTimerType(userId: Long, timerType: TimerType): List<Timersession>
}