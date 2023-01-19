FROM openjdk:17-jdk-slim-buster
WORKDIR /app

COPY app/build/lib/* build/lib/

COPY app/build/libs/app.jar build/

EXPOSE 8080
EXPOSE 5000

WORKDIR /app/build
ENTRYPOINT java -jar app.jar