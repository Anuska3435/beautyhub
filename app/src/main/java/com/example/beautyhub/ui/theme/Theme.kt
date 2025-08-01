package com.example.beautyhub.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = DeepRose,
    onPrimary = Color.White,
    primaryContainer = SoftPink,
    onPrimaryContainer = DarkGray,
    
    secondary = SoftLavender,
    onSecondary = Color.White,
    secondaryContainer = LavenderMist,
    onSecondaryContainer = DarkGray,
    
    tertiary = GoldenAccent,
    onTertiary = DarkGray,
    tertiaryContainer = CreamWhite,
    onTertiaryContainer = DarkGray,
    
    background = PearlWhite,
    onBackground = CharcoalGray,
    
    surface = Color.White,
    onSurface = CharcoalGray,
    surfaceVariant = LightGray,
    onSurfaceVariant = WarmGray,
    
    outline = WarmGray,
    outlineVariant = LightGray,
    
    error = ErrorRed,
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    
    scrim = Color.Black.copy(alpha = 0.32f)
)

private val DarkColorScheme = darkColorScheme(
    primary = RoseGold,
    onPrimary = DarkBackground,
    primaryContainer = DarkRose,
    onPrimaryContainer = Color.White,
    
    secondary = DarkLavender,
    onSecondary = DarkBackground,
    secondaryContainer = Color(0xFF4A4458),
    onSecondaryContainer = Color.White,
    
    tertiary = GoldenAccent,
    onTertiary = DarkBackground,
    tertiaryContainer = Color(0xFF5C5B00),
    onTertiaryContainer = Color.White,
    
    background = DarkBackground,
    onBackground = Color.White,
    
    surface = DarkSurface,
    onSurface = Color.White,
    surfaceVariant = Color(0xFF49454F),
    onSurfaceVariant = Color(0xFFCAC4D0),
    
    outline = Color(0xFF938F99),
    outlineVariant = Color(0xFF49454F),
    
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    
    scrim = Color.Black.copy(alpha = 0.32f)
)

@Composable
fun BeautyHubTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
