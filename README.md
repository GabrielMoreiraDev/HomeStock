# 🏠 HomeStock
> A microservices-based backend (Spring Boot + Kafka + Docker) for managing household inventory.

---

### 📘 Overview

**HomeStock** is a distributed backend application designed to help users manage their household inventory across multiple locations or groups.  
Each user can create groups (e.g., “Home”, “Vacation House”) and track the products stored within each one.  
Users can define items, monitor quantities, and automatically flag products that fall below a defined threshold.

This MVP focuses on implementing a **microservices-based architecture** using **Spring Boot**, **Apache Kafka**, and **Docker**, demonstrating asynchronous communication between services and independent data persistence per domain.  
The project serves both as a technical showcase and as the foundation for future development of a complete household stock management platform.

---

### 🎯 Objectives

The main goal of this project is to demonstrate knowledge of distributed systems and event-driven communication using modern backend technologies.

**Key technical objectives:**
- Implement a **microservices-based architecture** with Spring Boot  
- Apply **Kafka** for asynchronous message exchange between services  
- Use **PostgreSQL** as the database for each independent service  
- Secure endpoints with **JWT authentication**  
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
| **Programming Language** | Java 17+ |
| **Database** | PostgreSQL |
| **Messaging** | Apache Kafka |
| **Containerization** | Docker & Docker Compose |
| **Security** | JWT (Spring Security) |
| **API Documentation** | Swagger / OpenAPI |
| **Build Tool** | Maven |
| **Version Control** | Git & GitHub |

---

### 🧩 Microservices

| Service | Description | Port |
|----------|--------------|------|
| **Auth Service** | Handles user registration, authentication, and JWT token management. | `8081` |
| **Group Service** | Manages user groups and memberships. | `8082` |
| **Inventory Service** | Manages products and stock levels for each group, and emits Kafka events when thresholds are reached. | `8083` |

---

### ⚙️ Architecture

The system follows a **microservices architecture**, where each service owns its own database and communicates through **REST** or **Kafka events** depending on the use case.  
The `Auth` and `Group` services interact synchronously via REST, while the `Inventory` service emits Kafka events for asynchronous workflows (e.g., when a product quantity falls below the defined threshold).

A simplified diagram of the architecture will be added soon:

---

### 👨‍💻 Author

**Gabriel Ferreira Moreira**  
Software Engineer passionate about distributed systems, event-driven architectures, and clean code.  
📍 Based in Porto, Portugal  

[💼 LinkedIn](https://www.linkedin.com/in/gf-moreira/) • [🐙 GitHub](https://github.com/GabrielMoreiraDev)


