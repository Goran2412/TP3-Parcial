plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.parcialtp3"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.parcialtp3"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    kapt {
        correctErrorTypes = true
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    //implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2") duplicado
    //implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2") duplicado
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.recyclerview:recyclerview-selection:1.2.0-alpha01")



    //Activity-KTX
    implementation("androidx.activity:activity-ktx:1.7.2")

    ///Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")

    // Navigation libraries
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")

    // Retrofit
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.9.3")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    //DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.0-alpha04")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-compiler:2.44.2")

    // Picasso
    implementation("com.squareup.picasso:picasso:2.71828")

    //Preference
    implementation ("androidx.preference:preference:1.2.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")

    //Room
    implementation ("androidx.room:room-runtime:2.6.0")
    kapt ("androidx.room:room-compiler:2.6.0")
    implementation ("androidx.room:room-ktx:2.6.0")

    //Coil
    implementation ("io.coil-kt:coil:2.2.2")

}