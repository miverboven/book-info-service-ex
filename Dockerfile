FROM openjdk:8-jdk-alpine
EXPOSE 8051
ARG JAR_FILE=target/book-info-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]