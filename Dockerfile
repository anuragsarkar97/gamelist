FROM openjdk:17-jdk-slim-buster

COPY build/libs .

EXPOSE 8080
EXPOSE 5000

CMD ["java", "-jar", "gamelist-0.0.1-SNAPSHOT.jar"]