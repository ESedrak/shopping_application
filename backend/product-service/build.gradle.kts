val openApi by extra("2.5.0")
val restAssured by extra("5.4.0")
val junit by extra("5.10.2")
val cucumber by extra("7.18.0")

plugins {
	java
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "shopping.app"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
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
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$openApi")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:$openApi")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:mongodb")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("io.rest-assured:rest-assured:$restAssured")

	testImplementation(platform("org.junit:junit-bom:$junit"))
	testImplementation(platform("io.cucumber:cucumber-bom:$cucumber"))

	testImplementation("io.cucumber:cucumber-java")
	testImplementation("io.cucumber:cucumber-junit-platform-engine")
	testImplementation("org.junit.platform:junit-platform-suite")
	testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<Test> {
	useJUnitPlatform()
	// Work around. Gradle does not include enough information to disambiguate
	// between different examples and scenarios.
	systemProperty("cucumber.junit-platform.naming-strategy", "long")
}
