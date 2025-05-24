plugins {
    alias(libs.plugins.custom.android.application)
}

android {
    namespace = "com.nurtdinov.quickpassireland"
    defaultConfig {
        applicationId = "com.nurtdinov.quickpassireland"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(projects.data)
    implementation(projects.features.home.dataApi)
    implementation(projects.features.home.domain)
    implementation(projects.features.home.presentation)
    implementation(projects.features.quiz.dataApi)
    implementation(projects.features.quiz.domain)
    implementation(projects.features.quiz.presentation)
    implementation(projects.core.resources)
    implementation(projects.features.roadSigns.presentation)
    implementation(projects.features.roadSigns.domain)
    implementation(projects.features.roadSigns.dataApi)

    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.compose)
    implementation(libs.nav.compose)
    implementation(libs.kotlin.serialization.core)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.room)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    implementation(libs.retrofit)
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.interceptor)
    implementation(libs.moshi)
    implementation(libs.moshi.converter)
    implementation(libs.coil)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    testImplementation(libs.room.test)
    testImplementation(libs.okhttp3.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}