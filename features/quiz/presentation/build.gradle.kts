plugins {
    alias(libs.plugins.custom.android.library.compose)
}

android {
    namespace = "com.nurtdinov.presentation"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(projects.features.quiz.domain)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.compose)
    implementation(libs.nav.compose)

    implementation(libs.kotlin.serialization.core)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.coil)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}