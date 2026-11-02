package edu.ucb.ds.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.ds.components.label.AppLabel
import edu.ucb.ds.icons.DsIcons
import edu.ucb.ds.theme.UcbEncuentraTheme

/** Una opción de categoría para [CategoryTile] / [CategoryGrid]. */
data class CategoryOption(val id: String, val label: String, val icon: ImageVector)

/**
 * Tile de categoría del wizard (Figma: Reportar - Categoría): surfaceContainer, radio 24, ícono + texto.
 * Seleccionado: fondo secondary con borde primary (el Figma no muestra este estado; usa el azul de selección).
 */
@Composable
fun CategoryTile(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val c = UcbEncuentraTheme.colors
    val shape = UcbEncuentraTheme.shapes.extraLarge
    Column(
        modifier = modifier
            .aspectRatio(TILE_ASPECT_RATIO)
            .clip(shape)
            .background(if (selected) c.secondary else c.surfaceContainer)
            .border(2.dp, if (selected) c.primary else Color.Transparent, shape)
            .clickable(onClick = onClick)
            .semantics {
                this.selected = selected
                role = Role.RadioButton
            }
            .padding(UcbEncuentraTheme.spacing.large),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.itemGap, Alignment.CenterVertically)
    ) {
        Icon(icon, contentDescription = null, tint = c.onSurface, modifier = Modifier.size(28.dp))
        AppLabel(text = label, color = c.onSurface, textAlign = TextAlign.Center)
    }
}

/** Grilla de 2 columnas de categorías, selección única. */
@Composable
fun CategoryGrid(
    options: List<CategoryOption>,
    selectedId: String?,
    onSelect: (CategoryOption) -> Unit,
    modifier: Modifier = Modifier
) {
    val gap = UcbEncuentraTheme.spacing.large
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(gap)) {
        options.chunked(COLUMNS).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(gap)) {
                row.forEach { option ->
                    CategoryTile(
                        label = option.label,
                        icon = option.icon,
                        selected = option.id == selectedId,
                        onClick = { onSelect(option) },
                        modifier = Modifier.weight(1f)
                    )
                }
                // Si la última fila tiene una sola categoría, mantiene el ancho de media columna.
                if (row.size < COLUMNS) Spacer(Modifier.weight(1f))
            }
        }
    }
}

private const val COLUMNS = 2
private const val TILE_ASPECT_RATIO = 1.13f

@Preview
@Composable
private fun CategoryGridPreview() {
    UcbEncuentraTheme {
        CategoryGrid(
            options = listOf(
                CategoryOption("electronica", "Electrónica", DsIcons.Inbox),
                CategoryOption("ropa", "Ropa/Accesorios", DsIcons.Check),
                CategoryOption("documentos", "Documentos", DsIcons.Calendar),
                CategoryOption("libros", "Libros/Cuadernos", DsIcons.Menu),
                CategoryOption("llaves", "Llaves", DsIcons.Location)
            ),
            selectedId = "documentos",
            onSelect = {},
            modifier = Modifier.padding(20.dp)
        )
    }
}
