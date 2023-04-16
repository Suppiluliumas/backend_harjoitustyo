# Build stage
FROM maven:3.8.6-eclipse-temurin-17-focal AS build
WORKDIR /app
COPY pom.xml .
COPY src/ ./src/
RUN mvn clean package

# Package stage
FROM eclipse-temurin:17-jre-focal
COPY --from=build /app/target/Harjoitustyo-0.0.1-SNAPSHOT.jar /usr/local/lib/harjoitustyo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/harjoitustyo.jar"]