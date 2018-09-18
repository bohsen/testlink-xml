import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "dk.rn.its"
version = "1.0-SNAPSHOT"

plugins {
    java
    kotlin("jvm") version "1.2.70"
}

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("no.tornado","tornadofx", "1.7.16")
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}