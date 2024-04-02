FROM maven:3-openjdk-17 as build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /ms-voting
COPY --from=build ./build/target/*.jar ./ms-voting.jar
ARG RABBITMQ=localhost
ARG EUREKA_SERVER=localhost
ENTRYPOINT java -jar ms-voting.jar