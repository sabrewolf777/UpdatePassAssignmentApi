FROM node:16-alpine

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .

EXPOSE 8080

CMD ["npm", "start"] 

FROM ghcr.io/graalvm/native-image:latest AS builder

WORKDIR /app
COPY . .

# Crear un archivo de respuesta para native-image
RUN echo "Args = -H:+StaticExecutableWithDynamicLibC \
          -H:Name=application \
          --no-fallback \
          -cp $(find /app -name '*.jar' | tr '\n' ':')" > native-image.args

# Usar el archivo de respuesta
RUN native-image @native-image.args

FROM scratch
COPY --from=builder /app/application /
EXPOSE 8080
ENTRYPOINT ["/application"] 