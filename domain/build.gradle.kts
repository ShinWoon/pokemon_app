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

    // paging - android 의존하지 않도록 testImplementation을 사용
    testImplementation("androidx.paging:paging-common:3.3.6")

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.inject)
}
