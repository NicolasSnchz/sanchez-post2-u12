plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.nicolassnchz.sanchezpost1u11.domain"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}
