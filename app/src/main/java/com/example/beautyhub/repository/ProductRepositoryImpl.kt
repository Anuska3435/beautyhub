package com.example.beautyhub.repository

import com.example.beautyhub.model.ProductModel
import kotlinx.coroutines.delay

class ProductRepositoryImpl : ProductRepository {
    private val products = mutableListOf<ProductModel>()

    init {
        // Add some sample beauty products
        products.addAll(listOf(
            ProductModel(
                id = 1, 
                name = "Moisturizing Face Cream", 
                description = "Hydrating cream for all skin types with hyaluronic acid", 
                price = 29.99, 
                imageUrl = "https://example.com/cream.jpg", 
                category = "Skincare", 
                rating = 4.5f, 
                reviewCount = 120,
                brand = "GlowSkin",
                ingredients = listOf("Hyaluronic Acid", "Ceramides", "Vitamin E"),
                skinTypes = listOf("All", "Dry", "Sensitive"),
                stockQuantity = 50,
                tags = listOf("Hydrating", "Anti-aging", "Cruelty-free")
            ),
            ProductModel(
                id = 2, 
                name = "Vitamin C Serum", 
                description = "Brightening serum with 20% vitamin C for radiant skin", 
                price = 39.99, 
                imageUrl = "https://example.com/serum.jpg", 
                category = "Skincare", 
                rating = 4.7f, 
                reviewCount = 85,
                brand = "BrightGlow",
                ingredients = listOf("Vitamin C", "Vitamin E", "Ferulic Acid"),
                skinTypes = listOf("All", "Dull", "Uneven"),
                stockQuantity = 30,
                tags = listOf("Brightening", "Antioxidant", "Vegan")
            ),
            ProductModel(
                id = 3, 
                name = "Matte Lipstick", 
                description = "Long-lasting matte lipstick in classic red", 
                price = 19.99, 
                imageUrl = "https://example.com/lipstick.jpg", 
                category = "Makeup", 
                rating = 4.3f, 
                reviewCount = 200,
                brand = "ColorPop",
                ingredients = listOf("Wax", "Pigments", "Vitamin E"),
                skinTypes = listOf("All"),
                stockQuantity = 100,
                tags = listOf("Long-lasting", "Matte", "Cruelty-free")
            ),
            ProductModel(
                id = 4, 
                name = "Foundation", 
                description = "Full coverage liquid foundation for flawless skin", 
                price = 34.99, 
                imageUrl = "https://example.com/foundation.jpg", 
                category = "Makeup", 
                rating = 4.4f, 
                reviewCount = 150,
                brand = "FlawlessBase",
                ingredients = listOf("Titanium Dioxide", "Iron Oxides", "Hyaluronic Acid"),
                skinTypes = listOf("All", "Oily", "Combination"),
                stockQuantity = 75,
                tags = listOf("Full Coverage", "Long-wearing", "Non-comedogenic")
            ),
            ProductModel(
                id = 5, 
                name = "Hair Mask", 
                description = "Deep conditioning hair mask with argan oil", 
                price = 24.99, 
                imageUrl = "https://example.com/hairmask.jpg", 
                category = "Haircare", 
                rating = 4.6f, 
                reviewCount = 90,
                brand = "SilkyLocks",
                ingredients = listOf("Argan Oil", "Keratin", "Shea Butter"),
                skinTypes = listOf("All"),
                stockQuantity = 40,
                tags = listOf("Moisturizing", "Repair", "Natural")
            )
        ))
    }

    override suspend fun getProducts(): List<ProductModel> {
        delay(500) // Simulate network delay
        return products.toList()
    }

    override suspend fun addProduct(product: ProductModel) {
        delay(300)
        products.add(product)
    }

    override suspend fun updateProduct(product: ProductModel) {
        delay(300)
        val index = products.indexOfFirst { it.id == product.id }
        if (index != -1) {
            products[index] = product
        }
    }

    override suspend fun deleteProduct(productId: Int) {
        delay(300)
        products.removeAll { it.id == productId }
    }

    override suspend fun getProductById(id: Int): ProductModel? {
        delay(200)
        return products.find { it.id == id }
    }

    override suspend fun searchProducts(query: String): List<ProductModel> {
        delay(400)
        return products.filter { 
            it.name.contains(query, ignoreCase = true) || 
            it.description.contains(query, ignoreCase = true) ||
            it.category.contains(query, ignoreCase = true)
        }
    }
}
