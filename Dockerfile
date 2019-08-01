FROM openjdk:11.0.4-jre-slim

# Must specify the version we're building.
ARG version

RUN adduser -u 1001 -Sh /home/elypia elypia
WORKDIR /home/elypia/
COPY ./build/libs/elypia-api-${version}.jar /home/elypia/elypia-api-${version}.jar

ENTRYPOINT ["java", "-jar", "elypia-api-${version}.jar"]
