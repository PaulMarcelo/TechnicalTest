FROM eclipse-temurin:22-jdk-alpine

WORKDIR /app

COPY build/libs/transactionsaccount-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
