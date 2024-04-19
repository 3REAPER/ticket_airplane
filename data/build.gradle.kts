plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
dependencies {
    implementation(project(":domain"))

    implementation("javax.inject:javax.inject:1")
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0")
    api("com.squareup.okhttp3:okhttp:4.9.3")
    api("com.squareup.okhttp3:mockwebserver:4.9.3")
    api("com.squareup.okhttp3:logging-interceptor:4.9.3")
}
