FROM openjdk:21-slim

WORKDIR /app

COPY target/eureka-server-1.jar eureka-server.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "eureka-server.jar"]