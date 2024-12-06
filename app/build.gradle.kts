plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id("kotlin-android")
}

android {
    namespace = "com.example.justicelawmovil"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.justicelawmovil"
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
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1") // Considera una versión más reciente
    implementation("androidx.navigation:navigation-compose:2.8.2")
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.navigation:navigation-compose:2.7.4")
    implementation("androidx.compose.material3:material3:1.3.0-beta04")
    implementation("com.google.accompanist:accompanist-pager:0.30.1")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.30.1")
    implementation ("androidx.compose.runtime:runtime-livedata:1.4.0")
    implementation ("androidx.compose.material:material-icons-extended:1.4.0")
    implementation ("com.google.accompanist:accompanist-permissions:0.28.0")
    implementation("io.coil-kt:coil-compose:2.4.0") // Verifica la última versión disponible
// Retrofit library
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

// OkHttp library for making HTTP requests
    implementation ("com.squareup.okhttp3:okhttp:4.10.0")

// Gson library for JSON parsing
    implementation ("com.google.code.gson:gson:2.8.9")

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.protolite.well.known.types)
    implementation(libs.androidx.webkit)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

}


