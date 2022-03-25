FROM openjdk:8
ARG JAR_FILE=target/account-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /account-service.jar
EXPOSE 8400
ENTRYPOINT ["java","-jar","/account-service.jar"]