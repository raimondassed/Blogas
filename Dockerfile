FROM openjdk:17-alpine

COPY target/thymeleaf_blog-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]