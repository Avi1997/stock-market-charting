FROM openjdk:8-jdk-alpine
COPY target/excel-upload-0.0.1-SNAPSHOT.war excel.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Djava.net.preferIPv4Stack=true","-jar","/excel.jar"]