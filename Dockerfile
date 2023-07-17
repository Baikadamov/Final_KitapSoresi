FROM openjdk:17-oracle
MAINTAINER maksat
COPY build/libs/Kitapsoresi-0.0.1-SNAPSHOT.jar spring-backend.jar
ENTRYPOINT ["java", "-jar", "spring-backend.jar"]