FROM java:8

ADD ./build/libs/user-rest-service-0.1.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongo/micros", "-jar", "/app.jar"]