# ==============================
# 1️⃣ Build Stage
# ==============================
FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiar pom.xml e baixar dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código
COPY src ./src

# Build do projeto sem testes, fat JAR
RUN mvn clean package -DskipTests

# ==============================
# 2️⃣ Runtime Stage
# ==============================
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copiar JAR buildado
COPY --from=builder /app/target/group-service-0.0.1-SNAPSHOT.jar app.jar

# Expor porta padrão
EXPOSE 8081

# Rodar JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
