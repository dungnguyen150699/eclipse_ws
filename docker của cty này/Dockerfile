FROM openjdk:11.0.1
ADD landing-page-1.0.0.jar /application.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/application.jar"]
