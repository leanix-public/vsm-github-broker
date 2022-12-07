import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.6"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("io.gitlab.arturbosch.detekt") version "1.21.0"
    id("com.expediagroup.graphql") version "6.3.1"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "net.leanix.vsm"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

ext["snakeyaml.version"] = "1.33"
ext["guava.version"] = "24.1.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.security:spring-security-oauth2-client:5.7.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("net.logstash.logback:logstash-logback-encoder:7.2")
    implementation("com.expediagroup:graphql-kotlin-spring-client:6.3.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("com.ninja-squad:springmockk:3.1.1")

    developmentOnly("io.netty:netty-resolver-dns-native-macos:4.1.85.Final") {
        artifact {
            classifier = "osx-aarch_64"
        }
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2021.0.5")
    }
    dependencies {
        dependency("com.google.guava:guava:24.1.1-jre")
        dependency("io.ktor:ktor-client-apache:2.1.3")
    }
}

detekt {
    autoCorrect = true
    parallel = true
    buildUponDefaultConfig = true
    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.21.0")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

graphql {
    client {
        schemaFile = file("${project.projectDir}/src/main/resources/schemas/github_schema.graphql")
        packageName = "net.leanix.githubbroker.connector.adapter.graphql.data"
        queryFileDirectory = "${project.projectDir}/src/main/resources/queries"
    }
}
