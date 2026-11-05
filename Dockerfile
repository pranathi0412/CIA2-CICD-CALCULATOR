FROM eclipse-temurin:22-jdk

WORKDIR /app

COPY target/CalculatorApp-1.0-SNAPSHOT.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
