package edu.ucb.ds.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Token de colores del Design System: Primary, Secondary, Background, Error (+ sus "on" y surface).
 * success / warning / neutral se usan para el score de coincidencia.
 */
@Immutable
data class DsColors(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val error: Color,
    val onError: Color,
    val success: Color,
    val warning: Color,
    val neutral: Color
)

/** Colores tomados del PDF de pantallas de UCB Encuentra. */
val LightDsColors = DsColors(
    primary = Color(0xFF002341),      // azul marino de headers y botones
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFFA7CCFD),    // azul de selección (pastilla del bottom nav)
    onSecondary = Color(0xFF002341),
    background = Color(0xFFF8F9FD),
    onBackground = Color(0xFF002341),
    surface = Color(0xFFE0E4EF),      // tarjetas y tiles
    onSurface = Color(0xFF002341),
    error = Color(0xFFDC2727),        // "Por vencer"
    onError = Color(0xFFFFFFFF),
    success = Color(0xFF2E7D32),      // score 92%
    warning = Color(0xFFDF6500),      // score 65%
    neutral = Color(0xFF7B8E9E)       // score 30%, botón deshabilitado
)

/** El PDF no tiene pantallas en modo oscuro: esta paleta es una propuesta a validar con el equipo. */
val DarkDsColors = DsColors(
    primary = Color(0xFFA7CCFD),
    onPrimary = Color(0xFF002341),
    secondary = Color(0xFF103D66),
    onSecondary = Color(0xFFA7CCFD),
    background = Color(0xFF0A1622),
    onBackground = Color(0xFFE6EBF2),
    surface = Color(0xFF12243A),
    onSurface = Color(0xFFE6EBF2),
    error = Color(0xFFFF8A80),
    onError = Color(0xFF0A1622),
    success = Color(0xFF7BC67F),
    warning = Color(0xFFFFA25C),
    neutral = Color(0xFF8D9DAC)
)

/** Color del score de coincidencia: verde >= 80, naranja 50-79, gris < 50. */
fun DsColors.scoreColor(score: Int): Color = when {
    score >= 80 -> success
    score >= 50 -> warning
    else -> neutral
}
