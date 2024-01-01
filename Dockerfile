FROM eclipse-temurin:17-jdk-jammy
RUN chmod +x gradlew
CMD ["./gradlew", "runDocker"]