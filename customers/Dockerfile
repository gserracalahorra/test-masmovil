FROM openjdk:10-jdk
VOLUME /tmp
ARG JAR_FILE
COPY ./customers-api/target/customers-api.jar /app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar","/app.jar"]