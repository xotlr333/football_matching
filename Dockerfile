FROM openjdk:17-oracle
COPY build/libs/football-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]