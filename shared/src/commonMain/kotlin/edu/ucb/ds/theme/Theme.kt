package edu.ucb.ds.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalDsColors = staticCompositionLocalOf { LightDsColors }
internal val LocalDsTypography = staticCompositionLocalOf { DefaultDsTypography }
internal val LocalDsSpacing = staticCompositionLocalOf { DefaultDsSpacing }
internal val LocalDsShapes = staticCompositionLocalOf { DefaultDsShapes }

/**
 * Forma de acceso a los tokens:
 *
 *     UcbEncuentraTheme.colors.primary
 *     UcbEncuentraTheme.typography.heading
 *     UcbEncuentraTheme.spacing.large
 *     UcbEncuentraTheme.shapes.pill
 */
object UcbEncuentraTheme {
    val colors: DsColors
        @Composable
        @ReadOnlyComposable
        get() = LocalDsColors.current

    val typography: DsTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalDsTypography.current

    val spacing: DsSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalDsSpacing.current

    val shapes: DsShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalDsShapes.current
}

/**
 * Envuelve tu pantalla con este tema:  UcbEncuentraTheme { ...tu UI... }
 *
 * darkTheme sigue al sistema por defecto; la app lo cambia con el ícono luna del header.
 */
@Composable
fun UcbEncuentraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: DsColors = dsColorsFor(darkTheme),
    typography: DsTypography = openSansDsTypography(),
    spacing: DsSpacing = DefaultDsSpacing,
    shapes: DsShapes = DefaultDsShapes,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDsColors provides colors,
        LocalDsTypography provides typography,
        LocalDsSpacing provides spacing,
        LocalDsShapes provides shapes
    ) {
        // Material3 por dentro usa los mismos colores y fuente, así los componentes base no se ven distintos.
        MaterialTheme(
            colorScheme = colors.toMaterialColorScheme(darkTheme),
            typography = typography.toMaterialTypography(),
            content = content
        )
    }
}

/** Elige la paleta según el modo oscuro. */
fun dsColorsFor(darkTheme: Boolean): DsColors = if (darkTheme) DarkDsColors else LightDsColors

private fun DsColors.toMaterialColorScheme(darkTheme: Boolean): ColorScheme =
    if (darkTheme) {
        darkColorScheme(
            primary = primary,
            onPrimary = onPrimary,
            secondary = secondary,
            onSecondary = onSecondary,
            secondaryContainer = secondary,
            onSecondaryContainer = onSecondary,
            tertiary = tertiary,
            onTertiary = onTertiary,
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            surfaceContainer = surfaceContainer,
            surfaceVariant = surfaceVariant,
            onSurfaceVariant = onSurfaceVariant,
            outline = outline,
            outlineVariant = outlineVariant,
            error = error,
            onError = onError
        )
    } else {
        lightColorScheme(
            primary = primary,
            onPrimary = onPrimary,
            secondary = secondary,
            onSecondary = onSecondary,
            secondaryContainer = secondary,
            onSecondaryContainer = onSecondary,
            tertiary = tertiary,
            onTertiary = onTertiary,
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            surfaceContainer = surfaceContainer,
            surfaceVariant = surfaceVariant,
            onSurfaceVariant = onSurfaceVariant,
            outline = outline,
            outlineVariant = outlineVariant,
            error = error,
            onError = onError
        )
    }

private fun DsTypography.toMaterialTypography(): Typography {
    val base = Typography()
    return Typography(
        headlineLarge = base.headlineLarge.merge(heading),
        headlineMedium = base.headlineMedium.merge(heading),
        headlineSmall = heading,
        titleLarge = title,
        titleMedium = bodyLarge.copy(fontWeight = label.fontWeight),
        titleSmall = label,
        bodyLarge = bodyLarge,
        bodyMedium = body,
        bodySmall = base.bodySmall.copy(fontFamily = body.fontFamily),
        labelLarge = label,
        labelMedium = label,
        labelSmall = overline
    )
}
