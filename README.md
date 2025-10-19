# Academic Service

## Overview

The **Academic Service** is responsible for managing all academic-related data within the CUE Event Management System. It handles the administration of faculties, academic programs, and academic areas, forming the core foundation for associating events and resources with academic entities across the university.

---

## Purpose

This service centralizes all academic domain operations to maintain data consistency and avoid duplication across other services. It provides APIs to manage:

* Faculties (organizational units within the university).
* Academic programs (e.g., undergraduate, graduate programs).
* Academic areas (specialized domains or departments).

It ensures that other microservices, such as **Event Service** or **Space Service**, can reference validated and up-to-date academic data.

---

## Versions

| Component                                   | Version |
| ------------------------------------------- | ------- |
| **Java**                                    | 17      |
| **Spring Boot**                             | 3.5.4   |
| **Gradle**                                  | 8.14.3  |
| **Bancolombia Clean Architecture Scaffold** | 3.26.1  |

---

## Architecture

The Academic Service is built using the **Bancolombia Clean Architecture Scaffold**, ensuring modularity and clear separation between business logic and infrastructure.

```
academic-service/
├── applications/             # Application entry points and configurations
├── domain/                   # Core entities, value objects, and use cases
├── infrastructure/            # Repositories, adapters, and integrations
├── build.gradle               # Gradle configuration
└── settings.gradle            # Project settings
```

### Layers

* **Domain:** Defines entities like `Faculty`, `AcademicProgram`, and `AcademicArea`.
* **Use Cases:** Implements creation, updating, and retrieval logic for academic entities.
* **Infrastructure:** Contains JPA repositories, mappers, and controllers.
* **Entry Points:** Exposes REST endpoints for CRUD operations.

---

## Environment Variables

Below are the required environment variables for the Academic Service:

```bash
# -----------------------------------
# Server Configuration
# -----------------------------------
SERVER_PORT=8080
SPRING_PROFILES_ACTIVE=dev

# -----------------------------------
# Database Configuration
# -----------------------------------
DB_URL=jdbc:mysql://mysql-academic:3306/academic_service?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
DB_USERNAME=academic_user
DB_PASSWORD=academic_password

# -----------------------------------
# Internal Communication
# -----------------------------------
INTERNAL_SECRET=your-internal-service-secret
EUREKA_URL=http://discovery-service:8761/eureka/

# -----------------------------------
# Logging Configuration
# -----------------------------------
LOGGING_LEVEL_ROOT=INFO
LOGGING_LEVEL_CO.EDU.CUE=DEBUG

# -----------------------------------
# CORS Configuration
# -----------------------------------
CORS_ALLOWED_ORIGINS=http://localhost:4200,http://localhost:3000
```

## Security

* Uses **internal service communication validation** via `INTERNAL_SECRET`.
* Integrated with **Auth Service** through the API Gateway for user-level authorization.
* Enforces **role-based access** for academic entity management (admin and academic staff only).

---

## Related Services

* **Event Service:** Links events to faculties or programs.

Part of the **CUE Event Management System**, Universidad Alexander von Humboldt. Developed under the Nuclear Project framework for academic and research purposes.
