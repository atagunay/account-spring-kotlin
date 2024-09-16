FROM amazoncorretto:17 AS build
LABEL authors="agunay"

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM amazoncorretto:17
WORKDIR account-api
COPY --from=build target/*.jar account-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "account-api.jar"]
