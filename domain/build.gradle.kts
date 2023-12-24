plugins {
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
}

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
}

tasks.register<Copy>("copyPreCommit") {
    from(file("${rootProject.rootDir}/script/pre-commit"))
    into(file("${rootProject.rootDir}/.git/hooks"))
    eachFile {
        fileMode = 0b111101101
    }
}
tasks.getByPath(":domain:compileKotlin").dependsOn("copyPreCommit")
