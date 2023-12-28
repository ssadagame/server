plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("plugin.allopen")
    id("org.jlleitschuh.gradle.ktlint")
}

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")

    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("org.hibernate:hibernate-core:6.4.1.Final")

    implementation(project.project(":domain"))
    implementation(project.project(":infrastructure:redis"))
    implementation(project.project(":infrastructure:client"))

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
}

tasks.register<Copy>("copyPreCommit") {
    from(file("${rootProject.rootDir}/script/pre-commit"))
    into(file("${rootProject.rootDir}/.git/hooks"))
    eachFile {
        fileMode = 0b111101101
    }
}
tasks.getByPath(":domain-adapter:compileKotlin").dependsOn("copyPreCommit")
