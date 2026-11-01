package edu.ucb.ds.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Token de tipografía: Heading, Body, Label (guía de clase) + los estilos que usa el Figma.
 */
@Immutable
data class DsTypography(
    val heading: TextStyle,   // 22/28 Bold: "Iniciar Sesión", "¿Perdiste algo?"
    val title: TextStyle,     // 20/28 Bold: título del header, "2 reportes activos", números del score
    val bodyLarge: TextStyle, // 16/24 Regular: ítems de lista ("Mis reportes")
    val body: TextStyle,      // 14/20 Regular: texto general
    val label: TextStyle,     // 12/16 SemiBold: botones, bottom nav, métricas
    val overline: TextStyle   // 12/16 ExtraBold espaciado: "ACTIVIDAD", "ELECTRÓNICOS" (escribir en mayúsculas)
)

fun dsTypography(fontFamily: FontFamily = FontFamily.Default) = DsTypography(
    heading = TextStyle(
        fontFamily = fontFamily,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Bold
    ),
    title = TextStyle(
        fontFamily = fontFamily,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Bold
    ),
    bodyLarge = TextStyle(
        fontFamily = fontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal
    ),
    body = TextStyle(
        fontFamily = fontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal
    ),
    label = TextStyle(
        fontFamily = fontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.SemiBold
    ),
    overline = TextStyle(
        fontFamily = fontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.ExtraBold,
        letterSpacing = 1.2.sp
    )
)

/** Sin fuentes cargadas (previews, tests). */
val DefaultDsTypography = dsTypography()

/** Tipografía con Open Sans, la que usa el tema por defecto. */
@Composable
fun openSansDsTypography(): DsTypography = dsTypography(Fonts.openSans())
