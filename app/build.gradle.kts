plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.tugasfeinfinite"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tugasfeinfinite"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
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
    // Dependensi dasar Compose
    implementation("androidx.compose.ui:ui:1.7.5")
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("androidx.compose.runtime:runtime:1.7.5")
    implementation("androidx.compose.foundation:foundation:1.7.5")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.5")
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    debugImplementation("androidx.compose.ui:ui-tooling:1.7.5")
    implementation("androidx.navigation:navigation-compose:2.8.3")
    implementation ("androidx.compose.material:material-icons-extended:1.3.1")


    // Dependensi lain untuk aplikasi Android
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.activity:activity-compose:1.9.3")

    // Testing dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
}
