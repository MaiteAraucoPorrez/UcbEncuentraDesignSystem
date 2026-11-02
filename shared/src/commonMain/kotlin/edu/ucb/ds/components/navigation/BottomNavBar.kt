package edu.ucb.ds.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.ucb.ds.components.label.AppLabel
import edu.ucb.ds.icons.DsIcons
import edu.ucb.ds.theme.UcbEncuentraTheme

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val selected: Boolean,
    val onClick: () -> Unit
)

/** Botón redondo central (FAB "+" para Reportar en la barra del estudiante). */
data class BottomNavCenterAction(
    val icon: ImageVector,
    val contentDescription: String,
    val onClick: () -> Unit
)

/**
 * Barra inferior del Figma. Con [centerAction] el FAB va al medio de los ítems (estudiante: 2 + FAB + 2);
 * sin él, solo ítems (staff: Panel, Inventario, Reclamos, Alertas, Perfil).
 */
@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    modifier: Modifier = Modifier,
    centerAction: BottomNavCenterAction? = null
) {
    val shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
    val half = items.size / 2
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 8.dp, shape = shape)
            .background(UcbEncuentraTheme.colors.background, shape)
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(BAR_HEIGHT)
                .padding(horizontal = UcbEncuentraTheme.spacing.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEachIndexed { index, item ->
                if (centerAction != null && index == half) {
                    CenterButton(centerAction, Modifier.weight(1f))
                }
                NavItem(item, Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun NavItem(item: BottomNavItem, modifier: Modifier) {
    val c = UcbEncuentraTheme.colors
    val contentColor = if (item.selected) c.onSecondary else c.onSurfaceVariant
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .clip(UcbEncuentraTheme.shapes.pill)
                .then(if (item.selected) Modifier.background(c.secondary) else Modifier)
                .clickable(onClick = item.onClick)
                .semantics {
                    selected = item.selected
                    role = Role.Tab
                }
                .padding(horizontal = UcbEncuentraTheme.spacing.itemGap, vertical = UcbEncuentraTheme.spacing.small),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(UcbEncuentraTheme.spacing.small)
        ) {
            Icon(item.icon, contentDescription = null, tint = contentColor, modifier = Modifier.size(20.dp))
            AppLabel(text = item.label, color = contentColor)
        }
    }
}

@Composable
private fun CenterButton(action: BottomNavCenterAction, modifier: Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .offset(y = -FAB_RAISE)
                .size(FAB_SIZE)
                .shadow(elevation = 6.dp, shape = CircleShape)
                .background(UcbEncuentraTheme.colors.primary, CircleShape)
                .clickable(onClick = action.onClick)
                .semantics {
                    contentDescription = action.contentDescription
                    role = Role.Button
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(action.icon, contentDescription = null, tint = UcbEncuentraTheme.colors.onPrimary)
        }
    }
}

private val BAR_HEIGHT = 84.dp
private val FAB_SIZE = 56.dp
private val FAB_RAISE = 24.dp

@Preview
@Composable
private fun BottomNavBarPreview() {
    UcbEncuentraTheme {
        Column(verticalArrangement = Arrangement.spacedBy(32.dp), modifier = Modifier.padding(top = 32.dp)) {
            BottomNavBar(
                items = listOf(
                    BottomNavItem("Inicio", DsIcons.Inbox, selected = true, onClick = {}),
                    BottomNavItem("Buscar", DsIcons.Location, selected = false, onClick = {}),
                    BottomNavItem("Alertas", DsIcons.Error, selected = false, onClick = {}),
                    BottomNavItem("Perfil", DsIcons.Check, selected = false, onClick = {})
                ),
                centerAction = BottomNavCenterAction(DsIcons.Add, "Reportar", onClick = {})
            )
            BottomNavBar(
                items = listOf("Panel", "Inventario", "Reclamos", "Alertas", "Perfil").mapIndexed { i, label ->
                    BottomNavItem(label, DsIcons.Inbox, selected = i == 0, onClick = {})
                }
            )
        }
    }
}
