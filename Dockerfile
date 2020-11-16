FROM java:8
ADD target/auth-service.jar auth-service.jar
EXPOSE 3305
ENTRYPOINT ["java" , "-jar" , "auth-service.jar"]