FROM openjdk:21-slim

WORKDIR /app

COPY target/product-1.jar product.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "product.jar"]