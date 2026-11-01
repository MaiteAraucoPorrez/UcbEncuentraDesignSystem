package edu.ucb.ds.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/** Token de formas redondeadas (radios medidos en el Figma). */
@Immutable
data class DsShapes(
    val small: Shape = RoundedCornerShape(8.dp),       // miniaturas de objetos
    val medium: Shape = RoundedCornerShape(12.dp),     // ítems de lista, esquinas del bottom nav
    val large: Shape = RoundedCornerShape(16.dp),      // tarjetas de métricas del dashboard
    val extraLarge: Shape = RoundedCornerShape(24.dp), // tarjeta del login, tarjetas de Home y Matching
    val pill: Shape = RoundedCornerShape(50)           // botones, inputs, chips, pastilla del bottom nav
)

val DefaultDsShapes = DsShapes()
