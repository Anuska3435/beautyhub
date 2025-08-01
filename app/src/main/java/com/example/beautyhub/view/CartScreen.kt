package com.example.beautyhub.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.beautyhub.R
import com.example.beautyhub.model.CartItem
import com.example.beautyhub.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    onCheckout: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shopping Cart") },
                actions = {
                    if (cartViewModel.cartItems.isNotEmpty()) {
                        TextButton(
                            onClick = { cartViewModel.clearCart() }
                        ) {
                            Text("Clear All")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        if (cartViewModel.cartItems.isEmpty()) {
            // Empty cart state
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = "Empty cart",
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Your cart is empty",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Add some products to get started",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Cart items list
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(cartViewModel.cartItems) { cartItem ->
                        CartItemCard(
                            cartItem = cartItem,
                            onQuantityChange = { newQuantity ->
                                cartViewModel.updateCartItemQuantity(cartItem, newQuantity)
                            },
                            onRemove = {
                                cartViewModel.removeFromCart(cartItem.id)
                            }
                        )
                    }
                }
                
                // Cart summary and checkout
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // Order summary
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Items (${cartViewModel.cartItemCount})",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "$${cartViewModel.cartTotal}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Shipping",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Free",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        
                        Divider(modifier = Modifier.padding(vertical = 8.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Total",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "$${cartViewModel.cartTotal}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Checkout button
                        Button(
                            onClick = onCheckout,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Proceed to Checkout")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemCard(
    cartItem: CartItem,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Product image
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = cartItem.product.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            
            // Product details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = cartItem.product.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Text(
                    text = cartItem.product.brand,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "$${cartItem.product.price}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Quantity controls
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = { onQuantityChange(cartItem.quantity - 1) },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Default.Remove,
                            contentDescription = "Decrease quantity",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    
                    Text(
                        text = cartItem.quantity.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    
                    IconButton(
                        onClick = { onQuantityChange(cartItem.quantity + 1) },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Increase quantity",
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
            
            // Remove button and total price
            Column(
                horizontalAlignment = Alignment.End
            ) {
                IconButton(
                    onClick = onRemove
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Remove item",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "$${cartItem.totalPrice}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}