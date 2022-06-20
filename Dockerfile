FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} gameclub-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/gameclub-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
