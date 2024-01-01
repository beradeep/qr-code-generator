# Setup Alpine Linux bundled with OpenJDK 17
FROM openjdk:17-jdk-alpine
# Copy the jar to the production image from the builder stage.
COPY /build/libs/*.jar /app.jar
# Exposed port 8080
EXPOSE 8080
# Run the web service on container startup.
CMD ["java", "-jar", "/app.jar"]