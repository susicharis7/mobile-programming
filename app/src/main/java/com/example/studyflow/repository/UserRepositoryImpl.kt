package com.example.studyflow.repository

import com.example.studyflow.dao.UserDao
import com.example.studyflow.model.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao): UserRepository {
    override suspend fun insert(entity: User) : Long {
        return userDao.insert(entity)
    }

    override suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    override suspend fun getUserByEmailAndPassword(email: String, password: String): User? {
        return userDao.getUserByEmailAndPassword(email, password)
    }

    override suspend fun getUserById(userId: Long): User? {
        return userDao.getUserById(userId)
    }

    override suspend fun delete(entity: User) {
        return userDao.delete(entity)
    }

    override suspend fun update(entity: User) {
        return userDao.update(entity)
    }
}