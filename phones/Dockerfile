FROM openjdk:10-jdk
VOLUME /tmp
ARG JAR_FILE
COPY ./phones-api/target/phones-api.jar /app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar","/app.jar"]