# Smallest appropriate image.
FROM openjdk:11.0.4-jre-slim

LABEL maintainer="seth@elypia.com"

# The Docker container will always default to prod.
ENV SPRING_PROFILES_ACTIVE=prod

# Create a user so we don't run as root.
RUN useradd -mu 1001 -s /bin/bash elypia
USER elypia
WORKDIR /home/elypia/

# Copy over the application, it's just a single jar file.
COPY ./build/libs/elypia-api-*.jar /home/elypia/elypia-api.jar

EXPOSE 8080

# On startup, execute the jar.
ENTRYPOINT ["java", "-jar", "elypia-api.jar"]

# Check the README.md to find our what else you'll want to do with the resulting image.
