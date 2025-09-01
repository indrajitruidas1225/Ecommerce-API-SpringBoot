# Step 1: Use official JDK image to build the app

FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml and download dependencies (better caching)

COPY pom.xml .
COPY src ./src

# Package the Spring Boot application

RUN mvn clean package -DskipTests

# Step 2: Use a smaller JDK/JRE image for running

FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copy the built jar from the first stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port Spring Boot runs on
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]