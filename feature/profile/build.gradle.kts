plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.thousandcourses.feature.profile"

    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }
}

dependencies {

    implementation(project(":core:common"))
}