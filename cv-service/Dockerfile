FROM openjdk:8
ARG JAR_FILE=target/cv-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /cv-service.jar
EXPOSE 8400
ENTRYPOINT ["java","-jar","/cv-service.jar"]