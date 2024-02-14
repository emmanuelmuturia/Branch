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

rootProject.name = "Branch"
include(":app")
include(":commons:uilayer")
include(":commons:domainlayer")
include(":navigation")
include(":network:datalayer")
include(":network:domainlayer")
include(":messages:uilayer")
include(":settings:uilayer")
include(":settings:domainlayer")
include(":settings:datalayer")
include(":authentication:uilayer")