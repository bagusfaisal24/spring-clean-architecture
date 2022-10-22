FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
WORKDIR /opt
ENV PORT 8081
EXPOSE 8081
COPY ${JAR_FILE} architecture-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","architecture-0.0.1-SNAPSHOT.jar"]