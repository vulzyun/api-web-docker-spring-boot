FROM maven:3.9.6-amazoncorretto-21 as build
RUN mkdir -p /demo
WORKDIR /demo
COPY pom.xml /demo
COPY src /demo/src
RUN mvn -f pom.xml clean package

FROM amazoncorretto:21.0.2-alpine3.19
COPY --from=build /demo/target/*.jar app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar","/app.jar"]