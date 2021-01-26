# Alea Technical Test

## Author: Juan Camilo Carrillo Casas

For this task used the following libraries:

* Spring Webflux: nowadays using thread blocking I/O with normal Spring Web is getting a little bit outdated, due to this I have dedicated time to Project Reactor and its development to understand NIO and non-blocking requests. Reactive programming for me has a great experience to work with parallel processing, in a simpler way than using jdk 8 streams, callable, dealing with backpreassure, etc... at the beginning is a little different way of thinking while programming but it worths to learn it for microservices and avoid large resources consumption.
* Spring R2DBC: its support has increased in the last major version of spring boot 2.4.x including its updated library. In my last projects I have moved to this library for the same, avoiding consumptions or threads and waiting time while the database gives an answer back. Spring Data R2DBC is more complete with the latest version, and so on I wanted to do it for this test instead of thread blocking JPA.
* About JUnit: I have worked with JUnit, Mockito, PowerMockito and these libraries, but the testing code I find it more legible and more easy to write with its similar Spock Framework that I wanted to show for TDD development in this project. Another reason to use Spock is due that it uses Groovy which is easier to access private methods and properties. For allowing the last mentioned, in JUnit you have to use PowerMockito and play a little bit with more preprocessing to allow this, and use behaviour testing to verify when some method is required to be called.

## Compile

* Maven 
 
 Java 11 OpenJDK is required
```
// bash
./mvnw package

// cmd
.mvnw.cmd package
```

* Using Docker

`docker build  -t alea.com/pokeapi:latest .`

## Run

`docker run -p 8081:8081 -e SPRING_PROFILES_ACTIVE=loc alea.com/pokeapi:latest`

## Deployment
### Environment Variables
| Name | Description |
|------|------|
|SPRING_PROFILES_ACTIVE|loc,docker,pro|
|PORT|server port, default is https 8080|
