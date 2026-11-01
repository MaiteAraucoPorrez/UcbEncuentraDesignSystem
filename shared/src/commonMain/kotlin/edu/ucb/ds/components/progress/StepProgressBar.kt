package edu.ucb.ds.components.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.ds.components.label.AppLabel
import edu.ucb.ds.theme.UcbEncuentraTheme

/**
 * Barra "Paso x de y" del wizard de reporte.
 * [onDark] = true para ponerla dentro del header azul (Figma: Reportar - Categoría);
 * false para fondo claro (Figma: Reportar - Ubicación).
 */
@Composable
fun StepProgressBar(
    currentStep: Int,
    totalSteps: Int,
    modifier: Modifier = Modifier,
    stepName: String? = null,
    onDark: Boolean = false
) {
    val c = UcbEncuentraTheme.colors
    val textColor = if (onDark) Color.White.copy(alpha = 0.8f) else c.tertiary
    val trackColor = if (onDark) Color.White.copy(alpha = 0.2f) else c.surfaceVariant
    val fillColor = if (onDark) ON_DARK_FILL else c.tertiary
    val progress = if (totalSteps > 0) (currentStep.toFloat() / totalSteps).coerceIn(0f, 1f) else 0f

    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.small)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            AppLabel(text = "Paso $currentStep de $totalSteps", color = textColor)
            if (stepName != null) AppLabel(text = stepName, color = textColor)
        }
        Box(
            Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(UcbEncuentraTheme.shapes.pill)
                .background(trackColor)
        ) {
            Box(
                Modifier
                    .fillMaxWidth(progress)
                    .fillMaxHeight()
                    .clip(UcbEncuentraTheme.shapes.pill)
                    .background(fillColor)
            )
        }
    }
}

/** Celeste de la barra sobre el header (#D2E4FF en el Figma). */
private val ON_DARK_FILL = Color(0xFFD2E4FF)

@Preview
@Composable
private fun StepProgressBarPreview() {
    UcbEncuentraTheme {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(Modifier.background(UcbEncuentraTheme.colors.primary).padding(20.dp)) {
                StepProgressBar(currentStep = 1, totalSteps = 4, stepName = "Categoría", onDark = true)
            }
            StepProgressBar(currentStep = 3, totalSteps = 4, modifier = Modifier.padding(20.dp))
        }
    }
}
