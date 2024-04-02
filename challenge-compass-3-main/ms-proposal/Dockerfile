FROM maven:3-openjdk-17 as build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /ms-proposal
COPY --from=build ./build/target/*.jar ./ms-proposal.jar
ARG RABBITMQ=localhost
ARG EUREKA_SERVER=localhost
ENTRYPOINT java -jar ms-proposal.jar