package edu.ucb.ds.components.label

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import edu.ucb.ds.theme.UcbEncuentraTheme

/** 22/28 Bold: títulos de pantalla y de tarjeta ("Iniciar Sesión", "¿Perdiste algo?"). */
@Composable
fun AppHeading(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = UcbEncuentraTheme.colors.onSurface,
    textAlign: TextAlign? = null
) {
    Text(text = text, modifier = modifier, color = color, style = UcbEncuentraTheme.typography.heading, textAlign = textAlign)
}

/** 20/28 Bold: título del header, filas destacadas ("2 reportes activos"). */
@Composable
fun AppTitle(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = UcbEncuentraTheme.colors.onSurface,
    textAlign: TextAlign? = null
) {
    Text(text = text, modifier = modifier, color = color, style = UcbEncuentraTheme.typography.title, textAlign = textAlign)
}

/** 14/20 Regular (o 16/24 con [large]): texto general. Por defecto en el color secundario del Figma. */
@Composable
fun AppBody(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = UcbEncuentraTheme.colors.onSurfaceVariant,
    large: Boolean = false,
    textAlign: TextAlign? = null
) {
    val style = if (large) UcbEncuentraTheme.typography.bodyLarge else UcbEncuentraTheme.typography.body
    Text(text = text, modifier = modifier, color = color, style = style, textAlign = textAlign)
}

/** 12/16 SemiBold: etiquetas, fechas, textos de botón. */
@Composable
fun AppLabel(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = UcbEncuentraTheme.colors.onSurfaceVariant,
    textAlign: TextAlign? = null
) {
    Text(text = text, modifier = modifier, color = color, style = UcbEncuentraTheme.typography.label, textAlign = textAlign)
}

/** 12/16 ExtraBold en mayúsculas: encabezados de sección ("ACTIVIDAD", "¿DÓNDE LO PERDISTE?"). */
@Composable
fun AppOverline(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = UcbEncuentraTheme.colors.onSurfaceVariant
) {
    Text(text = text.uppercase(), modifier = modifier, color = color, style = UcbEncuentraTheme.typography.overline)
}

@Preview
@Composable
private fun AppLabelPreview() {
    UcbEncuentraTheme {
        Column {
            AppOverline("Actividad")
            AppHeading("¿Perdiste algo?")
            AppTitle("2 reportes activos")
            AppBody("Mis reportes", large = true, color = UcbEncuentraTheme.colors.onSurface)
            AppBody("La comunidad UCB está aquí para ayudarte a recuperarlo.")
            AppLabel("Encontrado: 12 Oct 2026")
        }
    }
}
