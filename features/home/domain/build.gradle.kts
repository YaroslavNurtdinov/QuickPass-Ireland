plugins {
    alias(libs.plugins.custom.kotlin.library)
}

dependencies {
    implementation(projects.features.home.dataApi)
}
