FROM eclipse-temurin:17-jdk-alpine
COPY . .
RUN chmod +x ./gradlew
CMD ["./gradlew", "buildFatJar", "--no-daemon"]
ENTRYPOINT ["./gradlew", "runFatJar", "--no-daemon"]