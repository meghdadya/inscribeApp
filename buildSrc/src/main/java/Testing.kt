object Testing {
    private const val junitVersion = "4.13.2"
    const val junit4 = "junit:junit:$junitVersion"

    private const val robolectricVersion = "4.11.1"

   const val robolectric = "org.robolectric:robolectric:$robolectricVersion"

    private const val junitAndroidExtVersion = "1.1.3"
    const val junitAndroidExt = "androidx.test.ext:junit:$junitAndroidExtVersion"

    private const val coroutinesTestVersion = "1.8.0"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion"

    private const val truthVersion = "1.4.2"
    const val truth = "com.google.truth:truth:$truthVersion"

    private const val mockkVersion = "1.13.10"
    const val mockk = "io.mockk:mockk:$mockkVersion"
    const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"


    private const val mockWebServerVersion = "4.9.3"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$mockWebServerVersion"

    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Compose.composeVersion}"

    const val hiltTesting = "com.google.dagger:hilt-android-testing:${DaggerHilt.version}"

    private const val testRunnerVersion = "1.4.0"
    const val testRunner = "androidx.test:runner:$testRunnerVersion"

    private const val testCoreTestingversion = "2.0.0-alpha1"

    const val coreTesting = "androidx.arch.core:core-testing:$testCoreTestingversion"

}