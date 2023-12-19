FROM openjdk:11-jre-slim-buster
LABEL authors="Rolando Ramos"
ENV PORT 8001
COPY target/spring-boot-exchange-rate-0.0.1-SNAPSHOT.jar app-1.0.0.jar
ENTRYPOINT ["java","-jar","/app-1.0.0.jar"]

