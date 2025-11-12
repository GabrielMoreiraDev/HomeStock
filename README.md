# 🏠 HomeStock
> A microservices-based backend (Spring Boot + Kafka + Docker) for managing household inventory.

---

### 📘 Overview

**HomeStock** is a distributed backend system designed to help users manage their household inventory across multiple locations or groups.  
Each user can create groups (e.g., “Home”, “Vacation House”) and track the products stored within each one.  
Users can define items, monitor quantities, and automatically flag products that fall below a defined threshold.

This MVP showcases a **microservices architecture** using **Spring Boot**, **Apache Kafka**, and **Docker**, featuring asynchronous communication between services and independent data persistence per domain.  
It serves as both a **technical reference project** and a **foundation for future extensions** of a complete household stock management platform.

---

### 🎯 Objectives

The main goal of this project is to demonstrate knowledge of distributed systems and event-driven communication using modern backend technologies.

**Key technical objectives:**
- Implement a **microservices-based architecture** with Spring Boot  
- Apply **Kafka** for asynchronous message exchange between services  
- Use **PostgreSQL** as the database for each independent service  
- Secure endpoints with **JWT authentication** and **internal service-to-service tokens**  
- Containerize all services with **Docker Compose**  
- Provide **API documentation** via Swagger / OpenAPI  

---

### 📋 Functional Requirements

- Users can register and authenticate using JWT-based login.  
- Each user can create and manage multiple household groups.  
- Groups can have multiple members with different roles (admin / member).  
- Each group maintains its own inventory of products.  
- Products can be created, updated, and deleted by group admins.  
- When a product’s quantity falls below a defined threshold, a Kafka event is emitted to trigger shopping list updates.

---

### 🧱 Tech Stack

| Category | Technologies |
|-----------|---------------|
| **Backend Framework** | Spring Boot 3 |
| **Programming Language** | Java 21 |
| **Database** | PostgreSQL |
| **Messaging** | Apache Kafka |
| **Containerization** | Docker & Docker Compose |
| **Security** | JWT (Spring Security) + Internal JWT for microservice communication |
| **API Documentation** | Swagger / OpenAPI |
| **Build Tool** | Maven |
| **Version Control** | Git & GitHub |

---

### 🧩 Microservices

| Service | Description | Port |
|----------|--------------|------|
| **Gateway Service** | Handles routing, request forwarding, and internal JWT validation between services. | `8080` |
| **Auth Service** | Handles user registration, authentication, and JWT token management. | `8082` |
| **Group Service** | Manages user groups and memberships. | `8081` |
| **Inventory Service** | Manages products and stock levels for each group, and emits Kafka events when thresholds are reached. | `8083` |

---

### ⚙️ Architecture

The system follows a microservices architecture, where each service owns its own database and communicates exclusively through Kafka events.
There are no direct REST calls between microservices — instead, each service maintains minimal local copies of the data it needs, ensuring data consistency and eventual synchronization through event-driven communication.

The Gateway acts as the single entry point for all external requests, handling authentication, authorization, and routing.
It also issues an internal JWT token attached to outgoing requests, allowing microservices to verify that requests originate only from the gateway.

This architecture promotes loose coupling, fault isolation, and scalability, following event-driven design principles.

A simplified diagram of the architecture:

<img width="1695" height="1050" alt="image" src="https://github.com/user-attachments/assets/38871a2d-544f-4e30-bebc-ac65c69613a3" />

---

### 🧠 Future Improvements

- Add a **Shopping List Service** to consume stock-related Kafka events  
- Implement a **Purchase Service** to handle product acquisitions and restocking  
- Extend the **Gateway** with rate limiting, logging, and tracing (Spring Cloud Gateway)  
- Add a **frontend (React or Flutter)** for user interaction  
- Deploy the full system on **Kubernetes** with CI/CD pipelines  

---

### 👨‍💻 Author

**Gabriel Ferreira Moreira**  
Software Engineer passionate about distributed systems, event-driven architectures, and clean code.  
📍 Based in Porto, Portugal  

[💼 LinkedIn](https://www.linkedin.com/in/gf-moreira/) • [🐙 GitHub](https://github.com/GabrielMoreiraDev)
