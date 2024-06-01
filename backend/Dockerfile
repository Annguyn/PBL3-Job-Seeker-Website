FROM openjdk:22 AS BUILD
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:22
COPY --from=BUILD /target/backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
LABEL authors="nguye"