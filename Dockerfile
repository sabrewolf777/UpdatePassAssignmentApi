####
# Este Dockerfile se utiliza para construir un contenedor que ejecuta la aplicación de Spring Boot en modo nativo
#
# Antes de construir la imagen del contenedor, asegúrate de tener GraalVM y Maven instalados.
#
# Luego, construye la imagen con:
#
# docker build -t spring-boot-native-app .
#
# Luego ejecuta el contenedor usando:
#
# docker run -i --rm -p 8080:8080 spring-boot-native-app
#
###
FROM ghcr.io/graalvm/graalvm-ce:latest

# Instalar Maven
RUN gu install native-image

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente de la aplicación
COPY src ./src

# Compilar la aplicación en un ejecutable nativo
RUN mvn -Pnative package -DskipTests

# Copiar el ejecutable nativo al contenedor
COPY target/*-runner /app/app

# Exponer el puerto de la aplicación
EXPOSE 8080

# Ejecutar la aplicación nativa
ENTRYPOINT ["/app/app"]