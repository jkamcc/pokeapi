FROM adoptopenjdk/openjdk11:alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY core core
COPY pokemon-search/pom.xml pokemon-search/pom.xml
COPY discovery-server discovery-server
COPY gateway/pom.xml gateway/pom.xml

RUN ./mvnw -pl '!gateway,!pokemon-search' install -DskipTests
RUN mv discovery-server/target target
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM adoptopenjdk/openjdk11:alpine-jre

ARG DEPENDENCY=/workspace/app/target/dependency

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

VOLUME /tmp

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.alea.pokeapi.autodiscovery.EurekaServerApplication"]
