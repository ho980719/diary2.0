# Step 1: Build stage
FROM openjdk:21-jdk-slim AS builder

# Set working directory
WORKDIR /app

# Copy gradle wrapper and gradle configuration
COPY ../gradlew ./
COPY ../gradle ./gradle

# Copy only the necessary source files
COPY . .

# Run Gradle to build the JAR file
RUN ./gradlew clean :diary-api:build --stacktrace

# Step 2: Run stage (use slim JDK image for production)
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy only the JAR file from the builder stage
COPY --from=builder /app/diary-api/build/libs/diary-api-1.0.0.jar /app/diary-api.jar

# Set environment variable for active Spring profile
ENV SPRING_PROFILES_ACTIVE=dev

# Expose the port for the application (default is 8080)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/diary-api.jar"]