package com.example.beautyhub.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.beautyhub.viewmodel.ProductViewModel
import com.example.beautyhub.viewmodel.CartViewModel
import com.example.beautyhub.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    userViewModel: UserViewModel = viewModel(),
    productViewModel: ProductViewModel = viewModel(),
    cartViewModel: CartViewModel = viewModel()
) {
    val bottomNavController = rememberNavController()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "BeautyHub",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    // Search icon
                    IconButton(onClick = { 
                        bottomNavController.navigate("search")
                    }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    
                    // Cart icon with badge
                    BadgedBox(
                        badge = {
                            if (cartViewModel.cartItemCount > 0) {
                                Badge {
                                    Text(cartViewModel.cartItemCount.toString())
                                }
                            }
                        }
                    ) {
                        IconButton(onClick = { 
                            bottomNavController.navigate("cart")
                        }) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(bottomNavController)
        }
    ) { paddingValues ->
        NavHost(
            navController = bottomNavController,
            startDestination = "products",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("products") { 
                ProductsScreen(
                    productViewModel = productViewModel,
                    cartViewModel = cartViewModel,
                    onProductClick = { product ->
                        productViewModel.selectProduct(product)
                        bottomNavController.navigate("product_detail")
                    }
                )
            }
            composable("categories") { 
                CategoriesScreen(
                    productViewModel = productViewModel,
                    onCategoryClick = { category ->
                        productViewModel.filterByCategory(category)
                        bottomNavController.navigate("products")
                    }
                )
            }
            composable("search") { 
                SearchScreen(
                    productViewModel = productViewModel,
                    cartViewModel = cartViewModel,
                    onProductClick = { product ->
                        productViewModel.selectProduct(product)
                        bottomNavController.navigate("product_detail")
                    }
                )
            }
            composable("cart") { 
                CartScreen(
                    cartViewModel = cartViewModel,
                    onCheckout = {
                        // Navigate to checkout
                    }
                )
            }
            composable("profile") { 
                ProfileScreen(
                    userViewModel = userViewModel,
                    onLogout = {
                        userViewModel.logout()
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                )
            }
            composable("product_detail") {
                ProductDetailScreen(
                    productViewModel = productViewModel,
                    cartViewModel = cartViewModel,
                    onBackClick = {
                        bottomNavController.popBackStack()
                    }
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("products", "Products", Icons.Default.Home),
        BottomNavItem("categories", "Categories", Icons.Default.Category),
        BottomNavItem("search", "Search", Icons.Default.Search),
        BottomNavItem("cart", "Cart", Icons.Default.ShoppingCart),
        BottomNavItem("profile", "Profile", Icons.Default.Person)
    )
    
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)