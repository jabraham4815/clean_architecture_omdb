import org.gradle.api.JavaVersion

object Versions {
    const val sdkMin = 21
    const val sdkTarget = 28
    const val sdkCompile = 28
    const val versionCode = 1
    const val versionName = "1.0"
    const val renderscriptTargetApi = 26

    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8

    const val kotlin = "1.3.0"
    const val kotlinCoroutines = "1.0.0"

    const val gsonConverterVersion = "2.3.0"
    const val gsonVersion = "2.8.0"

    const val retrofitVersion = "2.3.0"
    const val retrofitScalarVersion = "2.3.0"
    const val okhttpVersion = "3.11.0"

    const val constraintLayOutVersion = "1.1.3"

    const val daggerVersion = "2.13"

    const val materialDialogs ="0.9.2.3"

    const val androidSupport = "28.0.0"

    const val archLifecycle = "1.1.1"

    const val playServices = "15.0.1"
    const val firebaseCore = "15.0.2"
    const val firebaseMessaging = "15.0.2"

    const val koin = "0.9.0"

    const val piccaso = "2.5.2"

    const val mockito = "2.8.9"
    const val junit = "4.12"
    const val mockk = "1.8.10.kotlin13"
    val powermock = "1.6.5"
    val powermockrunner = "4:1.6.5"
}