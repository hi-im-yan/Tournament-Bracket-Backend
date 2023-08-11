# Use a base image with Java 17 support
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/tournament.bracket.generator-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port on which your Spring Boot application is running
#EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
