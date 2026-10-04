# Etapa 1: compilar el proyecto
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests

# Etapa 2: ejecutar Spring Boot
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

RUN adduser --system --group appuser

COPY --from=build /app/target/barberia-0.0.1-SNAPSHOT.jar app.jar

RUN chown appuser:appuser app.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
