# Inventory Service

Handles stock management for a group in HomeStock.

---

## 📋 Responsibilities
- Create, update, and delete products
- Register product consumption
- Publish inventory-related events to Kafka  

---

## 🧱 Tech Stack
- **Java 21**  
- **Spring Boot 3**  
- **Spring Security / JWT** (for service-to-service authentication)  
- **PostgreSQL**  
- **Docker**  
- **Kafka (for group events)**  

---

## 🗄️ Database Schema

<img width="860" height="438" alt="Inventory" src="https://github.com/user-attachments/assets/89cbbc01-4465-49a9-9062-3c1d800d2ebd" />

---

## 🧠 Kafka Integration

| Event | Direction | Description |
|-------|-----------|-------------|
| `userGroup.created` | Consumer | Consumed when a new userGroup is created |
| `userGroup.updated` | Consumer | Consumed when a new userGroup is updated |
| `userGroup.deleted` | Consumer | Consumed when a new userGroup is deleted |
| `product.reachedThreshold` | Producer | Published when a product's quantity reaches threshold |

---

## ⚙️ Environment Variables

| Variable | Description |
|-----------|-------------|
| `SPRING_DATASOURCE_URL` | Database url |
| `SPRING_DATASOURCE_USERNAME` | Database hostname |
| `SPRING_DATASOURCE_PASSWORD` | Database password |
| `JWT_SECRET` | Secret for token signing |
| `INTERNAL_SECRET` | Secret for gateway validation |
| `INTERNAL_SECRET_DURATION` | Duration of the token from the gateway |
| `INTERNAL_SECRET_ISSUER` | Gateway |

---

## 🚀 Running locally
```bash
# Start Postgres and Kafka using Docker Compose
docker compose up -d
