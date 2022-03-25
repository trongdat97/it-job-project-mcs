FROM openjdk:8
ARG JAR_FILE=target/api-gateway-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /api-service.jar
EXPOSE 8400
ENTRYPOINT ["java","-jar","/api-service.jar"]