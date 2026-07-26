plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.thousandcourses.feature.favorites"

    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }
}

dependencies {

    implementation(project(":core:common"))
    implementation(project(":core:database"))
}