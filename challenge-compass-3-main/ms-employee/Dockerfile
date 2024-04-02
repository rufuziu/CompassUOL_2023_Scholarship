FROM maven:3-openjdk-17 as build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /ms-employee
COPY --from=build ./build/target/*.jar ./ms-employee.jar
ARG EUREKA_SERVER=localhost
ENTRYPOINT java -jar ms-employee.jar