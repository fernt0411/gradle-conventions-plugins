package plugins

import com.android.build.api.dsl.ApplicationExtension
import extensions.buildTypesConfiguration
import extensions.javaConfiguration
import extensions.versionCatalog
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.application")
                apply("kotlin-android")
            }

            extensions.configure<ApplicationExtension> {
                compileSdk = versionCatalog().findVersion("compileSdk").get().toString().toInt()
                defaultConfig.apply {
                    minSdk = versionCatalog().findVersion("minSdk").get().toString().toInt()
                    targetSdk = versionCatalog().findVersion("targetSdk").get().toString().toInt()
                    versionName = rootProject.properties["versionName"].toString()

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }
                buildTypesConfiguration(this@configure)
                javaConfiguration(this@configure)

                buildFeatures {
                    compose = true
                }
                composeOptions {
                    kotlinCompilerExtensionVersion = "1.5.1"
                }

                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }


            }
            dependencies {

                add("implementation", versionCatalog().findLibrary("core-ktx").get())
                add("implementation", versionCatalog().findLibrary("lifecycle-runtime-ktx").get())
                add("implementation", versionCatalog().findLibrary("activity-compose").get())
                add("implementation", platform(versionCatalog().findLibrary("compose-bom").get()))
                add("implementation", versionCatalog().findLibrary("ui").get())
                add("implementation", versionCatalog().findLibrary("ui-graphics").get())
                add("implementation", versionCatalog().findLibrary("ui-tooling").get())
                add("implementation", versionCatalog().findLibrary("ui-tooling-preview").get())
                add("implementation", versionCatalog().findLibrary("ui-test-manifest").get())
                add("implementation", versionCatalog().findLibrary("ui-test-junit4").get())
                add("implementation", versionCatalog().findLibrary("material3").get())
                add("implementation", versionCatalog().findLibrary("appcompat").get())
                add("implementation", versionCatalog().findLibrary("material").get())

                add("testImplementation", versionCatalog().findLibrary("junit").get())

                add("androidTestImplementation", platform(versionCatalog().findLibrary("compose-bom").get()))
                add("androidTestImplementation", versionCatalog().findLibrary("androidx-test-ext-junit").get())
                add("androidTestImplementation", versionCatalog().findLibrary("espresso-core").get())
                add("androidTestImplementation", versionCatalog().findLibrary("ui-test-junit4").get())

                add("debugImplementation", versionCatalog().findLibrary("ui-tooling").get())
                add("debugImplementation", versionCatalog().findLibrary("ui-test-manifest").get())
            }

        }
    }
}