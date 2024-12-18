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
        jcenter()
        maven { setUrl("https://maven.google.com") }
        maven { setUrl("https://www.jitpack.io") }

    }
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            google()
            mavenCentral()
            gradlePluginPortal()
            maven {
                setUrl("https://www.jitpack.io")
                setUrl("https://jitpack.io")
            }
        }

        rootProject.name = "BeMohabatesh"
        include(":app")

    }
}