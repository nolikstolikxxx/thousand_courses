plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.thousandcourses.feature.main"

    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    sourceSets {
        getByName("main") {
            res.srcDirs(
                "src/main/res"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.material)

    implementation(libs.androidx.navigation.ui.ktx)

    implementation(project(":feature:courses"))
    implementation(project(":feature:favorites"))
    implementation(project(":feature:profile"))


}