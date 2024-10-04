import com.google.protobuf.gradle.*

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("androidx.navigation.safeargs.kotlin")
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.protobuf)
    id("kotlin-kapt")

//    id("androidx.hilt.navigation.compose")
}

android {
    namespace = "com.shekharhandigol.storage"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

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
}

dependencies {


    // Room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    androidTestImplementation(libs.androidx.room.testing)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)

    // DataStore
    /*implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.preferences.core)
    implementation(libs.androidx.datastore)
    implementation( libs.protobuf.lite)
    implementation(libs.protobuf.javalite)*/

    /*implementation("com.google.protobuf:protobuf-java:3.6.1")
    implementation("io.grpc:grpc-stub:1.15.1")
    implementation("io.grpc:grpc-protobuf:1.15.1")*/


    implementation("com.google.protobuf:protobuf-java:3.22.3")
    implementation("io.grpc:grpc-stub:1.57.0")
    implementation("io.grpc:grpc-protobuf:1.57.0")



    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)


    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.ui.test.junit4)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

protobuf {
    protoc {
        // The artifact spec for the Protobuf Compiler
        artifact = "com.google.protobuf:protoc:3.6.1"
    }
    plugins {
        // Optional: an artifact spec for a protoc plugin, with "grpc" as
        // the identifier, which can be referred to in the "plugins"
        // container of the "generateProtoTasks" closure.
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.15.1"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                // Apply the "grpc" plugin whose spec is defined above, without
                // options. Note the braces cannot be omitted, otherwise the
                // plugin will not be added. This is because of the implicit way
                // NamedDomainObjectContainer binds the methods.
                id("grpc") { }
            }
        }
    }
}
