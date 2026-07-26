plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.thousandcourses.feature.login"

    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":core:common"))

    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    implementation(libs.koin.android)

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
}