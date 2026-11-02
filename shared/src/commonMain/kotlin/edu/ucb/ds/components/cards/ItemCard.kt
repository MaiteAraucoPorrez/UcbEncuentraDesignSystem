package edu.ucb.ds.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.ds.components.badges.ScoreBadge
import edu.ucb.ds.components.badges.StatusChip
import edu.ucb.ds.components.badges.StatusTone
import edu.ucb.ds.components.label.AppBody
import edu.ucb.ds.components.label.AppLabel
import edu.ucb.ds.components.label.AppOverline
import edu.ucb.ds.components.label.AppTitle
import edu.ucb.ds.icons.DsIcons
import edu.ucb.ds.theme.UcbEncuentraTheme

/**
 * Tarjeta de objeto (perdido o encontrado). Cubre las dos del Figma:
 * - Resultados de Matching: [image] + categoría + título + fecha, [variant] Filled y [score] en la esquina.
 * - Mis Reportes: categoría + título + fecha + [location], [status] arriba a la derecha y botón [actionText].
 * [image] es un slot: la app pone ahí su foto (p. ej. AsyncImage de Coil) y el DS le da tamaño y forma.
 */
@Composable
fun ItemCard(
    category: String,
    title: String,
    date: String,
    modifier: Modifier = Modifier,
    image: (@Composable () -> Unit)? = null,
    location: String? = null,
    status: (@Composable () -> Unit)? = null,
    score: Int? = null,
    actionText: String? = null,
    onAction: () -> Unit = {},
    variant: AppCardVariant = AppCardVariant.Elevated,
    categoryColor: Color = UcbEncuentraTheme.colors.onSurfaceVariant,
    dimmed: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val c = UcbEncuentraTheme.colors
    val spacing = UcbEncuentraTheme.spacing
    // El badge sobresale de la esquina, por eso el Box no recorta.
    Box(modifier = modifier.fillMaxWidth()) {
        AppCard(
            variant = variant,
            onClick = onClick,
            modifier = Modifier.alpha(if (dimmed) DIMMED_ALPHA else 1f)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.large)) {
                if (image != null) {
                    Box(
                        Modifier
                            .size(IMAGE_SIZE)
                            .clip(UcbEncuentraTheme.shapes.medium)
                            .background(c.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) { image() }
                }
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(spacing.small)) {
                    Row(verticalAlignment = Alignment.Top) {
                        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(spacing.small)) {
                            AppOverline(text = category, color = categoryColor)
                            AppTitle(text = title)
                        }
                        if (status != null) status()
                    }
                    MetaRow(DsIcons.Calendar, date)
                    if (location != null) MetaRow(DsIcons.Location, location)
                }
            }
            if (actionText != null) {
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    AppLabel(
                        text = actionText,
                        color = if (dimmed) c.onSurfaceVariant else c.onPrimary,
                        modifier = Modifier
                            .clip(UcbEncuentraTheme.shapes.pill)
                            .background(if (dimmed) c.surfaceVariant else c.primary)
                            .clickable(onClick = onAction)
                            .padding(horizontal = spacing.itemGap, vertical = spacing.medium)
                    )
                }
            }
        }
        if (score != null) {
            ScoreBadge(score = score, modifier = Modifier.align(Alignment.TopEnd).offset(x = 8.dp, y = (-12).dp))
        }
    }
}

@Composable
private fun MetaRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.small)) {
        Icon(icon, contentDescription = null, tint = UcbEncuentraTheme.colors.outline, modifier = Modifier.size(14.dp))
        AppBody(text = text, color = UcbEncuentraTheme.colors.outline)
    }
}

private val IMAGE_SIZE = 80.dp
private const val DIMMED_ALPHA = 0.6f

@Preview
@Composable
private fun ItemCardPreview() {
    UcbEncuentraTheme {
        Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
            ItemCard(
                category = "Electrónicos",
                title = "Calculadora Científica",
                date = "Encontrado: 12 Oct 2026",
                image = { Icon(DsIcons.Inbox, contentDescription = null) },
                score = 92,
                variant = AppCardVariant.Filled
            )
            ItemCard(
                category = "Material de estudio",
                title = "Cuaderno de Cálculo",
                date = "Reportado: 05 Oct 2026",
                location = "Bloque D, Aula 104",
                status = { StatusChip("Match confirmado", StatusTone.Success) },
                actionText = "Ver Detalles",
                categoryColor = UcbEncuentraTheme.colors.primary
            )
            ItemCard(
                category = "Otros",
                title = "Botella de Agua Azul",
                date = "Reportado: 01 Sep 2026",
                location = "Canchas Deportivas",
                actionText = "Ver Detalles",
                dimmed = true
            )
        }
    }
}
