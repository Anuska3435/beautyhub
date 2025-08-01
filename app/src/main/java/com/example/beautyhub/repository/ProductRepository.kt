package com.example.beautyhub.repository

import com.example.beautyhub.model.ProductModel

interface ProductRepository {
    suspend fun getProducts(): List<ProductModel>
    suspend fun addProduct(product: ProductModel)
    suspend fun updateProduct(product: ProductModel)
    suspend fun deleteProduct(productId: Int)
    suspend fun getProductById(id: Int): ProductModel?
    suspend fun searchProducts(query: String): List<ProductModel>
}
