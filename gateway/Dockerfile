FROM adoptopenjdk/openjdk11:alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY core core
COPY pokemon-search/pom.xml pokemon-search/pom.xml
COPY discovery-server/pom.xml discovery-server/pom.xml
COPY gateway gateway

RUN ./mvnw -pl '!discovery-server,!pokemon-search' install -DskipTests
RUN mv gateway/target target
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM adoptopenjdk/openjdk11:alpine-jre

ARG DEPENDENCY=/workspace/app/target/dependency

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

VOLUME /tmp

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.alea.pokeapi.gateway.GatewayApplication"]
