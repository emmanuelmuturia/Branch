plugins {
    alias(notation = libs.plugins.com.android.library)
    alias(notation = libs.plugins.org.jetbrains.kotlin.android)
    alias(notation = libs.plugins.com.google.devtools.ksp)
    alias(notation = libs.plugins.com.google.dagger.hilt.android.plugin)
    alias(notation = libs.plugins.org.jetbrains.kotlin.plugin.serialization)
    alias(notation = libs.plugins.com.google.android.libraries.mapsplatform.secrets.gradle.plugin)
}

android {
    namespace = "branch.network.domainlayer"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles(proguardFiles = arrayOf("consumer-rules.pro"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                files = arrayOf(
                    getDefaultProguardFile(name = "proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompilerVersion.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Module(s)...
    val moduleList = listOf(
        "commons:domainlayer"
    )

    moduleList.forEach { module ->
        implementation(project(path = ":$module"))
    }

    // Retrofit2
    implementation(dependencyNotation = libs.retrofit)
    implementation(dependencyNotation = libs.kotlinx.serialization.json)
    implementation(dependencyNotation = libs.retrofit2.kotlinx.serialization.converter)

    //OkHTTP3...
    implementation(dependencyNotation = libs.okhttp)
    implementation(dependencyNotation = libs.logging.interceptor)

    // Dagger-Hilt...
    implementation(dependencyNotation = libs.hilt.android)
    "ksp"(dependencyNotation = libs.hilt.android.compiler)
    implementation(dependencyNotation = libs.androidx.hilt.navigation.compose)

    // Timber...
    implementation(dependencyNotation = libs.timber)

    // Android...
    implementation(dependencyNotation = libs.androidx.core.ktx)
    implementation(dependencyNotation = libs.appcompat)
    implementation(dependencyNotation = libs.material)

    // Testing...
    testImplementation(dependencyNotation = libs.junit)
    androidTestImplementation(dependencyNotation = libs.androidx.junit)
    androidTestImplementation(dependencyNotation = libs.androidx.espresso.core)

}