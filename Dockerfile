FROM openjdk:8-jdk-alpine
ADD target/auth-service.jar auth-service.jar
EXPOSE 3305
ENTRYPOINT ["java" , "-jar" , "auth-service.jar"]