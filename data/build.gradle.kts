plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.data"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk

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
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }

}

dependencies {
    ksp(DaggerHilt.hiltCompiler)
    implementation(DaggerHilt.hiltAndroid)
    ksp(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)
    implementation(project(Modules.domain))
    implementation(project(Modules.services))

    testImplementation(Testing.junit4)
    testImplementation(Testing.junitAndroidExt)
    testImplementation(Testing.truth)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.mockk)

    testImplementation(Testing.coreTesting)
    testImplementation(Testing.testRunner)
    testImplementation(Testing.robolectric)

}