# 1. Usamos una imagen base con Java 17
FROM eclipse-temurin:17-jdk-alpine

# 2. Creamos una carpeta de trabajo
WORKDIR /app

# 3. Copiamos el archivo .jar generado (El ejecutable)
# NOTA: Primero debes generar el .jar con Maven
COPY target/*.jar app.jar

# 4. Comando para iniciar la app
ENTRYPOINT ["java", "-jar", "app.jar"]