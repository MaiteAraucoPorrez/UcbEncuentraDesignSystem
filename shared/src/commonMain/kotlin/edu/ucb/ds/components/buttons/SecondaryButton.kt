package edu.ucb.ds.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.ds.icons.DsIcons
import edu.ucb.ds.theme.UcbEncuentraTheme

/**
 * Botón secundario con borde (pill): "No es lo mío, seguir buscando".
 * Con [color] = tertiary queda como "Agregar foto de referencia".
 */
@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: AppButtonSize = AppButtonSize.Regular,
    color: Color = UcbEncuentraTheme.colors.primary,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null
) {
    val effectiveColor = if (enabled) color else color.copy(alpha = DISABLED_ALPHA)
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.heightIn(min = size.minHeight),
        enabled = enabled,
        shape = UcbEncuentraTheme.shapes.pill,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = color,
            disabledContentColor = effectiveColor
        ),
        border = BorderStroke(1.dp, effectiveColor),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = UcbEncuentraTheme.spacing.itemGap)
    ) {
        ButtonContent(text, size, leadingIcon, trailingIcon)
    }
}

@Preview
@Composable
private fun SecondaryButtonPreview() {
    UcbEncuentraTheme {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SecondaryButton("NO ES LO MÍO, SEGUIR BUSCANDO", onClick = {}, modifier = Modifier.fillMaxWidth())
            SecondaryButton(
                "Agregar foto de referencia (opcional)",
                onClick = {},
                color = UcbEncuentraTheme.colors.tertiary,
                leadingIcon = DsIcons.Add
            )
            SecondaryButton("Deshabilitado", onClick = {}, enabled = false)
        }
    }
}
