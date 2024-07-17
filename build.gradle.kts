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

plugins {
    id("com.google.gms.google-services") version "4.4.2" apply false
}
