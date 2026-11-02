package edu.ucb.ds.components.badges

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import edu.ucb.ds.theme.UcbEncuentraTheme
import edu.ucb.ds.theme.scoreColor

/**
 * Círculo del % de coincidencia (Figma: Resultados de Matching).
 * Verde >= 80, naranja 50-79, gris < 50; borde de 4 del color de fondo para que "flote" sobre la tarjeta.
 */
@Composable
fun ScoreBadge(
    score: Int,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp
) {
    val c = UcbEncuentraTheme.colors
    val clamped = score.coerceIn(0, MAX_SCORE)
    Box(
        modifier = modifier
            .size(size)
            .shadow(elevation = 1.dp, shape = CircleShape)
            .background(c.scoreColor(clamped), CircleShape)
            .border(4.dp, c.background, CircleShape)
            .semantics { contentDescription = "Coincidencia $clamped por ciento" },
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
            Text(
                text = clamped.toString(),
                color = Color.White,
                style = UcbEncuentraTheme.typography.title.copy(fontWeight = UcbEncuentraTheme.typography.label.fontWeight)
            )
            Text(
                text = "%",
                color = Color.White,
                style = UcbEncuentraTheme.typography.label,
                modifier = Modifier.padding(bottom = 3.dp)
            )
        }
    }
}

private const val MAX_SCORE = 100

@Preview
@Composable
private fun ScoreBadgePreview() {
    UcbEncuentraTheme {
        Row(Modifier.padding(16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ScoreBadge(92)
            ScoreBadge(65)
            ScoreBadge(30)
        }
    }
}
