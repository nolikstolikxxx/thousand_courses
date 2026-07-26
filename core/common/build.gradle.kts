plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.thousandcourses.core.common"

    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }
}