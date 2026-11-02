package edu.ucb.ds.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.ds.components.label.AppBody
import edu.ucb.ds.components.label.AppLabel
import edu.ucb.ds.icons.DsIcons
import edu.ucb.ds.theme.UcbEncuentraTheme

/** Lista vertical (lazy) con el espaciado del Design System. */
@Composable
fun <T> AppList(
    items: List<T>,
    modifier: Modifier = Modifier,
    key: ((T) -> Any)? = null,
    contentPadding: PaddingValues = PaddingValues(vertical = UcbEncuentraTheme.spacing.medium),
    itemContent: @Composable (T) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.itemGap)
    ) {
        items(items, key = key) { item -> itemContent(item) }
    }
}

/** Contenedor surfaceContainer (radio 24, padding 8) que agrupa filas: "Accesos rápidos" del Home. */
@Composable
fun AppListGroup(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(UcbEncuentraTheme.shapes.extraLarge)
            .background(UcbEncuentraTheme.colors.surfaceContainer)
            .padding(UcbEncuentraTheme.spacing.medium),
        verticalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.medium),
        content = content
    )
}

/** Fila pill blanca: ícono + título (+ subtítulo) + chevron. "Mis reportes", "Objetos encontrados". */
@Composable
fun AppListItem(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leadingIcon: ImageVector? = null,
    showChevron: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    val c = UcbEncuentraTheme.colors
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(UcbEncuentraTheme.shapes.pill)
            .background(c.surface)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(horizontal = UcbEncuentraTheme.spacing.screenPadding, vertical = UcbEncuentraTheme.spacing.large),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.large)
    ) {
        if (leadingIcon != null) {
            Icon(leadingIcon, contentDescription = null, tint = c.onSurfaceVariant, modifier = Modifier.size(20.dp))
        }
        Column(Modifier.weight(1f)) {
            AppBody(text = title, large = true, color = c.onSurface)
            if (subtitle != null) AppLabel(text = subtitle)
        }
        if (showChevron) {
            Icon(DsIcons.ChevronRight, contentDescription = null, tint = c.onSurfaceVariant, modifier = Modifier.size(20.dp))
        }
    }
}

@Preview
@Composable
private fun AppListPreview() {
    UcbEncuentraTheme {
        AppListGroup(Modifier.padding(16.dp)) {
            AppListItem("Mis reportes", leadingIcon = DsIcons.Check, onClick = {})
            AppListItem("Objetos encontrados", leadingIcon = DsIcons.Add, onClick = {})
            AppListItem("Cómo funciona", subtitle = "Guía rápida", onClick = {})
        }
    }
}
