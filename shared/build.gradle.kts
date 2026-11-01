import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("maven-publish")
}

// Coordenadas de la librería: edu.ucb.ds:shared:1.0.0-SNAPSHOT
group = "edu.ucb.ds"
version = "1.0.0-SNAPSHOT"

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    android {
        namespace = "edu.ucb.ds.shared"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            // Igual que la app (JVM 11)
            jvmTarget = JvmTarget.JVM_11
        }
        // Necesario para empaquetar las fuentes (composeResources) en el .aar
        androidResources {
            enable = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // "api": la app que use el Design System recibe también estos tipos de Compose.
            api(libs.compose.runtime)
            api(libs.compose.foundation)
            api(libs.compose.material3)
            api(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
        }
    }
}

compose.resources {
    packageOfResClass = "edu.ucb.ds.resources"
}
