# Technical Test

## Author
#### Juan Camilo Carrillo Casas 
#### juancamilocasas@gmail.com

## Architecture
| Name | Description | Port |
|------|------|------|
|pokemon-search|microservice which retrieves the top 5 pokemon| 8081|
|discovery-server|Eureka which looks up microservices and register them| 8761|
|gateway|API Gateway using Spring Cloud Gateway|8080|

## Libraries used
* Maven (multi-module)
* Spring webflux
* Spring R2DBC
* Actuator
* Mapstruct
* Spock Framework
* Spring Cloud Gateway
* Netflix Eureka
* Docker

## Implementation Notes

For this task used the following libraries:

* Spring Webflux: nowadays using thread blocking I/O with normal Spring Web is getting a little bit outdated, due to this I have dedicated time to Project Reactor and its development to understand NIO and non-blocking requests. Reactive programming for me has a great experience to work with parallel processing, in a simpler way than using jdk 8 streams, callable, dealing with backpreassure, etc... at the beginning is a little different way of thinking while programming but it worths to learn it for microservices and avoid large resources consumption.
* Spring R2DBC: its support has increased in the last major version of spring boot 2.4.x including its updated library. In my last projects I have moved to this library for the same, avoiding consumptions or threads and waiting time while the database gives an answer back. Spring Data R2DBC is more complete with the latest version, and so on I wanted to do it for this test instead of thread blocking JPA.
* About JUnit: I have worked with JUnit, Mockito, PowerMockito and these libraries, but the testing code I find it more legible and more easy to write with its similar Spock Framework that I wanted to show for TDD development in this project. Another reason to use Spock is due that it uses Groovy which is easier to access private methods and properties. For allowing the last mentioned, in JUnit you have to use PowerMockito and play a little bit with more preprocessing to allow this, and use behaviour testing to verify when some method is required to be called.
* About Docker Images: For the dockerfiles they have a builder to avoid the reviewer to download specific jdk versions
to run the pokeapi example.
It is know that for Continuous Delivery and devops purposes this builder should not be used,
but instead to use the generated jar and only the second phase of the script, in the correspondent build stage on Jenkins e.g.
The fat jar is decompressed before running to increase start up time according to spring guru Josh Long
* Multi module maven: ideally each microservice should have its own git repo. The core module was created to show a module which is shared between all microservices. Created this structure to show handling of multiple modules.  

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
```
docker build -f pokemon-search/Dockerfile -t alea.com/pokeapi/pokemon-search:latest .
docker build -f discovery-server/Dockerfile -t alea.com/pokeapi/discovery-server:latest .
docker build -f gateway/Dockerfile -t alea.com/pokeapi/gateway:latest .
```
## Run Containers
```
docker run --network host -e SPRING_PROFILES_ACTIVE=loc alea.com/pokeapi/discovery-server:latest
docker run --network host -e SPRING_PROFILES_ACTIVE=loc alea.com/pokeapi/pokemon-search:latest
docker run --network host -e SPRING_PROFILES_ACTIVE=loc alea.com/pokeapi/gateway:latest
```
## Deployment
### Environment Variables
| Name | Description |
|------|------|
|SPRING_PROFILES_ACTIVE|loc,docker,pro|
|PORT|server port, default is https 8080|

## Test API

Calling Api Gateway see that redirects to the correspondent microservice

```
//Top 5 weight
curl localhost:8080/pokemon/top/weight
//top 5 height
curl localhost:8080/pokemon/top/height
//top 5 base_experience
curl localhost:8080/pokemon/top/base_experience
//change limit of results
curl --location --request GET 'localhost:8080/pokemon/top/base_experience?limit=2'
```
