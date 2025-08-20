# build stage
FROM gradle:8.7.0-jdk17 AS build
WORKDIR /workspace
COPY . .
RUN chmod +x gradlew
RUN ./gradlew clean build -x test

# runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]