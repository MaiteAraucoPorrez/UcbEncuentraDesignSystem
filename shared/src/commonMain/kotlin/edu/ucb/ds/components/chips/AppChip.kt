package edu.ucb.ds.components.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.ds.theme.UcbEncuentraTheme

/** Chip de filtro pill: seleccionado = primary con texto blanco; si no, surfaceContainer con borde. */
@Composable
fun AppFilterChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val c = UcbEncuentraTheme.colors
    val shape = UcbEncuentraTheme.shapes.pill
    Text(
        text = text,
        style = UcbEncuentraTheme.typography.label,
        color = if (selected) c.onPrimary else c.onSurfaceVariant,
        modifier = modifier
            .clip(shape)
            .background(if (selected) c.primary else c.surfaceContainer)
            .border(1.dp, if (selected) Color.Transparent else c.outlineVariant, shape)
            .clickable(onClick = onClick)
            .padding(horizontal = UcbEncuentraTheme.spacing.large, vertical = 7.dp)
    )
}

/** Grupo de chips de selección única que salta de línea si no entra (FlowRow). */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AppChipGroup(
    options: List<String>,
    selected: String,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.medium),
        verticalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.medium)
    ) {
        options.forEach { option ->
            AppFilterChip(text = option, selected = option == selected, onClick = { onSelect(option) })
        }
    }
}

@Preview
@Composable
private fun AppChipGroupPreview() {
    UcbEncuentraTheme {
        AppChipGroup(
            options = listOf("Todos", "Buscando", "Verificación"),
            selected = "Todos",
            onSelect = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
