FROM openjdk:17-jdk-slim as build
LABEL authors="Jordan"

WORKDIR /app

COPY build/libs/prueba-1.0.0.jar prueba-1.0.0.jar

# Exponemos el puerto en el que se ejecuta la aplicación (por defecto es el 8080)
EXPOSE 8080

# Comando para ejecutar el JAR de la aplicación
ENTRYPOINT ["java", "-jar", "/app/prueba-1.0.0.jar"]