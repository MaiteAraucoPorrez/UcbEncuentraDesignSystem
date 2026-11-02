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

    /** Menú hamburguesa del header. */
    val Menu: ImageVector by lazy { icon("Menu", "M3,18h18v-2H3V18zM3,13h18v-2H3V13zM3,6v2h18V6H3z") }

    /** Luna del header (modo oscuro). */
    val DarkMode: ImageVector by lazy {
        icon(
            "DarkMode",
            "M9.37,5.51C9.19,6.15 9.1,6.82 9.1,7.5c0,4.08 3.32,7.4 7.4,7.4c0.68,0 1.35,-0.09 1.99,-0.27" +
                "C17.45,17.19 14.93,19 12,19c-3.86,0 -7,-3.14 -7,-7C5,9.07 6.81,6.55 9.37,5.51z" +
                "M12,3c-4.97,0 -9,4.03 -9,9s4.03,9 9,9s9,-4.03 9,-9c0,-0.46 -0.04,-0.92 -0.1,-1.36" +
                "c-0.98,1.37 -2.58,2.26 -4.4,2.26c-2.98,0 -5.4,-2.42 -5.4,-5.4c0,-1.81 0.89,-3.42 2.26,-4.4" +
                "C12.92,3.04 12.46,3 12,3L12,3z"
        )
    }

    /** Calendario de "Reportado: 12 Oct". */
    val Calendar: ImageVector by lazy {
        icon(
            "Calendar",
            "M19,4h-1V2h-2v2H8V2H6v2H5C3.89,4 3.01,4.9 3.01,6L3,20c0,1.1 0.89,2 2,2h14c1.1,0 2,-0.9 2,-2V6" +
                "C21,4.9 20.1,4 19,4zM19,20H5V10h14V20zM19,8H5V6h14V8z"
        )
    }

    /** Pin de ubicación de "Biblioteca Central, Piso 2". */
    val Location: ImageVector by lazy {
        icon(
            "Location",
            "M12,2C8.13,2 5,5.13 5,9c0,5.25 7,13 7,13s7,-7.75 7,-13C19,5.13 15.87,2 12,2zM7,9c0,-2.76 2.24,-5 5,-5" +
                "s5,2.24 5,5c0,2.88 -2.88,7.19 -5,9.88C9.92,16.21 7,11.85 7,9zM12,6.5c-1.38,0 -2.5,1.12 -2.5,2.5" +
                "s1.12,2.5 2.5,2.5s2.5,-1.12 2.5,-2.5S13.38,6.5 12,6.5z"
        )
    }

    /** Error de ErrorState. */
    val Error: ImageVector by lazy {
        icon(
            "Error",
            "M11,15h2v2h-2zM11,7h2v6h-2zM11.99,2C6.47,2 2,6.48 2,12s4.47,10 9.99,10C17.52,22 22,17.52 22,12" +
                "S17.52,2 11.99,2zM12,20c-4.42,0 -8,-3.58 -8,-8s3.58,-8 8,-8 8,3.58 8,8 -3.58,8 -8,8z"
        )
    }

    /** Bandeja vacía de EmptyState. */
    val Inbox: ImageVector by lazy {
        icon(
            "Inbox",
            "M19,3H4.99C3.88,3 3.01,3.89 3.01,5L3,19c0,1.1 0.88,2 1.99,2H19c1.1,0 2,-0.9 2,-2V5C21,3.89 20.1,3 19,3z" +
                "M19,15h-4c0,1.66 -1.35,3 -3,3s-3,-1.34 -3,-3H4.99V5H19V15z"
        )
    }

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
