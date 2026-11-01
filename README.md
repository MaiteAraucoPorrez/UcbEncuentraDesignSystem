# UCB Encuentra · Design System

Librería Kotlin Multiplatform (Android + iOS) con Compose: tokens y tema de la app UCB Encuentra.
Coordenadas: `edu.ucb.ds:shared:1.0.0-SNAPSHOT`.

## Estructura

```text
shared/src/commonMain/
├── kotlin/edu/ucb/ds/
│   ├── theme/        Color.kt, Type.kt, Fonts.kt, Spacing.kt, Shape.kt, Theme.kt
│   ├── components/   (F03) PrimaryButton, AppTextField, AppCard...
│   └── icons/        (F03/F04)
└── composeResources/font/   Inter (Google Fonts)
```

## Tokens

```kotlin
UcbEncuentraTheme.colors.primary          // primary, secondary, background, surface, error (+ onX)
UcbEncuentraTheme.colors.scoreColor(92)   // success >= 80, warning 50-79, neutral < 50
UcbEncuentraTheme.typography.heading      // heading, body, label (Inter)
UcbEncuentraTheme.spacing.large           // small 4 · medium 8 · large 16
UcbEncuentraTheme.shapes.pill             // small, medium, large, pill
```

```kotlin
UcbEncuentraTheme(darkTheme = isDark) {   // por defecto sigue al sistema
    Text("Hola", color = UcbEncuentraTheme.colors.primary, style = UcbEncuentraTheme.typography.heading)
}
```

Los colores salen del PDF de pantallas. El modo oscuro es una propuesta (el PDF no lo define).

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
