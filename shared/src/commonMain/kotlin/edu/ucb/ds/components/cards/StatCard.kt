package edu.ucb.ds.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.ds.components.label.AppHeading
import edu.ucb.ds.components.label.AppLabel
import edu.ucb.ds.icons.DsIcons
import edu.ucb.ds.theme.UcbEncuentraTheme

/**
 * KPI del Dashboard (Figma: "142 · Objetos en custodia"): blanca, radio 16, borde outlineVariant al 30 %.
 * Para alertas ("3 · Por vencer") pasar [valueColor] = error.
 */
@Composable
fun StatCard(
    value: String,
    label: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    valueColor: Color = UcbEncuentraTheme.colors.primary,
    onClick: (() -> Unit)? = null
) {
    val c = UcbEncuentraTheme.colors
    val shape = UcbEncuentraTheme.shapes.large
    Column(
        modifier = modifier
            .shadow(elevation = 1.dp, shape = shape)
            .clip(shape)
            .background(c.surface)
            .border(1.dp, c.outlineVariant.copy(alpha = BORDER_ALPHA), shape)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(UcbEncuentraTheme.spacing.screenPadding),
        verticalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.small)
    ) {
        Icon(icon, contentDescription = null, tint = valueColor, modifier = Modifier.size(24.dp))
        AppHeading(text = value, color = valueColor)
        AppLabel(text = label)
    }
}

private const val BORDER_ALPHA = 0.3f

@Preview
@Composable
private fun StatCardPreview() {
    UcbEncuentraTheme {
        Row(Modifier.padding(20.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            StatCard("142", "Objetos en custodia", DsIcons.Inbox, Modifier.weight(1f))
            StatCard("3", "Por vencer", DsIcons.Error, Modifier.weight(1f), valueColor = UcbEncuentraTheme.colors.error)
        }
    }
}
