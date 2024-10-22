# Use a base image with java
FROM openjdk:23-jdk-slim as build

# Set the working directory
WORKDIR /app

# Copy the maven file (pom.xml) to get dependencies
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Download dependencies (this will be cached if no changes in pom.xml)
RUN ./mvnw dependency:go-offline

# Copy the rest of the application code
COPY src ./src

# Compile and package the application
RUN ./mvnw clean package -DskipTests -e

# Use a lightweight image for the final stage
FROM openjdk:23-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port (default is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
