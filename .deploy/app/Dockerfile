FROM maven:3.8.7-openjdk-18 AS MAVEN
COPY ./ ./
RUN mvn clean package -DskipTests

FROM openjdk:18-alpine

COPY --from=MAVEN /target/*-SNAPSHOT.jar /retmix.jar
CMD ["java", "-jar", "/retmix.jar"]