package edu.ucb.ds.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.unit.dp

/**
 * Íconos que usan los componentes del Design System, dibujados con rutas vectoriales
 * (no necesitan archivos ni librerías de íconos). Uso: Icon(DsIcons.Check, contentDescription = null)
 */
object DsIcons {
    /** Flecha "→" de los botones Continuar / Siguiente. */
    val ArrowForward: ImageVector by lazy { icon("ArrowForward", "M12,4l-1.41,1.41L16.17,11H4v2h12.17l-5.58,5.59L12,20l8,-8z") }

    /** Flecha "←" del header. */
    val ArrowBack: ImageVector by lazy { icon("ArrowBack", "M20,11H7.83l5.59,-5.59L12,4l-8,8 8,8 1.41,-1.41L7.83,13H20v-2z") }

    /** Chevron del SelectField. */
    val ChevronDown: ImageVector by lazy { icon("ChevronDown", "M7.41,8.59L12,13.17l4.59,-4.58L18,10l-6,6 -6,-6 1.41,-1.41z") }

    /** Chevron de los ítems de lista. */
    val ChevronRight: ImageVector by lazy { icon("ChevronRight", "M10,6L8.59,7.41 13.17,12l-4.58,4.59L10,18l6,-6z") }

    /** Check del color seleccionado. */
    val Check: ImageVector by lazy { icon("Check", "M9,16.17L4.83,12l-1.42,1.41L9,19 21,7l-1.41,-1.41z") }

    /** "+" para agregar (otro color, otra foto). */
    val Add: ImageVector by lazy { icon("Add", "M19,13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z") }

    private fun icon(name: String, path: String): ImageVector = ImageVector.Builder(
        name = name,
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).addPath(
        pathData = addPathNodes(path),
        fill = SolidColor(Color.Black)
    ).build()
}
