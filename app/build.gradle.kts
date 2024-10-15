plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt.android)

    id("kotlin-kapt")
}

android {
    namespace = "com.shekharhandigol.crm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.shekharhandigol.crm"
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
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":theme"))
    implementation(project(":features:auth"))
    implementation(project(":features:profile"))
    implementation(project(":features:home"))
    implementation(project(":features:attendanceSummary"))
    implementation(project(":features:salarySummary"))
    implementation(project(":common")) // TODO("this needs to go i think!!")


    implementation(libs.androidx.runtime.android)
    implementation(libs.androidx.navigation.runtime.ktx)

    implementation(libs.androidx.runtime)
    implementation(libs.androidx.runtime.livedata)

    implementation(libs.hilt.android)
    implementation(libs.play.services.tasks)
    implementation(libs.play.services.base)
    implementation(libs.play.services.auth)
    implementation(libs.firebase.auth.ktx)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}