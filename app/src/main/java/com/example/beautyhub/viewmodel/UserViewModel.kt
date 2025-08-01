package com.example.beautyhub.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beautyhub.model.UserModel
import com.example.beautyhub.repository.UserRepository
import com.example.beautyhub.repository.UserRepositoryImpl
import kotlinx.coroutines.launch
import java.util.UUID

class UserViewModel : ViewModel() {

    private val userRepository: UserRepository = UserRepositoryImpl()

    var currentUser by mutableStateOf<UserModel?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isLoggedIn by mutableStateOf(false)
        private set

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                val user = userRepository.getUserByEmail(email)
                if (user?.password == password) {
                    currentUser = user
                    isLoggedIn = true
                    onResult(true)
                } else {
                    errorMessage = "Invalid email or password"
                    onResult(false)
                }
            } catch (e: Exception) {
                errorMessage = "Login failed: ${e.message}"
                onResult(false)
            } finally {
                isLoading = false
            }
        }
    }

    fun register(
        name: String, 
        email: String, 
        password: String, 
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                // Check if user already exists
                val existingUser = userRepository.getUserByEmail(email)
                if (existingUser != null) {
                    errorMessage = "User with this email already exists"
                    onResult(false)
                    return@launch
                }

                val newUser = UserModel(
                    id = UUID.randomUUID().toString(),
                    name = name,
                    email = email,
                    password = password
                )
                
                userRepository.addUser(newUser)
                currentUser = newUser
                isLoggedIn = true
                onResult(true)
            } catch (e: Exception) {
                errorMessage = "Registration failed: ${e.message}"
                onResult(false)
            } finally {
                isLoading = false
            }
        }
    }

    fun logout() {
        currentUser = null
        isLoggedIn = false
        errorMessage = null
    }

    fun updateProfile(updatedUser: UserModel) {
        viewModelScope.launch {
            isLoading = true
            try {
                userRepository.updateUser(updatedUser)
                currentUser = updatedUser
            } catch (e: Exception) {
                errorMessage = "Profile update failed: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun clearError() {
        errorMessage = null
    }
}
