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
    }
}
//experimental implementation(projects.features...etc)
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "QuickPassIreland"
include(":app")
include(":data")
include(":features:home:data-api")
include(":features:home:domain")
include(":features:home:presentation")
include(":features:quiz:data-api")
include(":features:quiz:domain")
include(":features:quiz:presentation")
include(":core:resources")
include(":features:road_signs:presentation")
include(":features:road_signs:domain")
include(":features:road_signs:data-api")
