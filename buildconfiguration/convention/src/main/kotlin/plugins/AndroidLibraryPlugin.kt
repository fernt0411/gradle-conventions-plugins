package plugins

import com.android.build.api.dsl.LibraryExtension
import extensions.androidTestImplementation
import extensions.buildTypesConfiguration
import extensions.javaConfiguration
import extensions.testDependencies
import extensions.uiDependencies
import extensions.versionCatalog
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = versionCatalog().findVersion("compileSdk").get().toString().toInt()
                defaultConfig.apply {
                    minSdk = versionCatalog().findVersion("minSdk").get().toString().toInt()
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }
                buildTypesConfiguration(this@configure)
                javaConfiguration(this@configure)

                uiDependencies()
                testDependencies()
                androidTestImplementation()
            }
        }
    }
}