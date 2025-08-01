package com.example.beautyhub.repository

import com.example.beautyhub.model.UserModel
import kotlinx.coroutines.delay

class UserRepositoryImpl : UserRepository {
    private val users = mutableListOf<UserModel>()

    override suspend fun getUsers(): List<UserModel> {
        delay(300)
        return users.toList()
    }

    override suspend fun addUser(user: UserModel) {
        delay(300)
        users.add(user)
    }

    override suspend fun getUserByEmail(email: String): UserModel? {
        delay(200)
        return users.find { it.email == email }
    }

    override suspend fun getUserById(id: String): UserModel? {
        delay(200)
        return users.find { it.id == id }
    }

    override suspend fun updateUser(user: UserModel) {
        delay(300)
        val index = users.indexOfFirst { it.id == user.id }
        if (index != -1) {
            users[index] = user
        }
    }

    override suspend fun deleteUser(userId: String) {
        delay(300)
        users.removeAll { it.id == userId }
    }
}