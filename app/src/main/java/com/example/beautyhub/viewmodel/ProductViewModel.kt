package com.example.beautyhub.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beautyhub.model.ProductModel
import com.example.beautyhub.repository.ProductRepository
import com.example.beautyhub.repository.ProductRepositoryImpl
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val productRepository: ProductRepository = ProductRepositoryImpl()

    var products by mutableStateOf<List<ProductModel>>(emptyList())
        private set

    var filteredProducts by mutableStateOf<List<ProductModel>>(emptyList())
        private set

    var selectedProduct by mutableStateOf<ProductModel?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var searchQuery by mutableStateOf("")
        private set

    var selectedCategory by mutableStateOf("All")
        private set

    var sortBy by mutableStateOf("name") // "name", "price", "rating"
        private set

    var sortDescending by mutableStateOf(false)
        private set

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            
            try {
                products = productRepository.getProducts()
                applyFiltersAndSort()
            } catch (e: Exception) {
                errorMessage = "Failed to load products: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun searchProducts(query: String) {
        searchQuery = query
        viewModelScope.launch {
            if (query.isBlank()) {
                applyFiltersAndSort()
            } else {
                try {
                    val searchResults = productRepository.searchProducts(query)
                    filteredProducts = applySorting(searchResults)
                } catch (e: Exception) {
                    errorMessage = "Search failed: ${e.message}"
                }
            }
        }
    }

    fun filterByCategory(category: String) {
        selectedCategory = category
        applyFiltersAndSort()
    }

    fun sortProducts(sortBy: String, descending: Boolean = false) {
        this.sortBy = sortBy
        this.sortDescending = descending
        applyFiltersAndSort()
    }

    private fun applyFiltersAndSort() {
        var result = products

        // Apply category filter
        if (selectedCategory != "All") {
            result = result.filter { it.category == selectedCategory }
        }

        // Apply search filter
        if (searchQuery.isNotBlank()) {
            result = result.filter { 
                it.name.contains(searchQuery, ignoreCase = true) || 
                it.description.contains(searchQuery, ignoreCase = true) ||
                it.brand.contains(searchQuery, ignoreCase = true)
            }
        }

        filteredProducts = applySorting(result)
    }

    private fun applySorting(productList: List<ProductModel>): List<ProductModel> {
        return when (sortBy) {
            "name" -> if (sortDescending) productList.sortedByDescending { it.name } 
                     else productList.sortedBy { it.name }
            "price" -> if (sortDescending) productList.sortedByDescending { it.price } 
                      else productList.sortedBy { it.price }
            "rating" -> if (sortDescending) productList.sortedByDescending { it.rating } 
                       else productList.sortedBy { it.rating }
            else -> productList
        }
    }

    fun selectProduct(product: ProductModel) {
        selectedProduct = product
    }

    fun getProductById(id: Int) {
        viewModelScope.launch {
            try {
                selectedProduct = productRepository.getProductById(id)
            } catch (e: Exception) {
                errorMessage = "Failed to load product: ${e.message}"
            }
        }
    }

    fun getCategories(): List<String> {
        return listOf("All") + products.map { it.category }.distinct().sorted()
    }

    fun clearError() {
        errorMessage = null
    }
}