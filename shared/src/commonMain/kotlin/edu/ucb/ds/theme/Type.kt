package edu.ucb.ds.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/** Token de tipografía: Heading, Body, Label. */
@Immutable
data class DsTypography(
    val heading: TextStyle,
    val body: TextStyle,
    val label: TextStyle
)

fun dsTypography(fontFamily: FontFamily = FontFamily.Default) = DsTypography(
    heading = TextStyle(
        fontFamily = fontFamily,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Bold
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
    )
)

/** Sin fuentes cargadas (previews, tests). */
val DefaultDsTypography = dsTypography()

/** Tipografía con Inter, la que usa el tema por defecto. */
@Composable
fun interDsTypography(): DsTypography = dsTypography(Fonts.inter())
