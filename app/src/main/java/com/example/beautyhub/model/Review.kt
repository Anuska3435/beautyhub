package com.example.beautyhub.model

data class Review(
    val id: String,
    val productId: Int,
    val userId: String,
    val userName: String,
    val rating: Float,
    val comment: String,
    val createdAt: Long = System.currentTimeMillis(),
    val isVerified: Boolean = false
)