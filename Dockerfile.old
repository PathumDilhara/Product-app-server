# Use Eclipse Temurin OpenJDK 17 base image
FROM eclipse-temurin:17-jdk-jammy

# Set working directory inside container
WORKDIR /app

# Copy Maven-built jar (you build jar before docker build)
COPY target/product_app-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]


# mvn clean package
# docker build -t product_app:latest .
# docker run -p 8080:8080 product_app:latest

# mvn clean package -DskipTests
# docker compose up --build