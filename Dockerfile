FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "target/soulprincess-backend-0.0.1-SNAPSHOT.jar"]