FROM maven:3-openjdk-17 as build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /gateway
COPY --from=build ./build/target/*.jar ./gateway.jar
ARG EUREKA_SERVER=localhost
EXPOSE 8080
ENTRYPOINT java -jar gateway.jar