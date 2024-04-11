plugins {
	java
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.domaframework.doma.compile") version "2.0.0"
	id("org.domaframework.doma.codegen") version "2.0.0"

}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-security")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-hateoas")

	implementation("io.jsonwebtoken:jjwt:0.9.1")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	implementation("org.modelmapper:modelmapper:3.1.1")

	compileOnly("org.projectlombok:lombok:1.18.30")
	implementation("javax.xml.bind:jaxb-api:2.3.1")
	runtimeOnly("com.oracle.database.jdbc:ojdbc11:23.2.0.0")

	val domaVersion = "2.50.0"
	annotationProcessor("org.seasar.doma:doma-processor:${domaVersion}")
	implementation("org.seasar.doma.boot:doma-spring-boot-starter:1.7.0")
	implementation("org.seasar.doma.boot:doma-spring-boot-autoconfigure:1.7.0")
	implementation("org.seasar.doma:doma-core:${domaVersion}")
	implementation("org.seasar.doma:doma-slf4j:${domaVersion}")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
