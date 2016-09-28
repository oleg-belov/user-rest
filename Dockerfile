FROM java:8

ADD ./build/libs/user-rest-service-0.1.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://172.17.0.1:27017/micros", "-jar", "/app.jar"]