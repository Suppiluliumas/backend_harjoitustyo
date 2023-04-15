# Build stage
FROM maven:3.8.6-jdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src/ ./src/
RUN mvn clean package

# Package stage
FROM openjdk:17-jdk-alpine
COPY --from=build /app/target/Harjoitustyo-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]