#Step 1

FROM maven:3.6.1-jdk-11-slim AS MAVEN_BUILD

MAINTAINER Rafik BELAS

WORKDIR /build/

COPY pom.xml /build/
COPY src /build/src

RUN mvn package

#Step 2

FROM openjdk:11-jdk

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/currensee-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "currensee-0.0.1-SNAPSHOT.jar"]