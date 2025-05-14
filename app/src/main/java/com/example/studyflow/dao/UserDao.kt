package com.example.studyflow.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.studyflow.model.User

@Dao
interface UserDao : BaseDao<User> {
    // each one of these must be in UserRepository

    @Query("SELECT * FROM users WHERE users.email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun getUserByEmailAndPassword(email: String, password: String): User?

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Int): User?
}