FROM eclipse-temurin:17-jdk-alpine
ENTRYPOINT ["./gradlew", "runFatJar"]