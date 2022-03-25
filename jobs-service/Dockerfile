FROM openjdk:8
ARG JAR_FILE=target/jobs-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /job-service.jar
EXPOSE 8400
ENTRYPOINT ["java","-jar","/job-service.jar"]