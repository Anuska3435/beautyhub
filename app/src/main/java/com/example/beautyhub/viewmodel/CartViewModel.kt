package com.example.beautyhub.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beautyhub.model.CartItem
import com.example.beautyhub.model.ProductModel
import com.example.beautyhub.repository.CartRepository
import com.example.beautyhub.repository.CartRepositoryImpl
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID

class CartViewModel : ViewModel() {

    private val cartRepository: CartRepository = CartRepositoryImpl()

    var cartItems by mutableStateOf<List<CartItem>>(emptyList())
        private set

    var cartTotal by mutableStateOf(0.0)
        private set

    var cartItemCount by mutableStateOf(0)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        observeCartItems()
    }

    private fun observeCartItems() {
        viewModelScope.launch {
            cartRepository.getCartItems().collectLatest { items ->
                cartItems = items
                cartTotal = items.sumOf { it.totalPrice }
                cartItemCount = items.sumOf { it.quantity }
            }
        }
    }

    fun addToCart(product: ProductModel, quantity: Int = 1) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                val cartItem = CartItem(
                    id = UUID.randomUUID().toString(),
                    product = product,
                    quantity = quantity
                )
                cartRepository.addToCart(cartItem)
            } catch (e: Exception) {
                errorMessage = "Failed to add item to cart: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun updateCartItemQuantity(cartItem: CartItem, newQuantity: Int) {
        if (newQuantity <= 0) {
            removeFromCart(cartItem.id)
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                val updatedItem = cartItem.copy(quantity = newQuantity)
                cartRepository.updateCartItem(updatedItem)
            } catch (e: Exception) {
                errorMessage = "Failed to update cart item: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun removeFromCart(itemId: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                cartRepository.removeFromCart(itemId)
            } catch (e: Exception) {
                errorMessage = "Failed to remove item from cart: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                cartRepository.clearCart()
            } catch (e: Exception) {
                errorMessage = "Failed to clear cart: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun isProductInCart(productId: Int): Boolean {
        return cartItems.any { it.product.id == productId }
    }

    fun getCartItemForProduct(productId: Int): CartItem? {
        return cartItems.find { it.product.id == productId }
    }

    fun clearError() {
        errorMessage = null
    }
}