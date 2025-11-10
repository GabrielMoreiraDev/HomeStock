# Group Service

Handles group creation, members management, and group-related operations for HomeStock.

---

## 📋 Responsibilities
- Create, update, and delete groups  
- Add or remove users from groups  
- Manage group metadata (name, description, owner, etc.)  
- Publish group-related events to Kafka  

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

<img width="1000" height="544" alt="user" src="https://github.com/user-attachments/assets/5d5bba4a-7992-4065-a43f-2f6abd098663" />

---

## 🧠 Kafka Integration

| Event | Direction | Description |
|-------|-----------|-------------|
| `user.created` | Consumer | Consumed when a new user is created |
| `userGroup.created` | Producer | Published when a new userGroup is created |
| `userGroup.updated` | Producer | Published when a userGroup is updated |
| `userGroup.deleted` | Producer | Published when a userGroup is deleted |

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
