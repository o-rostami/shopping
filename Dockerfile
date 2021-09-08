FROM maven:3.6.3-adoptopenjdk-8 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests
FROM openjdk:8-jdk-alpine
ENV JAVA_OPTS ""
COPY --from=build /usr/src/app/target/shopping-app.jar /usr/app/shopping-app.jar
EXPOSE 8080
CMD exec java $JAVA_OPTS -jar /usr/app/shopping-app.jar $0 $@
