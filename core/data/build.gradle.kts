plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.nicolassnchz.sanchezpost2u12.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(libs.kotlinx.coroutines.core)
}
