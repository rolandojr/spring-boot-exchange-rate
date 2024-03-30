FROM openjdk:11-jre-slim-buster
LABEL authors="Rolando Ramos"
ENV PORT 8081
COPY target/spring-boot-exchange-rate-1.0.2-SNAPSHOT.jar app-1.0.0.jar
ENTRYPOINT ["java","-jar","/app-1.0.0.jar"]

