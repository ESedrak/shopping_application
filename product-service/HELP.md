# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.3.0/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.3.0/gradle-plugin/reference/html/#build-image)
* [Spring Boot Testcontainers support](https://docs.spring.io/spring-boot/docs/3.3.0/reference/html/features.html#features.testing.testcontainers)
* [Testcontainers MongoDB Module Reference Guide](https://java.testcontainers.org/modules/databases/mongodb/)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#data.nosql.mongodb)
* [Testcontainers](https://java.testcontainers.org/)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#web.reactive)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

### Testcontainers support

This project uses [Testcontainers at development time](https://docs.spring.io/spring-boot/docs/3.3.0/reference/html/features.html#features.testing.testcontainers.at-development-time).

Testcontainers has been configured to use the following Docker images:

* [`mongo:latest`](https://hub.docker.com/_/mongo)

Please review the tags of the used images and set them to the same as you're running in production.


### Setup
- Run `./gradlew clean build`
- create a .env file with the following
  - MONGO_DB_USERNAME=
    MONGO_DB_PASSWORD=
    MONGO_DB=
    MONGO_HOST=
    MONGO_PORT=
- Run `docker-compose up -d` to start mongoDB

> [!TIP]
> Run `docker-compose down -v` to remove mongoDB and its associated mounted volume

