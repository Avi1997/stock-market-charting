FROM openjdk:8-jdk-alpine
COPY target/authentication-service-0.0.1-SNAPSHOT.war auth.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Djava.net.preferIPv4Stack=true","-jar","/auth.jar"]