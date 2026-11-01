package edu.ucb.ds.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Token de colores del Design System. Base de la guía de clase (Primary, Secondary, Background, Error)
 * extendida con los roles que usa el Figma de UCB Encuentra.
 */
@Immutable
data class DsColors(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceContainer: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val outlineVariant: Color,
    val error: Color,
    val onError: Color,
    val success: Color,
    val warning: Color,
    val neutral: Color
)

/** Valores medidos en el Figma de UCB Encuentra. */
val LightDsColors = DsColors(
    primary = Color(0xFF002341),          // headers, botones, FAB
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFFA7CCFD),        // pastilla seleccionada del bottom nav
    onSecondary = Color(0xFF2F5681),      // ícono y texto sobre la pastilla
    tertiary = Color(0xFF3A618C),         // links ("Ver todos", "Contacta a Sistemas UCB")
    onTertiary = Color(0xFFFFFFFF),
    background = Color(0xFFF8F9FD),
    onBackground = Color(0xFF191C1F),
    surface = Color(0xFFFFFFFF),          // tarjeta del login, métricas del dashboard, inputs
    onSurface = Color(0xFF191C1F),        // texto principal
    surfaceContainer = Color(0xFFE0E4EF), // tarjetas de Home y Matching, tiles
    surfaceVariant = Color(0xFFE7E8EC),   // fondo de íconos y miniaturas
    onSurfaceVariant = Color(0xFF43474E), // texto secundario, labels del bottom nav
    outline = Color(0xFF73777E),          // placeholder, fechas
    outlineVariant = Color(0xFFC3C6CE),   // bordes de inputs y tarjetas, divisores
    error = Color(0xFFF84034),            // "Por vencer", punto de alerta
    onError = Color(0xFFFFFFFF),
    success = Color(0xFF2E7D32),          // score >= 80
    warning = Color(0xFFDF6500),          // score 50-79
    neutral = Color(0xFF73777E)           // score < 50
)

/** El Figma no tiene pantallas en modo oscuro: esta paleta es una propuesta a validar con el equipo. */
val DarkDsColors = DsColors(
    primary = Color(0xFFA7CCFD),
    onPrimary = Color(0xFF002341),
    secondary = Color(0xFF2F5681),
    onSecondary = Color(0xFFD3E4FF),
    tertiary = Color(0xFFA3C9FF),
    onTertiary = Color(0xFF00315C),
    background = Color(0xFF111418),
    onBackground = Color(0xFFE1E2E8),
    surface = Color(0xFF1D2024),
    onSurface = Color(0xFFE1E2E8),
    surfaceContainer = Color(0xFF272A2F),
    surfaceVariant = Color(0xFF32353A),
    onSurfaceVariant = Color(0xFFC3C6CE),
    outline = Color(0xFF8D9199),
    outlineVariant = Color(0xFF43474E),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    success = Color(0xFF7BC67F),
    warning = Color(0xFFFFA25C),
    neutral = Color(0xFF8D9199)
)

/** Color del score de coincidencia: verde >= 80, naranja 50-79, gris < 50. */
fun DsColors.scoreColor(score: Int): Color = when {
    score >= 80 -> success
    score >= 50 -> warning
    else -> neutral
}
