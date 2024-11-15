package extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


internal fun Project.uiDependencies(){

    dependencies {
        add("implementation", versionCatalog().findLibrary("core-ktx").get())
        add("implementation", versionCatalog().findLibrary("appcompat").get())
        add("implementation", versionCatalog().findLibrary("material3").get())
    }
}
internal fun Project.testDependencies(){

    dependencies {
        add("testImplementation", versionCatalog().findLibrary("junit").get())
    }

}


internal fun Project.androidTestImplementation(){

    dependencies {

        add(
            "androidTestImplementation",
            versionCatalog().findLibrary("androidx-test-ext-junit").get()
        )
        add(
            "androidTestImplementation",
            versionCatalog().findLibrary("espresso-core").get()
        )
    }

}