import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.androidGradlePlugin)
    compileOnly(libs.jetbrains.kotlinGradlePlugin)
    compileOnly(gradleApi())
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "conventionPlugin.android.library"
            implementationClass = "plugins.AndroidLibraryPlugin"
        }


        register("androidApplication") {
            id = "conventionPlugin.android.application"
            implementationClass = "plugins.AndroidApplicationPlugin"
        }
    }
}