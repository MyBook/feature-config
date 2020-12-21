plugins {
    id("com.android.application")
    kotlin("android")
}

android {

    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(29)
        targetSdkVersion(30)
    }

}

dependencies {

    val koin = "2.1.6"
    implementation(project(":feature-config"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.20")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("org.koin:koin-androidx-ext:$koin")
}
