plugins {
    alias(libs.plugins.custom.android.library)
}

android {
    namespace = "com.nurtdinov.data"
}

dependencies {
    implementation(projects.features.home.dataApi)
    implementation(projects.features.quiz.dataApi)
}