package com.example.studyflow.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.studyflow.model.TimerType
import com.example.studyflow.model.Timersession

@Dao
interface TimersessionDao : BaseDao<Timersession> {
    @Query("SELECT * FROM timersessions WHERE userId = :userId")
    suspend fun getTimersessionsByUserId(userId: Long): List<Timersession>

    @Query("SELECT * FROM timersessions WHERE userId = :userId AND timerType = :timerType")
    suspend fun getTimerSessionsByUserIdAndTimerType(userId: Long, timerType: TimerType): List<Timersession>

    // get by timer type
    // get by date or something that orders/groups by date
}