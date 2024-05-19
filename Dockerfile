FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ./target/elembasecontainer.jar elembasecontainer.jar
ENTRYPOINT ["java", "-jar", "/elembasecontainer.jar"]