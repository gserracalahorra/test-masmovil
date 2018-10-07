FROM openjdk:10-jdk
VOLUME /tmp
ARG JAR_FILE
COPY ./orders-api/target/orders-api.jar /app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar","/app.jar"]