#Start with base image containing Java Runtime
FROM openjdk:17-jdk-slim

#Info around who maintains the image
MAINTAINER biswadeb

#Add the applications jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#Execute the application
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]