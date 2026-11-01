package edu.ucb.ds.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import edu.ucb.ds.resources.Res
import edu.ucb.ds.resources.opensans_bold
import edu.ucb.ds.resources.opensans_extrabold
import edu.ucb.ds.resources.opensans_regular
import edu.ucb.ds.resources.opensans_semibold
import org.jetbrains.compose.resources.Font

/** Fuentes del Design System: Open Sans, la del Figma (Google Fonts, licencia OFL en shared/OPEN_SANS_OFL.txt). */
object Fonts {

    @Composable
    fun openSans(): FontFamily = FontFamily(
        Font(Res.font.opensans_regular, FontWeight.Normal),
        Font(Res.font.opensans_semibold, FontWeight.SemiBold),
        Font(Res.font.opensans_bold, FontWeight.Bold),
        Font(Res.font.opensans_extrabold, FontWeight.ExtraBold)
    )
}
