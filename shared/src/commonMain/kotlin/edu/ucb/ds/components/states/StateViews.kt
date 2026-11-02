package edu.ucb.ds.components.states

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.ds.components.buttons.PrimaryButton
import edu.ucb.ds.components.label.AppBody
import edu.ucb.ds.components.label.AppTitle
import edu.ucb.ds.icons.DsIcons
import edu.ucb.ds.theme.UcbEncuentraTheme

// El Figma no tiene estas pantallas: se arman solo con tokens y componentes del Design System.

/** Lista vacía: "Aún no tienes reportes". */
@Composable
fun EmptyState(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = DsIcons.Inbox,
    actionText: String? = null,
    onAction: () -> Unit = {}
) {
    StateLayout(
        icon = icon,
        iconTint = UcbEncuentraTheme.colors.onSurfaceVariant,
        title = title,
        message = message,
        actionText = actionText,
        onAction = onAction,
        modifier = modifier
    )
}

/** Error con reintento: "No pudimos cargar tus reportes". */
@Composable
fun ErrorState(
    message: String,
    modifier: Modifier = Modifier,
    title: String = "Algo salió mal",
    retryText: String = "Reintentar",
    onRetry: (() -> Unit)? = null
) {
    StateLayout(
        icon = DsIcons.Error,
        iconTint = UcbEncuentraTheme.colors.error,
        title = title,
        message = message,
        actionText = if (onRetry != null) retryText else null,
        onAction = onRetry ?: {},
        modifier = modifier
    )
}

/** Cargando, con mensaje opcional. */
@Composable
fun LoadingState(
    modifier: Modifier = Modifier,
    message: String? = null
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(UcbEncuentraTheme.spacing.screenPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.large, Alignment.CenterVertically)
    ) {
        CircularProgressIndicator(color = UcbEncuentraTheme.colors.primary)
        if (message != null) AppBody(text = message, textAlign = TextAlign.Center)
    }
}

@Composable
private fun StateLayout(
    icon: ImageVector,
    iconTint: Color,
    title: String,
    message: String,
    actionText: String?,
    onAction: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(UcbEncuentraTheme.spacing.screenPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.large, Alignment.CenterVertically)
    ) {
        Box(
            Modifier
                .size(72.dp)
                .background(UcbEncuentraTheme.colors.surfaceVariant, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(32.dp))
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.small)) {
            AppTitle(text = title, textAlign = TextAlign.Center)
            AppBody(text = message, textAlign = TextAlign.Center)
        }
        if (actionText != null) {
            PrimaryButton(text = actionText, onClick = onAction)
        }
    }
}

@Preview
@Composable
private fun StateViewsPreview() {
    UcbEncuentraTheme {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            EmptyState(
                title = "Aún no tienes reportes",
                message = "Cuando reportes un objeto perdido aparecerá aquí.",
                actionText = "Reportar objeto"
            )
            ErrorState(message = "Revisa tu conexión e intenta de nuevo.", onRetry = {})
            LoadingState(message = "Buscando coincidencias…")
        }
    }
}
