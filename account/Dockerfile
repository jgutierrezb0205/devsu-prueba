# Etapa 1: Build
FROM gradle:8.5-jdk17 AS build

WORKDIR /app

# Copiar archivos de configuración de Gradle
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY gradlew ./

# Descargar dependencias (se cachea si no cambian)
RUN gradle dependencies --no-daemon || true

# Copiar código fuente
COPY src ./src

# Compilar la aplicación
RUN gradle build -x test --no-daemon

# Etapa 2: Runtime
FROM eclipse-temurin:17-jre-alpine

LABEL authors="Jordan"

WORKDIR /app

# Copiar el JAR compilado desde la etapa de build
COPY --from=build /app/build/libs/account-1.0.0.jar app.jar

# Crear usuario no-root para seguridad
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]