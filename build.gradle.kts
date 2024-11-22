
plugins {
    java
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"

    val kotlinVersion = "1.9.22"
    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

group = "com.easyup"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21 //JavaLanguageVersion.of(17)
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    runtimeOnly("org.postgresql:postgresql")

    val mapstructVersion = "1.6.3"
    kapt("org.mapstruct:mapstruct-processor:1.6.3")

    implementation("org.mapstruct:mapstruct:1.6.3")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
}

kapt {
    keepJavacAnnotationProcessors = true
    arguments {
        arg("mapstruct.defaultComponentModel", "spring")
        arg("mapstruct.unmappedTargetPolicy", "IGNORE")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

//tasks.withType<JavaCompile> {
//    options.annotationProcessorPath = configurations.kapt.get()
//}
