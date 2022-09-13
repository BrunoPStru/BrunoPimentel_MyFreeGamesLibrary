FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} myfreegames.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","myfreegames.jar"]