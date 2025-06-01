package com.example.studyflow.repository

interface BaseRepository<T> {
    suspend fun insert(entity: T) : Long
    suspend fun update(entity: T)
    suspend fun delete(entity: T)
}