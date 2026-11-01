package edu.ucb.ds.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/** Token de formas redondeadas del mockup. */
@Immutable
data class DsShapes(
    val small: Shape = RoundedCornerShape(8.dp),   // chips
    val medium: Shape = RoundedCornerShape(12.dp), // campos de texto, tiles de categoría
    val large: Shape = RoundedCornerShape(16.dp),  // tarjetas
    val pill: Shape = RoundedCornerShape(50)       // botones "Continuar", "Siguiente"
)

val DefaultDsShapes = DsShapes()
