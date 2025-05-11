import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")
}
android {
    compileSdk = Const.TargetSdk
    defaultConfig {
        minSdk = Const.MinSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}


dependencies {
    implementation(customLibs.hilt)
    ksp(customLibs.hilt.compiler)

    implementation(customLibs.room)
    ksp(customLibs.room.compiler)
    implementation(customLibs.room.ktx)
    implementation(customLibs.room.paging)

    implementation(customLibs.moshi)
    implementation(customLibs.coroutines)
    implementation(customLibs.datastore.preferences)
    implementation(customLibs.kotlin.serialization.core)
    implementation(customLibs.kotlinx.collections.immutable)
    implementation(customLibs.androidx.core.ktx)

    testImplementation(customLibs.room.test)
    testImplementation(customLibs.coroutines.test)
    testImplementation(customLibs.junit)
    androidTestImplementation(customLibs.androidx.junit)
}
