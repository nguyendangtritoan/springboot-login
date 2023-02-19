# Use an existing image as a base image
FROM maven:3.8.3-jdk-11-slim as build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Download the dependencies
RUN mvn dependency:go-offline

# Copy the rest of the application code to the container
COPY . .

# Build the application
RUN mvn clean package -DskipTests=true

# Use a separate image for the runtime
FROM fabric8/java-alpine-openjdk11-jre

# Set the working directory in the container
WORKDIR /app

# Copy the built artifact from the build stage to the runtime image
COPY --from=build /app/target/*.jar app.jar

# Set the environment variable for the application
# For example:
#   ARG DATABASE_PASSWORD
#   ENV DATABASE_PASSWORD ${DATABASE_PASSWORD}

# Expose the port the application will listen on
EXPOSE 8080

# Start the application
CMD java $JAVA_OPTS -jar app.jar