FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /w
COPY . .
RUN mvn -q package -DskipTests
FROM eclipse-temurin:17-jre-alpine
COPY --from=build /w/target/cloudpulse-ai-1.0.0.jar /app.jar
EXPOSE 8087
ENTRYPOINT ["java","-jar","/app.jar"]
