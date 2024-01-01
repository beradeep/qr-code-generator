FROM eclipse-temurin:17-jdk-alpine
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew buildFatJar --no-daemon
CMD ["java", "-jar", "build/libs/qr-code-generator-0.0.1.jar"]