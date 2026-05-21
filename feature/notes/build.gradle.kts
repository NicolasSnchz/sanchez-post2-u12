plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

apply(plugin = "jacoco")

android {
    namespace = "com.nicolassnchz.sanchezpost2u12.feature.notes"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)

    implementation(libs.kotlinx.coroutines.android)

    testImplementation(project(":core:domain"))
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn("testDebugUnitTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val excludes = listOf(
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*"
    )

    val kotlinClasses = fileTree("${layout.buildDirectory.get()}/tmp/kotlin-classes/debug") {
        exclude(excludes)
    }

    classDirectories.setFrom(files(kotlinClasses))
    sourceDirectories.setFrom(files("src/main/java"))
    executionData.setFrom(
        fileTree(layout.buildDirectory.get()) {
            include("outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec")
            include("jacoco/testDebugUnitTest.exec")
        }
    )
}
