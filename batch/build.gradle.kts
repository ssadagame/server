plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.jlleitschuh.gradle.ktlint")
}

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework.boot:spring-boot-starter-batch")

    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("mysql:mysql-connector-java:8.0.33")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.9.0")

    implementation("org.springframework.data:spring-data-redis")
    implementation("io.lettuce:lettuce-core:6.2.3.RELEASE")

    implementation(project.project(":domain"))
    implementation(project.project(":domain-adapter"))
    implementation(project.project(":infrastructure:redis"))
    implementation(project.project(":infrastructure:client"))

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.register<Copy>("copyPreCommit") {
    from(file("${rootProject.rootDir}/script/pre-commit"))
    into(file("${rootProject.rootDir}/.git/hooks"))
    eachFile {
        fileMode = 0b111101101
    }
}
tasks.getByPath(":batch:compileKotlin").dependsOn("copyPreCommit")
