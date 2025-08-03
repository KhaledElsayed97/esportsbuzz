package com.kholiodev.core.ui.theme

import android.os.Build
import android.util.Log
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@VisibleForTesting
val LightDefaultColorScheme = lightColorScheme(
    primary = Purple40,
    onPrimary = Color.White,
    primaryContainer = Purple90,
    onPrimaryContainer = Purple10,
    secondary = Orange40,
    onSecondary = Color.White,
    secondaryContainer = Orange90,
    onSecondaryContainer = Orange10,
    tertiary = Blue40,
    onTertiary = Color.White,
    tertiaryContainer = Blue90,
    onTertiaryContainer = Blue10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = DarkPurpleGray99,
    onBackground = DarkPurpleGray10,
    surface = DarkPurpleGray99,
    onSurface = DarkPurpleGray10,
    surfaceVariant = PurpleGray90,
    onSurfaceVariant = PurpleGray30,
    inverseSurface = DarkPurpleGray20,
    inverseOnSurface = DarkPurpleGray95,
    outline = PurpleGray50,
)

/**
 * Dark default theme color scheme
 */
@VisibleForTesting
val DarkDefaultColorScheme = darkColorScheme(
    primary = Purple80,
    onPrimary = Purple20,
    primaryContainer = Purple30,
    onPrimaryContainer = Purple90,
    secondary = Orange80,
    onSecondary = Orange20,
    secondaryContainer = Orange30,
    onSecondaryContainer = Orange90,
    tertiary = Blue80,
    onTertiary = Blue20,
    tertiaryContainer = Blue30,
    onTertiaryContainer = Blue90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = DarkPurpleGray10,
    onBackground = DarkPurpleGray90,
    surface = DarkPurpleGray10,
    onSurface = DarkPurpleGray90,
    surfaceVariant = PurpleGray30,
    onSurfaceVariant = PurpleGray80,
    inverseSurface = DarkPurpleGray90,
    inverseOnSurface = DarkPurpleGray10,
    outline = PurpleGray60,
)

/**
 * Light Android theme color scheme
 */
@VisibleForTesting
val LightAndroidColorScheme = lightColorScheme(
    primary = Green40,
    onPrimary = Color.White,
    primaryContainer = Green90,
    onPrimaryContainer = Green10,
    secondary = DarkGreen40,
    onSecondary = Color.White,
    secondaryContainer = DarkGreen90,
    onSecondaryContainer = DarkGreen10,
    tertiary = Teal40,
    onTertiary = Color.White,
    tertiaryContainer = Teal90,
    onTertiaryContainer = Teal10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = DarkGreenGray99,
    onBackground = DarkGreenGray10,
    surface = DarkGreenGray99,
    onSurface = DarkGreenGray10,
    surfaceVariant = GreenGray90,
    onSurfaceVariant = GreenGray30,
    inverseSurface = DarkGreenGray20,
    inverseOnSurface = DarkGreenGray95,
    outline = GreenGray50,
)

/**
 * Dark Android theme color scheme
 */
@VisibleForTesting
val DarkAndroidColorScheme = darkColorScheme(
    primary = Green80,
    onPrimary = Green20,
    primaryContainer = Green30,
    onPrimaryContainer = Green90,
    secondary = DarkGreen80,
    onSecondary = DarkGreen20,
    secondaryContainer = DarkGreen30,
    onSecondaryContainer = DarkGreen90,
    tertiary = Teal80,
    onTertiary = Teal20,
    tertiaryContainer = Teal30,
    onTertiaryContainer = Teal90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = DarkGreenGray10,
    onBackground = DarkGreenGray90,
    surface = DarkGreenGray10,
    onSurface = DarkGreenGray90,
    surfaceVariant = GreenGray30,
    onSurfaceVariant = GreenGray80,
    inverseSurface = DarkGreenGray90,
    inverseOnSurface = DarkGreenGray10,
    outline = GreenGray60,
)

/**
 * Black and Red Gaming Theme
 */
@VisibleForTesting
val BlackRedGamingColorScheme = darkColorScheme(
    primary = Color(0xFFFF3D3D),              // Bright red
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF5B0000),     // Still dark, but slightly warmer
    onPrimaryContainer = Color(0xFFFFC1C1),   // Brighter blush

    secondary = Color(0xFFFF6B6B),            // Lighter vibrant red-pink
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF6B1B1B),   // Muted warm red
    onSecondaryContainer = Color(0xFFFFD4D4),

    tertiary = Color(0xFFFF9AA2),             // Warm coral-pink
    onTertiary = Color.Black,
    tertiaryContainer = Color(0xFF652C36),    // Dark muted rose
    onTertiaryContainer = Color(0xFFFFE3E8),

    error = Color(0xFFFF5C5C),                // Bright but not neon red
    onError = Color.Black,
    errorContainer = Color(0xFF8B0000),
    onErrorContainer = Color.White,

    background = Color(0xFF1A1A1A),           // Dark gray instead of black
    onBackground = Color(0xFFF0EAEA),         // Soft off-white with red tint

    surface = Color(0xFF2D2D2D),              // Lighter surface for cards
    onSurface = Color(0xFFF5EDED),            // Even softer for text

    surfaceVariant = Color(0xFF3D2A2A),       // Slightly lighter red-brown
    onSurfaceVariant = Color(0xFFE3BDBD),

    inverseSurface = Color(0xFFF4F0F0),
    inverseOnSurface = Color(0xFF300000),

    outline = Color(0xFFE57373)

)

//val BlackRedGamingColorScheme = darkColorScheme(
//    primary = Color(0xFFFF1744), // Bright red
//    onPrimary = Color.Black,
//    primaryContainer = Color(0xFF8B0000), // Dark red
//    onPrimaryContainer = Color.White,
//    secondary = Color(0xFFFF5252), // Lighter red
//    onSecondary = Color.Black,
//    secondaryContainer = Color(0xFF6A0000), // Very dark red
//    onSecondaryContainer = Color.White,
//    tertiary = Color(0xFFFF8A80), // Light red
//    onTertiary = Color.Black,
//    tertiaryContainer = Color(0xFF4A0000), // Very dark red
//    onTertiaryContainer = Color.White,
//    error = Color(0xFFFF1744), // Bright red for errors
//    onError = Color.Black,
//    errorContainer = Color(0xFF8B0000),
//    onErrorContainer = Color.White,
//    background = Color.Black,
//    onBackground = Color.White,
//    surface = Color(0xFF121212), // Very dark gray, almost black
//    onSurface = Color.White,
//    surfaceVariant = Color(0xFF1E1E1E), // Dark gray
//    onSurfaceVariant = Color(0xFFCCCCCC), // Light gray
//    inverseSurface = Color.White,
//    inverseOnSurface = Color.Black,
//    outline = Color(0xFF424242), // Medium gray
//)

/**
 * Black and Red Gaming gradient colors
 */
val BlackRedGamingGradientColors = GradientColors(
    top = Color(0xFF2A1A1A), // Dark red-gray
    bottom = Color(0xFF1A1A1A), // Dark gray
    container = Color(0xFF1A1A1A), // Dark gray
)

/**
 * Black and Red Gaming background theme
 */
val BlackRedGamingBackgroundTheme = BackgroundTheme(color = Color(0xFF1A1A1A))

/**
 * Light Android gradient colors
 */
val LightAndroidGradientColors = GradientColors(container = DarkGreenGray95)

/**
 * Dark Android gradient colors
 */
val DarkAndroidGradientColors = GradientColors(container = Color.Black)

/**
 * Light Android background theme
 */
val LightAndroidBackgroundTheme = BackgroundTheme(color = DarkGreenGray95)

/**
 * Dark Android background theme
 */
val DarkAndroidBackgroundTheme = BackgroundTheme(color = Color.Black)

/**
 * Now in Android theme.
 *
 * @param darkTheme Whether the theme should use a dark color scheme (follows system by default).
 * @param androidTheme Whether the theme should use the Android theme color scheme instead of the
 *        default theme.
 * @param gamingTheme Whether the theme should use the black and red gaming theme.
 * @param disableDynamicTheming If `true`, disables the use of dynamic theming, even when it is
 *        supported. This parameter has no effect if [androidTheme] is `true`.
 */
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    androidTheme: Boolean = false,
    gamingTheme: Boolean = false,
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit,
) {
    // Color scheme
    val colorScheme = when {
        gamingTheme -> BlackRedGamingColorScheme
        androidTheme -> if (darkTheme) DarkAndroidColorScheme else LightAndroidColorScheme
        !disableDynamicTheming && supportsDynamicTheming() -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        else -> if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme
    }
    // Gradient colors
    val emptyGradientColors = GradientColors(container = colorScheme.surfaceColorAtElevation(2.dp))
    val defaultGradientColors = GradientColors(
        top = colorScheme.inverseOnSurface,
        bottom = colorScheme.primaryContainer,
        container = colorScheme.surface,
    )
    val gradientColors = when {
        gamingTheme -> BlackRedGamingGradientColors
        androidTheme -> if (darkTheme) DarkAndroidGradientColors else LightAndroidGradientColors
        !disableDynamicTheming && supportsDynamicTheming() -> emptyGradientColors
        else -> defaultGradientColors
    }
    // Background theme
    val defaultBackgroundTheme = BackgroundTheme(
        color = colorScheme.surface,
        tonalElevation = 2.dp,
    )
    val backgroundTheme = when {
        gamingTheme -> BlackRedGamingBackgroundTheme
        androidTheme -> if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme
        else -> defaultBackgroundTheme
    }

    // Composition locals
    CompositionLocalProvider(
        LocalGradientColors provides gradientColors,
        LocalBackgroundTheme provides backgroundTheme,
    ) {
        Log.d("typography", PoppinsTypography.toString())
        MaterialTheme(
            colorScheme = colorScheme,
            typography = PoppinsTypography,
            content = content,
        )
    }
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
