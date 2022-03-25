FROM openjdk:8
ARG JAR_FILE=target/jobs-user-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /job-user-service.jar
EXPOSE 8400
ENTRYPOINT ["java","-jar","/job-user-service.jar"]