FROM openjdk:17-alpine
ARG JAR_FILE=jar/gateway-service.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 9090
