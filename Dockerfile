FROM eclipse-temurin:17-jdk-alpine
COPY . .
RUN chmod +x ./gradlew
CMD ["./gradlew", "buildFatJar", "--no-daemon"]
ENTRYPOINT ["java", "-jar", "qr-code-generator-0.0.1.jar"]