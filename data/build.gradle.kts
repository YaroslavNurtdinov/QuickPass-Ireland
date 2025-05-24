plugins {
    alias(libs.plugins.custom.android.library)
}

android {
    namespace = "com.nurtdinov.data"
}

dependencies {
    implementation(projects.features.home.dataApi)
    implementation(projects.features.quiz.dataApi)
    implementation(projects.features.quiz.domain)
    implementation(projects.features.roadSigns.domain)
    implementation(projects.features.roadSigns.dataApi)

    implementation(libs.moshi)
    implementation(libs.moshi.converter)
    implementation("com.squareup.moshi:moshi-kotlin:1.15.2") // або остання версія
}