package edu.ucb.ds.components.textfields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.ds.components.label.AppLabel
import edu.ucb.ds.components.label.AppOverline
import edu.ucb.ds.icons.DsIcons
import edu.ucb.ds.theme.UcbEncuentraTheme

/**
 * Campo de texto pill del Figma: etiqueta arriba en mayúsculas, borde outlineVariant y fondo background.
 * En el Login el fondo es blanco: pasar [containerColor] = surface.
 */
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: ImageVector? = null,
    errorMessage: String? = null,
    enabled: Boolean = true,
    containerColor: Color = UcbEncuentraTheme.colors.background,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    FieldLayout(label = label, errorMessage = errorMessage, modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            textStyle = UcbEncuentraTheme.typography.bodyLarge,
            placeholder = placeholder?.let { { Placeholder(it) } },
            leadingIcon = leadingIcon?.let { { Icon(it, contentDescription = null, modifier = Modifier.size(20.dp)) } },
            isError = errorMessage != null,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            shape = UcbEncuentraTheme.shapes.pill,
            colors = appTextFieldColors(containerColor)
        )
    }
}

/** Área de texto (radio 16) para descripciones: "Características distintivas". */
@Composable
fun AppTextArea(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    errorMessage: String? = null,
    enabled: Boolean = true,
    minLines: Int = 4,
    containerColor: Color = UcbEncuentraTheme.colors.surface
) {
    FieldLayout(label = label, errorMessage = errorMessage, modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            minLines = minLines,
            textStyle = UcbEncuentraTheme.typography.body,
            placeholder = placeholder?.let { { Placeholder(it) } },
            isError = errorMessage != null,
            shape = UcbEncuentraTheme.shapes.large,
            colors = appTextFieldColors(containerColor)
        )
    }
}

/** Etiqueta arriba + campo + mensaje de error abajo. Lo comparten AppTextField, AppTextArea y SelectField. */
@Composable
internal fun FieldLayout(
    label: String?,
    errorMessage: String?,
    modifier: Modifier = Modifier,
    field: @Composable () -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.medium)) {
        if (label != null) AppOverline(label)
        field()
        if (errorMessage != null) {
            AppLabel(
                text = errorMessage,
                color = UcbEncuentraTheme.colors.error,
                modifier = Modifier.padding(start = UcbEncuentraTheme.spacing.large)
            )
        }
    }
}

@Composable
internal fun Placeholder(text: String) {
    Text(text = text, style = UcbEncuentraTheme.typography.bodyLarge, color = UcbEncuentraTheme.colors.outline)
}

@Composable
internal fun appTextFieldColors(containerColor: Color): TextFieldColors {
    val c = UcbEncuentraTheme.colors
    return OutlinedTextFieldDefaults.colors(
        focusedTextColor = c.onSurface,
        unfocusedTextColor = c.onSurface,
        focusedContainerColor = containerColor,
        unfocusedContainerColor = containerColor,
        disabledContainerColor = containerColor,
        errorContainerColor = containerColor,
        focusedBorderColor = c.primary,
        unfocusedBorderColor = c.outlineVariant,
        errorBorderColor = c.error,
        cursorColor = c.primary,
        focusedLeadingIconColor = c.onSurfaceVariant,
        unfocusedLeadingIconColor = c.onSurfaceVariant,
        focusedTrailingIconColor = c.onSurfaceVariant,
        unfocusedTrailingIconColor = c.onSurfaceVariant
    )
}

@Preview
@Composable
private fun AppTextFieldPreview() {
    UcbEncuentraTheme {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            AppTextField(value = "", onValueChange = {}, placeholder = "correo@ucb.edu.bo", leadingIcon = DsIcons.Add)
            AppTextField(
                value = "",
                onValueChange = {},
                label = "Detalle adicional (aula, piso, etc.)",
                placeholder = "Ej. Aula A-203"
            )
            AppTextField(value = "ana@gmail.com", onValueChange = {}, label = "Correo", errorMessage = "Usa tu correo @ucb.edu.bo")
            AppTextArea(value = "", onValueChange = {}, label = "Características distintivas", placeholder = "Ej. sticker roto")
        }
    }
}
