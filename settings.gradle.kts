pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        // Repositorio adicional para plugins externos (opcional, pero útil)
        maven { url = uri("https://jitpack.io") }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Repositorio adicional para librerías externas (opcional)
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "MovieApp"
include(":app")

// Si algún día añades más módulos, puedes descomentar y usar líneas como estas:
// include(":data")
// include(":domain")
// include(":core")
