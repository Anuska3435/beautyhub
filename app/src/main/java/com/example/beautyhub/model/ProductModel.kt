package com.example.beautyhub.model

data class ProductModel(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String, // "Skincare", "Makeup", "Haircare", "Fragrance"
    val rating: Float = 0f,
    val reviewCount: Int = 0,
    val brand: String = "",
    val ingredients: List<String> = emptyList(),
    val skinTypes: List<String> = emptyList(), // Which skin types this product is suitable for
    val isInStock: Boolean = true,
    val stockQuantity: Int = 0,
    val tags: List<String> = emptyList() // "Vegan", "Cruelty-free", "Organic", etc.
)
