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
| `group.created` | Producer | Published when a new group is created |
| `group.updated` | Producer | Published when a group's metadata is updated |
| `group.deleted` | Producer | Published when a group is deleted |
| `group.member.added` | Producer | Published when a user is added to a group |
| `group.member.removed` | Producer | Published when a user is removed from a group |

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
