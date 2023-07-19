# Stage 1: Build the application using Maven
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create a smaller image with the application artifact
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /Cultify-0.0.1-SNAPSHOT.jar Cultify.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Cultify.jar"]