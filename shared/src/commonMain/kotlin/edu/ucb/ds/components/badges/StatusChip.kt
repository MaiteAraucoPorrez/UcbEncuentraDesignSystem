package edu.ucb.ds.components.badges

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.ds.components.label.AppLabel
import edu.ucb.ds.theme.DsColors
import edu.ucb.ds.theme.UcbEncuentraTheme

/**
 * Tono del chip de estado. El DS no conoce los estados del dominio: la app los traduce, por ejemplo
 * Buscando → [Neutral], En verificación → [Warning], Match confirmado → [Success], Vencido → [Error],
 * Disponible → [Info], Reclamado / Entregado → [Primary]. (Colores tomados de las tarjetas de Mis Reportes.)
 */
enum class StatusTone {
    Neutral,
    Warning,
    Success,
    Error,
    Info,
    Primary;

    internal fun background(c: DsColors): Color = when (this) {
        Neutral -> c.neutral
        Warning -> c.warning
        Success -> c.success
        Error -> c.error
        Info -> c.tertiary
        Primary -> c.primary
    }
}

/** Pastilla de estado con texto blanco: "Match confirmado", "Buscando", "Vencido"… */
@Composable
fun StatusChip(
    text: String,
    tone: StatusTone,
    modifier: Modifier = Modifier
) {
    AppLabel(
        text = text,
        color = Color.White,
        modifier = modifier
            .background(tone.background(UcbEncuentraTheme.colors), UcbEncuentraTheme.shapes.pill)
            .padding(horizontal = UcbEncuentraTheme.spacing.itemGap, vertical = UcbEncuentraTheme.spacing.small)
    )
}

@Preview
@Composable
private fun StatusChipPreview() {
    UcbEncuentraTheme {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            StatusChip("Buscando", StatusTone.Neutral)
            StatusChip("En verificación", StatusTone.Warning)
            StatusChip("Match confirmado", StatusTone.Success)
            StatusChip("Vencido", StatusTone.Error)
            StatusChip("Disponible", StatusTone.Info)
            StatusChip("Reclamado", StatusTone.Primary)
        }
    }
}
