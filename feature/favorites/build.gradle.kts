plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.thousandcourses.feature.favorites"

    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.koin.android)

    implementation(project(":core:common"))
    implementation(project(":core:database"))

    implementation(project(":feature:courses"))
}

