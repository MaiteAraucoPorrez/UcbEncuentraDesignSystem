package edu.ucb.ds.components.surfaces

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.ucb.ds.components.label.AppHeading
import edu.ucb.ds.theme.UcbEncuentraTheme

/** Fondo de pantalla del Design System (background #F8F9FD / onBackground). */
@Composable
fun AppSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        color = UcbEncuentraTheme.colors.background,
        contentColor = UcbEncuentraTheme.colors.onBackground,
        content = content
    )
}

@Preview
@Composable
private fun AppSurfacePreview() {
    UcbEncuentraTheme {
        AppSurface(Modifier.fillMaxSize()) {
            AppHeading("Mis reportes")
        }
    }
}
