package edu.ucb.ds.components.selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.ds.components.label.AppBody
import edu.ucb.ds.theme.UcbEncuentraTheme

/** Checkbox + texto; toda la fila es tocable. "Confirmo que he verificado la identidad…" (Confirmación de Entrega). */
@Composable
fun CheckRow(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val c = UcbEncuentraTheme.colors
    Row(
        modifier = modifier
            .fillMaxWidth()
            .toggleable(value = checked, enabled = enabled, role = Role.Checkbox, onValueChange = onCheckedChange),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.itemGap)
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = null, // lo maneja la fila completa
            enabled = enabled,
            colors = CheckboxDefaults.colors(
                checkedColor = c.primary,
                uncheckedColor = c.onSurfaceVariant,
                checkmarkColor = c.onPrimary
            )
        )
        AppBody(text = text, color = c.onSurface, modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
private fun CheckRowPreview() {
    UcbEncuentraTheme {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            CheckRow("Confirmo que he verificado la identidad del estudiante.", checked = true, onCheckedChange = {})
            CheckRow("El estudiante ha recibido el objeto.", checked = false, onCheckedChange = {})
        }
    }
}
