package com.example.studyflow.repository

import com.example.studyflow.model.User

interface UserRepository : BaseRepository<User> {
    suspend fun getUserByEmail(email: String): User?

    suspend fun getUserByEmailAndPassword(email: String, password: String): User?

    suspend fun getUserById(userId: Long): User?
}