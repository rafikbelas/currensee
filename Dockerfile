#Step 1 -----------------------------------------------------------

FROM maven:3.6.1-jdk-11-slim AS MAVEN_BUILD

MAINTAINER Rafik BELAS

WORKDIR /build/

COPY pom.xml /build/

#Download all required dependencies into one layer

RUN mvn -B dependency:resolve dependency:resolve-plugins

#Copy source code

COPY src /build/src

#Build application

RUN mvn package -DskipTests

#Step 2 -----------------------------------------------------------

FROM openjdk:11-jdk

ENV APP_HOME /app/

WORKDIR $APP_HOME

#Copy executable jar file from the MAVEN_BUILD image.

COPY --from=MAVEN_BUILD /build/target/currensee-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]