# RUN & START Application
FROM amazoncorretto:17-alpine
WORKDIR /app

# CURL
RUN apk add --no-cache curl

# Copy original as app.jar for start
COPY target/LLM-API-Backend.jar /app/app.jar

# Javalin
EXPOSE 7070

# Start REST API
ENTRYPOINT ["java", "-Dset.env=production", "-jar", "/app/app.jar"]