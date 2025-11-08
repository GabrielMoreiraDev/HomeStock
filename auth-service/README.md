# Auth Service

Handles user registration, authentication, and profile management for HomeStock.

---

## 📋 Responsibilities
- User registration and login
- JWT token generation and validation
- Password encryption (BCrypt)
- User data management (CRUD)

---

## 🧱 Tech Stack
- **Java 21**
- **Spring Boot 3**
- **Spring Security / JWT**
- **PostgreSQL**
- **Docker**
- **Kafka (for user events)**

---

## 🗄️ Database Schema

<img width="440" height="342" alt="User" src="https://github.com/user-attachments/assets/028ad451-d328-40b5-9b51-52be76f20bb5" />

---

## 🧠 Kafka Integration
| Event | Direction | Description |
|--------|------------|-------------|
| `user.created` | Producer | Published when a new user is registered |

---

## ⚙️ Environment Variables
| Variable | Description |
|-----------|-------------|
| `SPRING_DATASOURCE_URL` | Database url |
| `SPRING_DATASOURCE_USERNAME` | Database hostname |
| `SPRING_DATASOURCE_PASSWORD` | Database password |
| `JWT_SECRET` | Secret for token signing |

---

## 🚀 Running locally
```bash
# Start Postgres and Kafka using Docker Compose
docker compose up -d
