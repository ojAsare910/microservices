FROM openjdk:21-slim

WORKDIR /app

COPY target/order-service-1.jar order-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "order-service.jar"]