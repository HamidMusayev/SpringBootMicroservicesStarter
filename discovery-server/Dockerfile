FROM openjdk:17
WORKDIR /app
COPY target/discovery-server.jar discovery-server.jar
ENTRYPOINT ["java", "-jar", "discovery-server.jar"]
EXPOSE 8761
