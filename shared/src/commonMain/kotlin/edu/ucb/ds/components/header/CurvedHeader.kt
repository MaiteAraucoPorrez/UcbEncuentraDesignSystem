package edu.ucb.ds.components.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import edu.ucb.ds.components.label.AppBody
import edu.ucb.ds.components.label.AppTitle
import edu.ucb.ds.components.progress.StepProgressBar
import edu.ucb.ds.icons.DsIcons
import edu.ucb.ds.theme.UcbEncuentraTheme

/**
 * Máscara del header del Figma: elipse con centro arriba al medio (radios = ancho x alto),
 * opaca hasta el 85 % y transparente en el borde. Da la "curva" que se desvanece abajo.
 */
fun Modifier.headerWaveMask(): Modifier = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        val w = size.width
        // Gradiente circular de radio = ancho, aplastado verticalmente hasta el alto → elipse.
        scale(scaleX = 1f, scaleY = size.height / w, pivot = Offset(w / 2f, 0f)) {
            drawRect(
                brush = Brush.radialGradient(
                    WAVE_SOLID_UNTIL to Color.Black,
                    1f to Color.Transparent,
                    center = Offset(w / 2f, 0f),
                    radius = w
                ),
                size = androidx.compose.ui.geometry.Size(w, w),
                blendMode = BlendMode.DstIn
            )
        }
    }

private const val WAVE_SOLID_UNTIL = 0.85f
private val HEADER_BOTTOM_RADIUS = 40.dp

/** Fondo azul con curva para contenido libre (header alto del Login con escudo y "UCB Encuentra"). */
@Composable
fun CurvedHeaderContainer(
    modifier: Modifier = Modifier,
    minHeight: Dp = 120.dp,
    roundedBottom: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    val shape = if (roundedBottom) {
        RoundedCornerShape(bottomStart = HEADER_BOTTOM_RADIUS, bottomEnd = HEADER_BOTTOM_RADIUS)
    } else {
        RoundedCornerShape(0.dp)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = minHeight)
            .headerWaveMask()
            .clip(shape)
            .background(UcbEncuentraTheme.colors.primary)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = UcbEncuentraTheme.spacing.screenPadding),
        content = content
    )
}

/**
 * Header azul con curva (120 de alto): botón izquierdo (menú o atrás), título, subtítulo y acciones (luna).
 * [bottomContent] va debajo de la fila del título, por ejemplo la StepProgressBar del wizard.
 */
@Composable
fun CurvedHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    navigationIcon: ImageVector? = null,
    navigationContentDescription: String? = null,
    onNavigationClick: () -> Unit = {},
    centerTitle: Boolean = false,
    actions: @Composable RowScope.() -> Unit = {},
    bottomContent: (@Composable () -> Unit)? = null
) {
    val onHeader = UcbEncuentraTheme.colors.onPrimary
    CurvedHeaderContainer(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 88.dp),
            contentAlignment = Alignment.Center
        ) {
            val titleBlock: @Composable (Modifier) -> Unit = { titleModifier ->
                Column(
                    modifier = titleModifier,
                    horizontalAlignment = if (centerTitle) Alignment.CenterHorizontally else Alignment.Start
                ) {
                    AppTitle(text = title, color = onHeader, textAlign = if (centerTitle) TextAlign.Center else null)
                    if (subtitle != null) {
                        AppBody(text = subtitle, color = onHeader.copy(alpha = SUBTITLE_ALPHA))
                    }
                }
            }
            // Centrado: el título ocupa todo el ancho, así no se corre aunque solo haya botón a un lado.
            if (centerTitle) titleBlock(Modifier.fillMaxWidth().padding(horizontal = 48.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.medium)
            ) {
                if (navigationIcon != null) {
                    IconButton(onClick = onNavigationClick) {
                        Icon(navigationIcon, contentDescription = navigationContentDescription, tint = onHeader)
                    }
                }
                if (centerTitle) Spacer(Modifier.weight(1f)) else titleBlock(Modifier.weight(1f))
                Row(verticalAlignment = Alignment.CenterVertically, content = actions)
            }
        }
        if (bottomContent != null) {
            Box(Modifier.padding(bottom = UcbEncuentraTheme.spacing.large)) { bottomContent() }
        }
    }
}

/** Botón de acción blanco para el header (luna, perfil…). */
@Composable
fun HeaderAction(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(icon, contentDescription = contentDescription, tint = UcbEncuentraTheme.colors.onPrimary)
    }
}

private const val SUBTITLE_ALPHA = 0.7f

@Preview
@Composable
private fun CurvedHeaderPreview() {
    UcbEncuentraTheme {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            CurvedHeader(
                title = "¡Hola, Mateo!",
                subtitle = "Ingeniería • Sede La Paz",
                navigationIcon = DsIcons.Menu,
                actions = { HeaderAction(DsIcons.DarkMode, "Modo oscuro", onClick = {}) }
            )
            CurvedHeader(
                title = "Reportar objeto",
                navigationIcon = DsIcons.ArrowBack,
                centerTitle = true,
                bottomContent = { StepProgressBar(1, 4, stepName = "Categoría", onDark = true) }
            )
        }
    }
}
