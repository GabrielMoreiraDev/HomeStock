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

<img width="440" height="438" alt="inventory" src="https://github.com/user-attachments/assets/10e40261-0d52-4470-be5b-2774b0dae16f" />

---

## 🧠 Kafka Integration

TODO

---

## ⚙️ Environment Variables

TODO

---

## 🚀 Running locally
```bash
# Start Postgres and Kafka using Docker Compose
docker compose up -d
