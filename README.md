# UCB Encuentra · Design System

Librería Kotlin Multiplatform (Android + iOS) con Compose: tokens y tema de la app UCB Encuentra.
Coordenadas: `edu.ucb.ds:shared:1.0.0-SNAPSHOT`.

## Estructura

```text
shared/src/commonMain/
├── kotlin/edu/ucb/ds/
│   ├── theme/        Color.kt, Type.kt, Fonts.kt, Spacing.kt, Shape.kt, Theme.kt
│   ├── components/   label, buttons, textfields, cards, surfaces, list, chips, progress, selection
│   └── icons/        DsIcons (flechas, chevrons, check, add)
└── composeResources/font/   Open Sans (Google Fonts, la fuente del Figma)
```

## Tokens

Fuente de verdad: el [Figma de UCB Encuentra](https://www.figma.com/design/A9UfzePVdhqPurX4X7luU7/Sin-t%C3%ADtulo).
La guía de clase (Primary/Secondary/Background/Error, Heading/Body/Label, Small/Medium/Large) es la base y se extiende con lo que usa el diseño.

| Grupo | Tokens |
|---|---|
| `colors` | primary `#002341`, secondary `#A7CCFD` (onSecondary `#2F5681`), tertiary `#3A618C` (links), background `#F8F9FD`, surface `#FFFFFF`, surfaceContainer `#E0E4EF`, surfaceVariant `#E7E8EC`, onSurface `#191C1F`, onSurfaceVariant `#43474E`, outline `#73777E`, outlineVariant `#C3C6CE`, error `#F84034`, success `#2E7D32`, warning `#DF6500`, neutral `#73777E` |
| `typography` (Open Sans) | heading 22/28 Bold · title 20/28 Bold · bodyLarge 16/24 · body 14/20 · label 12/16 SemiBold · overline 12/16 ExtraBold +1.2 |
| `spacing` | small 4 · medium 8 · itemGap 12 · large 16 · screenPadding 20 |
| `shapes` | small 8 · medium 12 · large 16 · extraLarge 24 · pill |

```kotlin
UcbEncuentraTheme(darkTheme = isDark) {   // por defecto sigue al sistema
    Text("Hola", color = UcbEncuentraTheme.colors.onSurface, style = UcbEncuentraTheme.typography.heading)
}
UcbEncuentraTheme.colors.scoreColor(92)   // success >= 80, warning 50-79, neutral < 50
```

El Figma no tiene modo oscuro: `DarkDsColors` es una propuesta.

## Componentes atómicos (F03)

Todos siguen el Figma y tienen `@Preview` en su archivo.

| Paquete | Componentes | Dónde se ve en el Figma |
|---|---|---|
| `components.label` | `AppHeading`, `AppTitle`, `AppBody`, `AppLabel`, `AppOverline` | Todos los textos |
| `components.buttons` | `PrimaryButton`, `SecondaryButton` (`AppButtonSize.Regular` 56 / `Large` 60, íconos, deshabilitado al 50 %) | "Continuar", "Siguiente", "No es lo mío" |
| `components.textfields` | `AppTextField`, `AppTextArea`, `SelectField` (label arriba, placeholder, ícono, error) | Login, Reportar · Ubicación / Atributos |
| `components.cards` | `AppCard` (`Elevated` blanca / `Filled` #E0E4EF) | Login, Home |
| `components.surfaces` | `AppSurface` | Fondo de pantalla |
| `components.list` | `AppList`, `AppListGroup`, `AppListItem` | Accesos rápidos del Home |
| `components.chips` | `AppFilterChip`, `AppChipGroup` (FlowRow) | Mis Reportes |
| `components.progress` | `StepProgressBar` ("Paso x de y", `onDark` para el header) | Wizard de reporte |
| `components.selection` | `ColorSwatchPicker`, `CheckRow` | Reportar · Atributos, Confirmación de entrega |

```kotlin
PrimaryButton("Continuar", onClick = { }, trailingIcon = DsIcons.ArrowForward, modifier = Modifier.fillMaxWidth())
AppTextField(value, onValueChange, label = "Detalle adicional", placeholder = "Ej. Aula A-203")
AppChipGroup(listOf("Todos", "Buscando", "Verificación"), selected = filtro, onSelect = { filtro = it })
StepProgressBar(currentStep = 1, totalSteps = 4, stepName = "Categoría", onDark = true)
```

## Publicar en la app

Clona este repo **al lado** de la app:

```text
Proyecto/
├── ProyectoPrograMovilV1/   (app)
└── design-system/           (este repo)
```

Después de cambiar algo:

```cmd
gradlew.bat :shared:publishAllPublicationsToAppRepoRepository
```

Eso escribe en `../ProyectoPrograMovilV1/ds-repo/`. Haz commit de esa carpeta en el repo de la app (en una rama `feature/...`).

Las versiones de `gradle/libs.versions.toml` deben ser las mismas que las de la app.
En Windows, si git falla por rutas largas: `git config --global core.longpaths true`.
