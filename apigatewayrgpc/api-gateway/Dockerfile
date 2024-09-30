FROM openjdk:21-slim

WORKDIR /app

COPY target/api-gateway-1.jar api-gateway.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "api-gateway.jar"]