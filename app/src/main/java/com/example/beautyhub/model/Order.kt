package com.example.beautyhub.model

data class Order(
    val id: String,
    val userId: String,
    val items: List<CartItem>,
    val totalAmount: Double,
    val status: OrderStatus,
    val shippingAddress: Address,
    val paymentMethod: String,
    val orderDate: Long = System.currentTimeMillis(),
    val estimatedDelivery: Long? = null
)

enum class OrderStatus {
    PENDING,
    CONFIRMED,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED
}

data class Address(
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val country: String
)