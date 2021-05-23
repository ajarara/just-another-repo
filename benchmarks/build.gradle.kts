plugins {
    `java-library`
    kotlin("jvm") version "1.3.61"
}

repositories {
    jcenter()
}

dependencies {
    implementation(project(":lib"))
    implementation("org.openjdk.jmh:jmh-core:1.21")
    implementation(kotlin("stdlib-jdk8"))
    annotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.21")
    testCompile("org.junit.jupiter:junit-jupiter:5.6.0")
}

tasks {

    register("jmh", type=JavaExec::class) {
        dependsOn(":lib:assemble")
        group = "benchmark"
        main = "org.openjdk.jmh.Main"
        classpath = sourceSets["main"].runtimeClasspath
        // To pass parameters ("-h" gives a list of possible parameters)
        args(listOf("-lprof"))
    }
}