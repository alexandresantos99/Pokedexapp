import org.gradle.api.JavaVersion

object Config {

    val compileSdk = 34
    val minSdk = 21
    val targetSdk = 34
    val javaVersion = JavaVersion.VERSION_1_8

}

object Versions {

    //compose dependencies
    val androidxCoreKtx = "1.10.1"
    val androidxLifecycleRuntimeKtx = "2.6.1"
    val androidxActivityCompose = "1.7.2"
    val androidxCompose = "2022.10.00"
    val navigation_testing = "2.5.3"
    val koin = "3.4.0"
    val navigationCompose = "2.6.0"

    //network
    val retrofit = "2.9.0"
    val http = "4.11.0"
    val log_interceptor = "4.10.0"
    val serialization_json = "1.5.1"

    //coil dependencies
    val coil = "2.4.0"
    val coil_accompanist = "0.7.0"
    val pallete = "1.0.0"

    //test
    val junit = "4.13.2"
    val junitext = "1.1.5"
    val espressoCore = "3.5.1"
    val mockitoKotlin = "2.2.11"
    val mockk = "1.13.2"
    val kotlinxCoroutines = "1.7.3"
    val androidxTestRunner = "1.5.2"
    val androidxTestRules = "1.5.0"
    val androidxCoreTesting = "2.2.0"

}

object Deps {

    val androidx_core_ktx = "androidx.core:core-ktx:${Versions.androidxCoreKtx}"
    val androidx_lifecycle =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycleRuntimeKtx}"
    val androidx_activity_compose =
        "androidx.activity:activity-compose:${Versions.androidxActivityCompose}"
    val androidx_compose_bom_platform = "androidx.compose:compose-bom:${Versions.androidxCompose}"
    val androidx_compose_ui = "androidx.compose.ui:ui"
    val androidx_compose_ui_graphics = "androidx.compose.ui:ui-graphics"
    val androidx_compose_ui_preview = "androidx.compose.ui:ui-tooling-preview:1.4.3"
    val androidx_compose_material3 = "androidx.compose.material3:material3"

    val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    val coil_accompanist = "com.google.accompanist:accompanist-coil:${Versions.coil_accompanist}"
    val pallete = "androidx.palette:palette-ktx:${Versions.pallete}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val http = "com.squareup.okhttp3:okhttp:${Versions.http}"
    val logInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.log_interceptor}"
    val serialization_json =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization_json}"

    val koin = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigationCompose}"

    val testelib_compose_manifest = "androidx.compose.ui:ui-test-manifest"
    val testlib_compose_junit_ui = "androidx.compose.ui:ui-test-junit4"
    val testlib_navigation_test =
        "androidx.navigation:navigation-testing:${Versions.navigation_testing}"
    val testlib_webserver = "com.squareup.okhttp3:mockwebserver:${Versions.http}"
    val test_lib_koin = "io.insert-koin:koin-test-junit:${Versions.koin}"
    val testlib_junit = "junit:junit:${Versions.junit}"
    val testlib_junitext = "androidx.test.ext:junit:${Versions.junitext}"
    val testandroidx_espressocore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    val testlib_mockito = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    val testlib_mockk = "io.mockk:mockk:${Versions.mockk}"
    val testlib_mockk_android = "io.mockk:mockk-android:${Versions.mockk}"
    val testlib_coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinxCoroutines}"
    val lib_kt_coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutines}"
    val testandroidx_rules = "androidx.test:rules:${Versions.androidxTestRules}"
    val testandroidx_runner = "androidx.test:runner:${Versions.androidxTestRunner}"
    val testlib_archComponent = "androidx.arch.core:core-testing:${Versions.androidxCoreTesting}"

}