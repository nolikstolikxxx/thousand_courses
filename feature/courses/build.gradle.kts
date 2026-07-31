plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.thousandcourses.feature.courses"

    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.koin.android)

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)

    implementation(libs.androidx.navigation.fragment.ktx)

    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))
}