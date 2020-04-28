FROM openjdk:8-jdk-alpine
COPY target/company-service-0.0.1-SNAPSHOT.war company.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Djava.net.preferIPv4Stack=true","-jar","/company.jar"]