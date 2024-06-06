pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("com.android.application") version "7.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.5.21" apply false
    id("com.google.devtools.ksp") version "1.6.10-1.0.0-beta01" apply false
}


rootProject.name = "FilmSpot"
include(":app")
 