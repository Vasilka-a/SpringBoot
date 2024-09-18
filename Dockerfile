FROM openjdk:21-jdk
EXPOSE 8081
COPY build/libs/SpringBoot-0.0.1-SNAPSHOT.jar myapp.jar
CMD ["java","-jar","/myapp.jar"]