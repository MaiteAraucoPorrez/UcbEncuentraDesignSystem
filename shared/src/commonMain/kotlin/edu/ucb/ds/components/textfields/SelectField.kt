package edu.ucb.ds.components.textfields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.ds.icons.DsIcons
import edu.ucb.ds.theme.UcbEncuentraTheme

/** Lista desplegable con el estilo de AppTextField: "¿Dónde lo perdiste? · Selecciona una ubicación". */
@Composable
fun SelectField(
    options: List<String>,
    selected: String?,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String = "Selecciona una opción",
    errorMessage: String? = null,
    enabled: Boolean = true,
    containerColor: Color = UcbEncuentraTheme.colors.background
) {
    var expanded by remember { mutableStateOf(false) }

    FieldLayout(label = label, errorMessage = errorMessage, modifier = modifier) {
        Box {
            OutlinedTextField(
                value = selected.orEmpty(),
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                readOnly = true,
                singleLine = true,
                textStyle = UcbEncuentraTheme.typography.bodyLarge,
                placeholder = { Placeholder(placeholder) },
                trailingIcon = { Icon(DsIcons.ChevronDown, contentDescription = null) },
                isError = errorMessage != null,
                shape = UcbEncuentraTheme.shapes.pill,
                colors = appTextFieldColors(containerColor)
            )
            // Capa encima del campo para abrir el menú con un toque (el campo es solo lectura).
            Box(
                Modifier
                    .matchParentSize()
                    .clickable(enabled = enabled) { expanded = true }
            )
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option, style = UcbEncuentraTheme.typography.bodyLarge) },
                        onClick = {
                            onSelect(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SelectFieldPreview() {
    UcbEncuentraTheme {
        SelectField(
            options = listOf("Biblioteca Central", "Bloque D", "Cafetería"),
            selected = null,
            onSelect = {},
            label = "¿Dónde lo perdiste?",
            placeholder = "Selecciona una ubicación",
            modifier = Modifier.padding(16.dp)
        )
    }
}
