package com.example.beautyhub.repository

import com.example.beautyhub.model.CartItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartRepositoryImpl : CartRepository {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())

    override fun getCartItems(): Flow<List<CartItem>> {
        return _cartItems.asStateFlow()
    }

    override suspend fun addToCart(cartItem: CartItem) {
        delay(200)
        val currentItems = _cartItems.value.toMutableList()
        val existingItemIndex = currentItems.indexOfFirst { it.product.id == cartItem.product.id }
        
        if (existingItemIndex != -1) {
            // Update quantity if item already exists
            val existingItem = currentItems[existingItemIndex]
            currentItems[existingItemIndex] = existingItem.copy(quantity = existingItem.quantity + cartItem.quantity)
        } else {
            // Add new item
            currentItems.add(cartItem)
        }
        
        _cartItems.value = currentItems
    }

    override suspend fun updateCartItem(cartItem: CartItem) {
        delay(200)
        val currentItems = _cartItems.value.toMutableList()
        val index = currentItems.indexOfFirst { it.id == cartItem.id }
        if (index != -1) {
            currentItems[index] = cartItem
            _cartItems.value = currentItems
        }
    }

    override suspend fun removeFromCart(itemId: String) {
        delay(200)
        _cartItems.value = _cartItems.value.filter { it.id != itemId }
    }

    override suspend fun clearCart() {
        delay(200)
        _cartItems.value = emptyList()
    }

    override suspend fun getCartItemCount(): Int {
        return _cartItems.value.sumOf { it.quantity }
    }

    override suspend fun getCartTotal(): Double {
        return _cartItems.value.sumOf { it.totalPrice }
    }
}