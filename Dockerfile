FROM eclipse-temurin:17-jdk-alpine
COPY . .
RUN chmod +x ./gradlew
ENTRYPOINT ["./gradlew", "runFatJar"]