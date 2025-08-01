package com.example.beautyhub.repository

import com.example.beautyhub.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getCartItems(): Flow<List<CartItem>>
    suspend fun addToCart(cartItem: CartItem)
    suspend fun updateCartItem(cartItem: CartItem)
    suspend fun removeFromCart(itemId: String)
    suspend fun clearCart()
    suspend fun getCartItemCount(): Int
    suspend fun getCartTotal(): Double
}