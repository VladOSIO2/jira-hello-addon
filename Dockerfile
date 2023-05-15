# Use the official Gradle image with JDK 17 as the base image
FROM gradle:7.6.1-jdk17 AS build

# Set working directory
WORKDIR /app

# Copy the project to the working directory
COPY . .

# Run Gradle build with no unnecessary demons and test execution
RUN gradle clean build --no-daemon -x test

# Use the official OpenJDK image with JDK 17 as the runtime base image
FROM openjdk:17

# Set the working directory in the runtime container
WORKDIR /app

# Copy the compiled JAR & .properties file from the build stage to the runtime stage
COPY --from=build /app/build/libs/*.jar app.jar

# Set the entrypoint for the runtime container
ENTRYPOINT ["java", "-jar", "app.jar"]