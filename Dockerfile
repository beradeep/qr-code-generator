FROM eclipse-temurin:17-jdk-alpine
COPY . .
RUN chmod +x ./gradlew
CMD ["./gradlew", "buildFatJar", "--no-daemon"]
COPY ./build/libs/qr-code-generator-0.0.1.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]