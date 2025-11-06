FROM openjdk:26-ea-17-slim
COPY /target/*.jar application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]