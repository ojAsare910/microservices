FROM openjdk:21-slim

WORKDIR /app

COPY target/eureka-discovery-service-1.jar eureka-discovery-service.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "eureka-discovery-service.jar"]