package com.example.fintechdashboard.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Brand palette
val Emerald = Color(0xFF00C896)
val EmeraldDim = Color(0xFF00A37A)
val DeepNavy = Color(0xFF0B0F1A)
val CardNavy = Color(0xFF111827)
val CardBorder = Color(0xFF1E2A3A)
val SurfaceNavy = Color(0xFF161D2C)
val TextPrimary = Color(0xFFF0F4FF)
val TextSecondary = Color(0xFF7A8BA6)
val TextMuted = Color(0xFF3D4F66)
val RedAccent = Color(0xFFFF4D6A)
val AmberAccent = Color(0xFFFFB830)
val BlueAccent = Color(0xFF4D9EFF)

private val DarkColorScheme = darkColorScheme(
    primary = Emerald,
    onPrimary = DeepNavy,
    secondary = BlueAccent,
    onSecondary = DeepNavy,
    tertiary = AmberAccent,
    background = DeepNavy,
    onBackground = TextPrimary,
    surface = CardNavy,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceNavy,
    onSurfaceVariant = TextSecondary,
    outline = CardBorder,
    error = RedAccent,
    onError = DeepNavy
)

private val LightColorScheme = lightColorScheme(
    primary = EmeraldDim,
    onPrimary = Color.White,
    secondary = Color(0xFF185FA5),
    onSecondary = Color.White,
    background = Color(0xFFF4F6FA),
    onBackground = Color(0xFF0B0F1A),
    surface = Color.White,
    onSurface = Color(0xFF0B0F1A),
    surfaceVariant = Color(0xFFEDF1F7),
    onSurfaceVariant = Color(0xFF4A5568),
    outline = Color(0xFFDDE3ED)
)

@Composable
fun FintechTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = FintechTypography,
        content = content
    )
}