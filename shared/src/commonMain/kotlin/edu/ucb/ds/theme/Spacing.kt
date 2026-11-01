package edu.ucb.ds.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Token de espaciado: Small (4dp), Medium (8dp), Large (16dp). */
@Immutable
data class DsSpacing(
    val small: Dp = 4.dp,
    val medium: Dp = 8.dp,
    val large: Dp = 16.dp
)

val DefaultDsSpacing = DsSpacing()
