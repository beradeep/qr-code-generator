FROM eclipse-temurin:17-jdk-alpine
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew buildFatJar --no-daemon
RUN ls -la ./build/libs
CMD ["java", "-jar", "./build/libs/qr-code-generator-all.jar"]