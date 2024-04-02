FROM maven:3-openjdk-17 as build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /eureka-server
COPY --from=build ./build/target/*.jar ./eureka-server.jar
EXPOSE 8761
ENTRYPOINT java -jar eureka-server.jar