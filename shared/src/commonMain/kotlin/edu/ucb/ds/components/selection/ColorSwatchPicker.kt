package edu.ucb.ds.components.selection

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.ds.components.label.AppLabel
import edu.ucb.ds.icons.DsIcons
import edu.ucb.ds.theme.UcbEncuentraTheme

data class SwatchOption(val name: String, val color: Color)

/** Colores del selector "Color principal" del Figma (Reportar - Atributos). */
val DefaultItemColors = listOf(
    SwatchOption("Negro", Color(0xFF000000)),
    SwatchOption("Blanco", Color(0xFFFFFFFF)),
    SwatchOption("Rojo", Color(0xFFDC2626)),
    SwatchOption("Azul", Color(0xFF2563EB)),
    SwatchOption("Gris", Color(0xFF9CA3AF))
)

/** Círculos de color de 48 con check en el seleccionado y botón "+" opcional ([onAddClick]). */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColorSwatchPicker(
    selected: String?,
    onSelect: (SwatchOption) -> Unit,
    modifier: Modifier = Modifier,
    options: List<SwatchOption> = DefaultItemColors,
    label: String? = null,
    onAddClick: (() -> Unit)? = null
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.itemGap)) {
        if (label != null) AppLabel(label)
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.large),
            verticalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.large)
        ) {
            options.forEach { option ->
                Swatch(option = option, isSelected = option.name == selected, onClick = { onSelect(option) })
            }
            if (onAddClick != null) {
                Box(
                    modifier = Modifier
                        .size(SWATCH_SIZE)
                        .clip(CircleShape)
                        .background(UcbEncuentraTheme.colors.surfaceVariant)
                        .clickable(onClick = onAddClick)
                        .semantics { contentDescription = "Agregar color" },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(DsIcons.Add, contentDescription = null, tint = UcbEncuentraTheme.colors.onSurfaceVariant)
                }
            }
        }
    }
}

@Composable
private fun Swatch(option: SwatchOption, isSelected: Boolean, onClick: () -> Unit) {
    val isLight = option.color.luminance() > LIGHT_LUMINANCE
    Box(
        modifier = Modifier
            .size(SWATCH_SIZE)
            .clip(CircleShape)
            .background(option.color)
            .border(1.dp, if (isLight) UcbEncuentraTheme.colors.outlineVariant else Color.Transparent, CircleShape)
            .clickable(onClick = onClick)
            .semantics {
                contentDescription = option.name
                selected = isSelected
            },
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Icon(DsIcons.Check, contentDescription = null, tint = if (isLight) Color.Black else Color.White)
        }
    }
}

private val SWATCH_SIZE = 48.dp
private const val LIGHT_LUMINANCE = 0.6f

@Preview
@Composable
private fun ColorSwatchPickerPreview() {
    UcbEncuentraTheme {
        ColorSwatchPicker(
            selected = "Azul",
            onSelect = {},
            label = "Color principal",
            onAddClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
