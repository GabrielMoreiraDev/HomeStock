# Gateway

Handles all the JWT token validation and routing to the microservices.

---

## 📋 Responsibilities
- Validate JWT token
- Routing the requests to the respective microservices

---

## 🧱 Tech Stack
- **Java 21**  
- **Spring Boot 3**  
- **Spring Security / JWT** (for authentication)  
- **Docker**

---

## 🔐 Internal JWT Authentication

The gateway generates an internal JWT token that is attached to all outgoing requests to the microservices.
Each microservice validates this token to ensure that incoming requests are authentic and originate only from the gateway.

Additionally, the gateway adds specific headers to each request to provide context or metadata required by the target microservice.

---

## ⚙️ Environment Variables

| Variable | Description |
|-----------|-------------|
| `JWT_SECRET` | Secret for token signing |
| `INTERNAL_SECRET` | Secret for gateway validation |
| `INTERNAL_SECRET_DURATION` | Duration of the token from the gateway |
| `INTERNAL_SECRET_ISSUER` | Gateway |

---

## 🚀 Running locally
```bash
# Start Postgres and Kafka using Docker Compose
docker compose up -d
