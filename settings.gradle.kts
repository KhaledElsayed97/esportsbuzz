pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "esportsbuzz"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":features:matches")
include(":core")
include(":domain")
include(":data")
include(":features:onboarding")
include(":features:news")
include(":features:following")
