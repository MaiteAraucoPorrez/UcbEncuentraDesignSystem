package edu.ucb.ds.components.buttons

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import edu.ucb.ds.theme.UcbEncuentraTheme

/** Tamaños de botón del Figma. */
enum class AppButtonSize(val minHeight: Dp, val iconSize: Dp) {
    /** 56 de alto, texto 12 SemiBold: "Continuar", "Reportar objeto", "Verificar". */
    Regular(minHeight = 56.dp, iconSize = 20.dp),

    /** 60 de alto, texto 20 SemiBold: "Siguiente", "Enviar reporte" (barra inferior de formularios). */
    Large(minHeight = 60.dp, iconSize = 20.dp);

    internal val textStyle: TextStyle
        @Composable get() = when (this) {
            Regular -> UcbEncuentraTheme.typography.label
            Large -> UcbEncuentraTheme.typography.title.copy(fontWeight = UcbEncuentraTheme.typography.label.fontWeight)
        }
}
