FROM openjdk:21-jdk-slim
COPY ./build/libs/template-0.0.1.jar /opt/service.jar
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://database:5430/bank
ENV POSTGRES_USER=bank
ENV POSTGRES_PASSWORD=bank
EXPOSE 8080
CMD java -jar /opt/service.jar