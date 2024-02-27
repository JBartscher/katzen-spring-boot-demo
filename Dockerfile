FROM openjdk:17-jdk-alpine
LABEL authors="Jasper"

COPY build/libs/katzen-demo-0.0.1-SNAPSHOT-plain.jar katzen-demo-0.0.1-SNAPSHOT-plain.jar
ENTRYPOINT ["java","-jar","/katzen-demo-0.0.1-SNAPSHOT-plain.jar"]