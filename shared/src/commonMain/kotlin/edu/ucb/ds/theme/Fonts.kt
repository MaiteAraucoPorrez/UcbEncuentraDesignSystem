package edu.ucb.ds.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import edu.ucb.ds.resources.Res
import edu.ucb.ds.resources.inter_bold
import edu.ucb.ds.resources.inter_medium
import edu.ucb.ds.resources.inter_regular
import edu.ucb.ds.resources.inter_semibold
import org.jetbrains.compose.resources.Font

/** Fuentes del Design System: Inter (Google Fonts, licencia OFL en shared/INTER_OFL.txt). */
object Fonts {

    @Composable
    fun inter(): FontFamily = FontFamily(
        Font(Res.font.inter_regular, FontWeight.Normal),
        Font(Res.font.inter_medium, FontWeight.Medium),
        Font(Res.font.inter_semibold, FontWeight.SemiBold),
        Font(Res.font.inter_bold, FontWeight.Bold)
    )
}
