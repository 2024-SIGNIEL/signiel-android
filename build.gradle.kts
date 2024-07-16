buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.jetbrains.kotlin.gradle.plugin)
        classpath(libs.kotlin.serialization)
    }
}
