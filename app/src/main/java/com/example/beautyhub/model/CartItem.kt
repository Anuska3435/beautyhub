package com.example.beautyhub.model

data class CartItem(
    val id: String,
    val product: ProductModel,
    val quantity: Int,
    val addedAt: Long = System.currentTimeMillis()
) {
    val totalPrice: Double
        get() = product.price * quantity
}