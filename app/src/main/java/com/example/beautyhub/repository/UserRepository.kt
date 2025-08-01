package com.example.beautyhub.repository

import com.example.beautyhub.model.UserModel

interface UserRepository {
    suspend fun getUsers(): List<UserModel>
    suspend fun addUser(user: UserModel)
    suspend fun getUserByEmail(email: String): UserModel?
    suspend fun getUserById(id: String): UserModel?
    suspend fun updateUser(user: UserModel)
    suspend fun deleteUser(userId: String)
}