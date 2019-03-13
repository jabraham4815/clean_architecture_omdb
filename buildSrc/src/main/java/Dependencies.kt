object Deps {
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"

    val androidSupport = "com.android.support:support-v4:${Versions.androidSupport}"
    val androidSupport13 = "com.android.support:support-v13:${Versions.androidSupport}"
    val androidSupportAppcompat = "com.android.support:appcompat-v7:${Versions.androidSupport}"
    val androidSupportRecyclerview =
        "com.android.support:recyclerview-v7:${Versions.androidSupport}"
    val androidSupportCardview = "com.android.support:cardview-v7:${Versions.androidSupport}"
    val androidSupportGridlayout = "com.android.support:gridlayout-v7:${Versions.androidSupport}"
    val androidSupportAnnotations =
        "com.android.support:support-annotations:${Versions.androidSupport}"
    val androidSupportPalette = "com.android.support:palette-v7:${Versions.androidSupport}"
    val androidSupportDesign = "com.android.support:design:${Versions.androidSupport}"
    val androidSupportPercent = "com.android.support:percent:${Versions.androidSupport}"

    val androidLifeCycleExtension = "android.arch.lifecycle:extensions:${Versions.archLifecycle}"
    val androidLifeCycleViewModel = "android.arch.lifecycle:viewmodel:${Versions.archLifecycle}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    val retrofitScalar = "com.squareup.retrofit2:converter-scalars:${Versions.retrofitScalarVersion}"
    val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverterVersion}"

    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    val okhttpurlconnection = "com.squareup.okhttp3:okhttp-urlconnection:${Versions.okhttpVersion}"
    val okhttpLogginginterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"

    val androidSupportConstraintlayout = "com.android.support.constraint:constraint-layout:${Versions.constraintLayOutVersion}"

    val materialDialogs = "com.afollestad.material-dialogs:core:${Versions.materialDialogs}"
    val materialDialogsCommons = "com.afollestad.material-dialogs:commons:${Versions.materialDialogs}"

    val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    val daggerAndroid = "com.google.dagger:dagger-android:${Versions.daggerVersion}"
    val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"

    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}" //kapt
    val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}" //kapt

    val koinAndroid = "org.koin:koin-android:${Versions.koin}"

    val piccasoImage = "com.squareup.picasso:picasso:${Versions.piccaso}"

}