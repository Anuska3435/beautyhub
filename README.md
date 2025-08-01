# BeautyHub - Android Beauty Products App

A modern, feature-rich Android application for beauty products built with **Kotlin**, **Jetpack Compose**, and **MVVM architecture**. This app provides a complete e-commerce experience for beauty enthusiasts.

## 🌟 Features

### 🔐 Authentication
- User registration and login
- Form validation with real-time feedback
- Password visibility toggle
- Error handling and loading states

### 🏠 Home & Navigation
- Beautiful bottom navigation with 5 main sections
- Splash screen with smooth transitions
- Modern Material Design 3 UI
- Rose gold and pink themed color scheme

### 🛍️ Product Management
- Browse products by categories (Skincare, Makeup, Haircare, Fragrance)
- Search functionality with real-time filtering
- Product sorting (by name, price, rating)
- Detailed product information with ingredients and features
- Product ratings and reviews display

### 🛒 Shopping Cart
- Add/remove products from cart
- Quantity management
- Real-time cart total calculation
- Cart badge showing item count
- Empty cart state handling

### 👤 User Profile
- User profile management
- Settings and preferences
- Order history (placeholder)
- Favorites (placeholder)
- Logout functionality

### 🎨 Modern UI/UX
- Material Design 3 components
- Beautiful color scheme with rose gold theme
- Smooth animations and transitions
- Responsive design
- Dark theme support

## 🏗️ Architecture

This app follows the **MVVM (Model-View-ViewModel)** architecture pattern with clear separation of concerns:

### 📁 Project Structure
```
app/src/main/java/com/example/beautyhub/
├── model/              # Data models
│   ├── UserModel.kt
│   ├── ProductModel.kt
│   ├── CartItem.kt
│   ├── Review.kt
│   └── Order.kt
├── repository/         # Data layer
│   ├── UserRepository.kt
│   ├── UserRepositoryImpl.kt
│   ├── ProductRepository.kt
│   ├── ProductRepositoryImpl.kt
│   ├── CartRepository.kt
│   └── CartRepositoryImpl.kt
├── viewmodel/          # Business logic
│   ├── UserViewModel.kt
│   ├── ProductViewModel.kt
│   └── CartViewModel.kt
├── view/              # UI screens
│   ├── SplashScreen.kt
│   ├── LoginScreen.kt
│   ├── RegisterScreen.kt
│   ├── HomeScreen.kt
│   ├── ProductsScreen.kt
│   ├── ProductDetailScreen.kt
│   ├── CartScreen.kt
│   ├── SearchScreen.kt
│   ├── CategoriesScreen.kt
│   └── ProfileScreen.kt
└── ui/theme/          # Theming
    ├── Color.kt
    ├── Theme.kt
    └── Type.kt
```

## 🛠️ Technologies Used

- **Kotlin** - Modern programming language for Android
- **Jetpack Compose** - Modern declarative UI toolkit
- **Material Design 3** - Latest Material Design components
- **Navigation Compose** - Type-safe navigation
- **ViewModel** - Lifecycle-aware data holder
- **StateFlow & Flow** - Reactive data streams
- **Coroutines** - Asynchronous programming

## 🎨 Design System

### Color Palette
- **Primary**: Deep Rose (#D63384)
- **Secondary**: Soft Lavender (#DDA0DD)
- **Accent**: Golden Accent (#FFD700)
- **Background**: Pearl White (#F8F8FF)
- **Surface**: White with subtle shadows

### Typography
- Modern, readable font hierarchy
- Consistent spacing and sizing
- Proper contrast ratios

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or newer
- Android SDK 24 (API level 24) or higher
- Kotlin 2.0.21

### Installation
1. Clone the repository
```bash
git clone https://github.com/yourusername/beautyhub-android.git
```

2. Open the project in Android Studio

3. Sync the project with Gradle files

4. Run the app on an emulator or physical device

## 📱 App Flow

1. **Splash Screen** → Displays app logo for 2 seconds
2. **Authentication** → Login or Register
3. **Home** → Main dashboard with bottom navigation:
   - **Products** → Browse all products with categories and sorting
   - **Categories** → Browse by specific categories
   - **Search** → Search for products with real-time results
   - **Cart** → Manage shopping cart items
   - **Profile** → User settings and account management

## 🔧 Key Components

### ViewModels
- **UserViewModel**: Handles authentication and user data
- **ProductViewModel**: Manages product data, filtering, and search
- **CartViewModel**: Handles shopping cart operations

### Repositories
- **UserRepository**: User data operations
- **ProductRepository**: Product data operations  
- **CartRepository**: Cart data operations with Flow for reactive updates

### Models
- **UserModel**: User information with profile details
- **ProductModel**: Comprehensive product information
- **CartItem**: Shopping cart item with quantity and pricing
- **Order**: Order management (future implementation)

## 🎯 Features in Detail

### Product Management
- **Filtering**: By category (All, Skincare, Makeup, Haircare, Fragrance)
- **Sorting**: By name, price, or rating
- **Search**: Real-time search across product names, descriptions, and brands
- **Details**: Full product information including ingredients, features, and stock

### Shopping Cart
- **Add to Cart**: From product cards or detail screen
- **Quantity Control**: Increase/decrease quantities
- **Remove Items**: Individual item removal or clear all
- **Price Calculation**: Real-time total calculation
- **Empty State**: Beautiful empty cart illustration

### User Experience
- **Loading States**: Smooth loading indicators
- **Error Handling**: User-friendly error messages
- **Form Validation**: Real-time validation feedback
- **Navigation**: Intuitive bottom navigation with badges

## 🔮 Future Enhancements

- [ ] Room database integration for offline storage
- [ ] Firebase integration for backend services
- [ ] Push notifications
- [ ] Order management system
- [ ] Favorites/Wishlist functionality
- [ ] Product reviews and ratings
- [ ] Payment integration
- [ ] User profile image upload
- [ ] Social sharing features

## 🤝 Contributing

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

Created with ❤️ for the beauty community

---

**BeautyHub** - Your gateway to beautiful products! 💄✨