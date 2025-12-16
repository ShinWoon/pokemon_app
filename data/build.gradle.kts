plugins {
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":dataresource"))

    // paging - android 의존하지 않도록 testImplementation을 사용
    testImplementation(libs.androidx.paging.common)

    implementation(libs.kotlinx.coroutines.core)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.core)
}