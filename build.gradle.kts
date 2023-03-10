val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val prometeus_version: String by project

plugins {
    kotlin("jvm") version "1.8.10"
    id("io.ktor.plugin") version "2.2.4"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
  //id("org.jetbrains.compose") version ("1.0.5")
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers") }
}

dependencies {
    implementation("io.ktor:ktor-server-html-builder-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("org.jetbrains:kotlin-css-jvm:1.0.0-pre.156-kotlin-1.5.0")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-metrics-micrometer-jvm:$ktor_version")
    implementation("io.micrometer:micrometer-registry-prometheus:$prometeus_version")
    implementation("io.ktor:ktor-server-openapi:$ktor_version")
    implementation("io.ktor:ktor-server-compression-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("com.lordcodes.turtle:turtle:0.8.0")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation("org.json:json:20220924") //TODO move to versions

    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.google.code.gson:gson:2.8.8")

    /*
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("androidx.compose.ui:ui-desktop:1.0.5")
        implementation("androidx.compose.material:material:1.0.5")
        implementation("org.jetbrains.compose.runtime:runtime:1.0.5")
        */
}