plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.thousandcourses.core.network"

    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }
}

dependencies {
    api(libs.retrofit)
    api(libs.converter.gson)

    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    implementation(libs.koin.android)
}