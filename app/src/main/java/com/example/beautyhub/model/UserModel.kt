package com.example.beautyhub.model

data class UserModel(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val profileImageUrl: String? = null,
    val phoneNumber: String? = null,
    val dateOfBirth: String? = null,
    val skinType: String? = null, // "Oily", "Dry", "Combination", "Sensitive"
    val favoriteProducts: List<Int> = emptyList(),
    val isVerified: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
