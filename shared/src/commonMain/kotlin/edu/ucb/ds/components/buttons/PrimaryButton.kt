package edu.ucb.ds.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.ds.icons.DsIcons
import edu.ucb.ds.theme.UcbEncuentraTheme

/** Botón principal (pill azul marino). Deshabilitado = mismo color al 50 %, como "Siguiente" en el Figma. */
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: AppButtonSize = AppButtonSize.Regular,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null
) {
    val colors = UcbEncuentraTheme.colors
    Button(
        onClick = onClick,
        modifier = modifier.heightIn(min = size.minHeight),
        enabled = enabled,
        shape = UcbEncuentraTheme.shapes.pill,
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.primary,
            contentColor = colors.onPrimary,
            disabledContainerColor = colors.primary.copy(alpha = DISABLED_ALPHA),
            disabledContentColor = colors.onPrimary
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = UcbEncuentraTheme.spacing.itemGap)
    ) {
        ButtonContent(text, size, leadingIcon, trailingIcon)
    }
}

@Composable
internal fun ButtonContent(
    text: String,
    size: AppButtonSize,
    leadingIcon: ImageVector?,
    trailingIcon: ImageVector?
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.medium)
    ) {
        if (leadingIcon != null) Icon(leadingIcon, contentDescription = null, modifier = Modifier.size(size.iconSize))
        Text(text = text, style = size.textStyle)
        if (trailingIcon != null) Icon(trailingIcon, contentDescription = null, modifier = Modifier.size(size.iconSize))
    }
}

internal const val DISABLED_ALPHA = 0.5f

@Preview
@Composable
private fun PrimaryButtonPreview() {
    UcbEncuentraTheme {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            PrimaryButton("Continuar", onClick = {}, modifier = Modifier.fillMaxWidth(), trailingIcon = DsIcons.ArrowForward)
            PrimaryButton("Reportar objeto", onClick = {}, modifier = Modifier.fillMaxWidth(), leadingIcon = DsIcons.Add)
            PrimaryButton("Siguiente", onClick = {}, modifier = Modifier.fillMaxWidth(), size = AppButtonSize.Large)
            PrimaryButton("Siguiente", onClick = {}, modifier = Modifier.fillMaxWidth(), size = AppButtonSize.Large, enabled = false)
        }
    }
}
