plugins {
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":dataresource"))

    // paging - android 종속 X
    implementation(libs.androidx.paging.common)

    implementation(libs.kotlinx.coroutines.core)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.core)
}