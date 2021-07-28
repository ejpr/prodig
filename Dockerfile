FROM openjdk:8-jdk-alpine
MAINTAINER edu
COPY target/prodig-0.0.1-SNAPSHOT.jar prodig-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/prodig-0.0.1-SNAPSHOT.jar"]