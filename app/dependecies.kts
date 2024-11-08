ext {
    daggerVersion = '2.24'
    appcompatVersion = '1.1.0-alpha04'
//    junitVersion = '4.12'
    androidxTestRunnerVersion = '1.1.1'
//    espressoCoreVersion = '3.1.1'
//    constraintLayoutVersion = '2.0.0-alpha4'
//    checkstyleVersion = '8.19'
//    androidxCoreVersion = '1.0.2'
//    robolectricVersion='4.3'//3.8
//    rxJavaVersion = '2.2.10'
//    rxAndroidVersion = '2.1.1'
//    mockitoVersion = '2.25.0'
//    fragmentVersion = '1.2.0'
//    retrofitVersion = '2.9.0'
//    gsonVersion = '2.8.6'
//    moshiVersion = '1.11.0'

//    test = [
//            junit: "junit:junit:${junitVersion}",
//            robolectric: "org.robolectric:robolectric:4.3",
//            resources: "org.robolectric:resources:${robolectricVersion}",
//            sandbox: "org.robolectric:sandbox:${robolectricVersion}",
//            utils: "org.robolectric:utils:${robolectricVersion}",
//            mockito: "org.mockito:mockito-core:${mockitoVersion}",
//            fragment: "androidx.fragment:fragment-testing:${fragmentVersion}",
//            shadowPlay: "org.robolectric:shadows-play-services:3.1.3",//3.1
//            shadowCore: "org.robolectric:shadows-core-v16:3.1.3",//3.1.3
//
//    ]
//    androidx = [
//            appcompat       : "androidx.appcompat:appcompat:${appcompatVersion}",
//            constraintLayout: "com.android.support.constraint:constraint-layout:${constraintLayoutVersion}",
//            core            : "androidx.core:core:${androidxCoreVersion}",
//            testRunner      : "androidx.test:runner:${androidxTestRunnerVersion}",
//            testEspressoCore: "androidx.test.espresso:espresso-core:${espressoCoreVersion}"
//    ]
    di = [
        daggerApi             : "com.google.dagger:dagger:${daggerVersion}",
    daggerSupport         : "com.google.dagger:dagger-android-support:${daggerVersion}",
    daggerProcessor       : "com.google.dagger:dagger-compiler:${daggerVersion}",
    daggerAndroidProcessor: "com.google.dagger:dagger-android-processor:${daggerVersion}",
    ]
//    rx = [
//            java   : "io.reactivex.rxjava2:rxjava:${rxJavaVersion}",
//            android: "io.reactivex.rxjava2:rxandroid:${rxAndroidVersion}"
//    ]
//    http = [
//            retrofit: "com.squareup.retrofit2:retrofit:${retrofitVersion}",
//            moshiModule: "com.squareup.retrofit2:converter-moshi:${retrofitVersion}",
//            gsonModule: "com.squareup.retrofit2:converter-gson:${retrofitVersion}",
//            moshi: "com.squareup.moshi:moshi:${moshiVersion}",
//            gson: "com.google.code.gson:gson:${gsonVersion}"
//    ]

}