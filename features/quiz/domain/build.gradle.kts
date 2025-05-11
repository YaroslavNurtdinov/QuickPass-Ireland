plugins {
    alias(libs.plugins.custom.kotlin.library)
}

dependencies {
    implementation(projects.features.quiz.dataApi)
}