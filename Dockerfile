##
## For this dockerfile it has a builder to avoid the reviewer to download specific jdk versions
## to run the pokeapi example.
# It is know that for Continuous Delviery and devops purposes this builder should not be used,
# but instead to use the generated jar and only the second phase of the script.

## The fat jar is decompressed before running to increase start up time according to Josh Long


FROM adoptopenjdk/openjdk11:alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY core core
COPY pokemon-search pokemon-search

RUN ./mvnw install -DskipTests
RUN mv pokemon-search/target target
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM adoptopenjdk/openjdk11:alpine-jre

ARG DEPENDENCY=/workspace/app/target/dependency

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

VOLUME /tmp

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.alea.pokeapi.search.PokeApiApplication"]
