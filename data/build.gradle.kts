plugins {
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":dataresource"))

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.hilt.compiler)
    implementation(libs.hilt.core)
}