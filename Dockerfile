# Use a base image with java
FROM openjdk:17-jdk-slim as build

# Set the working directory
WORKDIR /app

# Copy the maven file (pom.xml) for get dependencies and download
COPY pom.xml .
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Use a lightweight image for the final stage
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# COPY the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port (default is 8080)

EXPOSE 8080

# Command to run the application

ENTRYPOINT ["java", "-jar", "app.jar"]