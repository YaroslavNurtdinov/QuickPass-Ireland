import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(files(LibrariesForLibs::class.java.protectionDomain.codeSource.location)) // add access to version catalogue in build scripts
    implementation(libs.plugin.android.application)
    implementation(libs.plugin.kotlin.android)
    implementation(libs.plugin.kotlin.jvm)
    implementation(libs.plugin.android.library)
    implementation(libs.plugin.kotlin.compose)
    implementation(libs.plugin.ksp)
    implementation(libs.plugin.hilt)
    implementation(libs.plugin.kotlin.serialization)
}