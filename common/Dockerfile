FROM openjdk:8
ARG JAR_FILE=target/common-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} common.jar
ENTRYPOINT ["java","-jar","/common.jar"]