plugins {
    id("com.android.library")
    kotlin("android")
    id("com.vanniktech.maven.publish")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
    }

}

dependencies {
    val atrium = "0.12.0"
    val mockK = "1.10.0"
    val spek = "2.0.13"
    testImplementation("ch.tutteli.atrium:atrium-fluent-en_GB:$atrium")
    testImplementation("io.mockk:mockk:$mockK")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spek")
    testImplementation("org.spekframework.spek2:spek-runner-junit5:$spek")
}

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("junit-vintage", "spek2")
    }
}