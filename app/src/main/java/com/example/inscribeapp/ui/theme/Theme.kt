package com.example.inscribeapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.example.core_ui.*


private val LightColorPalette = lightColorScheme(
    primary = BrightGreen,
    primaryContainer = DarkGray,
    secondary = Blue,
    background = Color.White,
    onBackground = LightGray,
    surface = Color.White,
    onSurface = LightGray,
    onPrimary = Color.White,
    onSecondary = Color.White,
)

@Composable
fun InscribeAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = LightColorPalette

    CompositionLocalProvider(LocalSpacing provides Dimensions()) {
        MaterialTheme(
            colorScheme = colors,
            content = content
        )
    }
}