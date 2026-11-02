package edu.ucb.ds.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.ds.components.buttons.PrimaryButton
import edu.ucb.ds.components.label.AppBody
import edu.ucb.ds.components.label.AppHeading
import edu.ucb.ds.theme.UcbEncuentraTheme

/** Las dos tarjetas del Figma (ambas con radio 24 y padding 20). */
enum class AppCardVariant {
    /** Blanca con sombra suave: tarjeta del Login, formularios del wizard, confirmación de entrega. */
    Elevated,

    /** Fondo surfaceContainer (#E0E4EF) sin sombra: "¿Perdiste algo?", actividad, resultados de matching. */
    Filled
}

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    variant: AppCardVariant = AppCardVariant.Elevated,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val c = UcbEncuentraTheme.colors
    val colors = CardDefaults.cardColors(
        containerColor = if (variant == AppCardVariant.Elevated) c.surface else c.surfaceContainer,
        contentColor = c.onSurface
    )
    val elevation = CardDefaults.cardElevation(
        defaultElevation = if (variant == AppCardVariant.Elevated) 2.dp else 0.dp
    )
    val shape = UcbEncuentraTheme.shapes.extraLarge
    val body: @Composable ColumnScope.() -> Unit = {
        Column(
            modifier = Modifier.padding(UcbEncuentraTheme.spacing.screenPadding),
            verticalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.large),
            content = content
        )
    }

    if (onClick != null) {
        Card(onClick = onClick, modifier = modifier.fillMaxWidth(), shape = shape, colors = colors, elevation = elevation, content = body)
    } else {
        Card(modifier = modifier.fillMaxWidth(), shape = shape, colors = colors, elevation = elevation, content = body)
    }
}

@Preview
@Composable
private fun AppCardPreview() {
    UcbEncuentraTheme {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            AppCard {
                AppHeading("Iniciar Sesión")
                AppBody("Ingresa con tu correo institucional @ucb.edu.bo")
            }
            AppCard(variant = AppCardVariant.Filled) {
                AppHeading("¿Perdiste algo?")
                AppBody("La comunidad UCB está aquí para ayudarte a recuperarlo.")
                PrimaryButton("Reportar objeto", onClick = {}, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}
