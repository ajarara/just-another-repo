plugins {
    `java-library`
    kotlin("jvm") version "1.3.61"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("org.junit.jupiter:junit-jupiter:5.6.0")
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }

}