# Use the official gradle image to create a build artifact.
FROM gradle:7.3.3-jdk11 AS build

# Copy local code to the container image.
WORKDIR /app
COPY build.gradle.kts .
COPY gradlew .
COPY src ./src

# Make the gradlew script executable
RUN chmod +x ./gradlew

# Build a release artifact.
RUN ./gradlew clean build --no-daemon

# Use AdoptOpenJDK for base image.
FROM adoptopenjdk:11-jre-hotspot

# Copy the jar to the production image from the builder stage.
COPY --from=build /app/build/libs/*.jar /app.jar

# Run the web service on container startup.
CMD ["java", "-jar", "/app.jar"]