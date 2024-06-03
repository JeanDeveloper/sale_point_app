plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.jchunga.salesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jchunga.salesapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.runtime)

    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.runtime.livedata)

    //Navigation and Material Icons
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material.icons.extended)

    //Coil
    implementation(libs.coil.compose)
    implementation(libs.accompanist.coil)
    implementation(libs.coil.svg)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material3.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //ROOM
    implementation(libs.androidx.room.ktx)
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")


    //Hilt and Coroutines
    implementation(libs.hilt.android)
    implementation(libs.klaxon)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)


    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

    //DataStore
    implementation(libs.androidx.datastore.preferences)

    implementation(libs.maps.compose)
    implementation(libs.play.services.maps)

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}