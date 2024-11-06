plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.parcelize")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.huawei.agconnect")
    id("androidx.room")
}

android {
    namespace = "me.yihtseu.messenger"
    compileSdk = 35

    defaultConfig {
        applicationId = "me.yihtseu.messenger"
        minSdk = 33
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_22
        targetCompatibility = JavaVersion.VERSION_22
    }

    kotlinOptions {
        jvmTarget = "22"
    }

    buildFeatures {
        compose = true
    }

    dynamicFeatures += listOf(
        ":extension:kritor",
        ":extension:matrix"
    )

    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    implementation(project(":view:monet"))
    implementation(project(":view:one"))
    implementation(project(":view:ark"))
    /// Common
    // App Core
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.appcompat:appcompat-resources:1.7.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    // Jetpack Activity
    implementation("androidx.activity:activity-ktx:1.9.3")
    implementation("androidx.activity:activity-compose:1.9.3")

    // Jetpack Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-service:2.8.7")
    implementation("androidx.lifecycle:lifecycle-process:2.8.7")
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:2.8.7")
    testImplementation("androidx.lifecycle:lifecycle-runtime-testing:2.8.7")

    // Jetpack Work
    implementation("androidx.work:work-runtime-ktx:2.10.0")
    implementation("androidx.work:work-multiprocess:2.10.0")
    androidTestImplementation("androidx.work:work-testing:2.10.0")

    // Jetpack Futures
    implementation("androidx.concurrent:concurrent-futures-ktx:1.2.0")

    // Jetpack Startup
    implementation("androidx.startup:startup-runtime:1.2.0")

    // Kotlin Core
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-properties:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.21")

    /// Jetpack Hilt
    implementation("com.google.dagger:hilt-android:2.52")
    ksp("com.google.dagger:dagger-compiler:2.52")
    ksp("com.google.dagger:hilt-compiler:2.52")

    /// UI
    // Jetpack Splash Screen
    implementation("androidx.core:core-splashscreen:1.0.1")
    // Jetpack Compose
    implementation(platform("androidx.compose:compose-bom:2024.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-adaptive-navigation-suite")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.10.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Jetpack Material3 Adaptive
    implementation("androidx.compose.material3.adaptive:adaptive:1.0.0")
    implementation("androidx.compose.material3.adaptive:adaptive-layout:1.0.0")
    implementation("androidx.compose.material3.adaptive:adaptive-navigation:1.0.0")

    // Jetpack Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.8.3")

    // Jetpack Emoji2
    implementation("androidx.emoji2:emoji2:1.5.0")
    implementation("androidx.emoji2:emoji2-views:1.5.0")
    implementation("androidx.emoji2:emoji2-views-helper:1.5.0")
    implementation("androidx.emoji2:emoji2-emojipicker:1.5.0")

    // Jetpack Browser
    implementation("androidx.browser:browser:1.8.0")

    // Accompanist
    implementation("com.google.accompanist:accompanist-permissions:0.36.0")
    implementation("com.google.accompanist:accompanist-adaptive:0.36.0")

    // Coil Image
    implementation("io.coil-kt.coil3:coil-compose:3.0.0-rc01")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.0-rc01")

    // Lottie Animation
    implementation("com.airbnb.android:lottie-compose:6.5.2")

    /// Data
    // Jetpack Room
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")
    testImplementation("androidx.room:room-testing:2.6.1")

    // Jetpack Paging
    implementation("androidx.paging:paging-runtime-ktx:3.3.2")
    testImplementation("androidx.paging:paging-common-ktx:3.3.2")
    implementation("androidx.paging:paging-compose:3.3.2")

    // Jetpack Preference
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // Ktor
    implementation("io.ktor:ktor-client-core:3.0.0")
    implementation("io.ktor:ktor-client-cio:3.0.0")
    implementation("io.ktor:ktor-client-logging:3.0.0")
    implementation("io.ktor:ktor-client-resources:3.0.0")
    implementation("io.ktor:ktor-client-content-negotiation:3.0.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.0")
    implementation("io.ktor:ktor-serialization-kotlinx-xml:3.0.0")
    implementation("io.ktor:ktor-serialization-kotlinx-protobuf:3.0.0")
    implementation("io.ktor:ktor-client-encoding:3.0.0")
    implementation("io.ktor:ktor-client-call-id:3.0.0")
    testImplementation("io.ktor:ktor-client-mock:3.0.0")

    /// Media
    // Jetpack Telecom
    implementation("androidx.core:core-telecom:1.0.0-alpha03")

    // Jetpack Media3
    implementation("androidx.media3:media3-exoplayer:1.4.1")
    implementation("androidx.media3:media3-exoplayer-dash:1.4.1")
    implementation("androidx.media3:media3-ui:1.4.1")
    implementation("androidx.media3:media3-session:1.4.1")

    // Jetpack CameraX
    implementation("androidx.camera:camera-camera2:1.4.0")
    implementation("androidx.camera:camera-lifecycle:1.4.0")
    implementation("androidx.camera:camera-video:1.4.0")
    implementation("androidx.camera:camera-view:1.4.0")
    implementation("androidx.camera:camera-extensions:1.4.0")

    // Webrtc
    implementation("io.getstream:stream-webrtc-android:1.1.0")

    // Google Shortcut
    implementation("androidx.core:core-google-shortcuts:1.1.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")
    implementation("com.google.firebase:firebase-messaging-directboot")
    implementation("com.google.firebase:firebase-inappmessaging-ktx")

    // Google Play
    implementation("com.google.android.gms:play-services-base:18.5.0")
    implementation("com.google.mlkit:barcode-scanning:17.3.0")
    implementation("com.google.android.play:feature-delivery-ktx:2.1.0")
    implementation("com.google.apis:google-api-services-drive:v3-rev20241014-2.0.0")

    // AppGallery Connect
    implementation("com.huawei.agconnect:agconnect-core:1.9.1.302")
    implementation("com.huawei.agconnect:agconnect-auth:1.9.1.302")
    implementation("com.huawei.agconnect:agconnect-crash:1.9.1.302")
    implementation("com.huawei.agconnect:agconnect-appmessaging:1.9.1.302")

    // Huawei Mobile Service
    implementation("com.huawei.hms:base:6.12.0.300")
    implementation("com.huawei.hms:scan:2.12.0.301")
    implementation("com.huawei.hms:hwid:6.12.0.300")
    implementation("com.huawei.hms:push:6.12.0.300")
    implementation("com.huawei.hms:drive:5.2.0.300")
    implementation("com.huawei.hms:dynamicability:1.0.18.300")
    
    // Matrix SDK
    implementation("org.matrix.android:matrix-android-sdk2:1.6.10")
}