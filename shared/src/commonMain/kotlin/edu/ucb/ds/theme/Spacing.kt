package edu.ucb.ds.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Token de espaciado: Small (4dp), Medium (8dp), Large (16dp) de la guía de clase
 * + los dos valores que el Figma usa en todas las pantallas.
 */
@Immutable
data class DsSpacing(
    val small: Dp = 4.dp,
    val medium: Dp = 8.dp,
    val itemGap: Dp = 12.dp,      // entre ítems de una lista, ícono y texto de una fila
    val large: Dp = 16.dp,
    val screenPadding: Dp = 20.dp // margen lateral de pantalla y padding interno de tarjetas
)

val DefaultDsSpacing = DsSpacing()
