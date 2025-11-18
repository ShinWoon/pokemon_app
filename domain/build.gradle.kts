plugins {
    id("kotlin")
    id("kotlin-kapt")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {
    implementation(project(":dataresource"))

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.inject)
}
