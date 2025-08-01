package com.example.beautyhub.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.beautyhub.R
import com.example.beautyhub.viewmodel.CartViewModel
import com.example.beautyhub.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel,
    onBackClick: () -> Unit
) {
    val product = productViewModel.selectedProduct
    var quantity by remember { mutableStateOf(1) }
    
    if (product == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Product not found")
        }
        return
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // Product image
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = product.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            
            item {
                // Product info
                Column {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Text(
                        text = product.brand,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Rating and reviews
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(5) { index ->
                            Icon(
                                Icons.Default.Star,
                                contentDescription = "Star",
                                tint = if (index < product.rating.toInt()) 
                                    MaterialTheme.colorScheme.primary 
                                else 
                                    MaterialTheme.colorScheme.outline,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${product.rating} (${product.reviewCount} reviews)",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Price
                    Text(
                        text = "$${product.price}",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            
            item {
                // Description
                Card {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Description",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = product.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
            
            item {
                // Ingredients
                if (product.ingredients.isNotEmpty()) {
                    Card {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Ingredients",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(product.ingredients) { ingredient ->
                                    AssistChip(
                                        onClick = { },
                                        label = { Text(ingredient) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
            
            item {
                // Tags
                if (product.tags.isNotEmpty()) {
                    Card {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Features",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(product.tags) { tag ->
                                    SuggestionChip(
                                        onClick = { },
                                        label = { Text(tag) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
            
            item {
                // Quantity selector and add to cart
                Card {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Quantity",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            // Quantity controls
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(
                                    onClick = { if (quantity > 1) quantity-- },
                                    enabled = quantity > 1
                                ) {
                                    Icon(Icons.Default.Remove, contentDescription = "Decrease")
                                }
                                
                                Text(
                                    text = quantity.toString(),
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )
                                
                                IconButton(
                                    onClick = { quantity++ }
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = "Increase")
                                }
                            }
                            
                            // Stock info
                            Text(
                                text = "${product.stockQuantity} in stock",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Add to cart button
                        Button(
                            onClick = { 
                                cartViewModel.addToCart(product, quantity)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = product.isInStock
                        ) {
                            Icon(
                                Icons.Default.ShoppingCart,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Add to Cart - $${(product.price * quantity)}")
                        }
                    }
                }
            }
        }
    }
}