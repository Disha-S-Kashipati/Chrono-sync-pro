# ChronoSyncPro - Attendance Management System

ChronoSyncPro is a comprehensive Attendance Management System featuring a robust backend API and a user-friendly frontend application.

This repository contains two main components:

1.  **`AMS_API_V10/`**: The backend REST API built with Java and Spring Boot.
2.  **`AMS_APP_V1/`**: The frontend web application built with Angular.

---

## 1. Backend API (`AMS_API_V10/`)

Provides the core logic, data persistence, and secure endpoints for the Attendance Management System.

### Technology Stack

*   **Language:** Java 17
*   **Framework:** Spring Boot 3.2.2
    *   Spring Web (REST APIs)
    *   Spring Data JPA (Database interaction)
    *   Spring Security (Authentication & Authorization)
    *   Spring Mail (Email sending)
*   **Database:** Supports MySQL and PostgreSQL
*   **Authentication:** JWT (jjwt library)
*   **Build Tool:** Maven
*   **Other:** Lombok

### Key Features

*   Secure user authentication (JWT, OTP) and role-based authorization.
*   CRUD operations for Departments, Courses, Batches, Instructors, and Students.
*   Functionality to assign instructors to courses/batches.
*   Attendance recording and retrieval.
*   Student promotion and removal capabilities.
*   Email notifications.
*   Detailed API endpoints (See `AMS_API_V10/README.md` for the full list).

### Project Structure

```
AMS_API_V10/
├── .mvn/                      # Maven wrapper configuration
├── src/
│   ├── main/
│   │   ├── java/com/cloud/ChronoSyncPro/
│   │   │   ├── config/        # Security, JWT, Application config
│   │   │   ├── controller/    # REST API Controllers
│   │   │   ├── customExceptions/ # Custom exception classes
│   │   │   ├── dtos/          # Data Transfer Objects
│   │   │   ├── entity/        # JPA Entities (Database models)
│   │   │   ├── repository/    # Spring Data JPA Repositories
│   │   │   ├── service/       # Business Logic Layer
│   │   │   └── AmsApiV2Application.java # Main application class
│   │   └── resources/
│   │       └── application.properties # Application configuration
│   └── test/                  # Unit/Integration tests
├── .gitignore                 # Specifies intentionally untracked files
├── mvnw                       # Maven wrapper script (Linux/macOS)
├── mvnw.cmd                   # Maven wrapper script (Windows)
└── pom.xml                    # Maven project configuration (dependencies, build)
```

### Setup & Running

1.  **Prerequisites:** JDK 17+, Maven (optional), MySQL/PostgreSQL instance.
2.  **Navigate:** `cd AMS_API_V10`
3.  **Configure:** Update database and mail settings in `src/main/resources/application.properties`.
4.  **Build:** `./mvnw clean install` (Linux/macOS) or `mvnw.cmd clean install` (Windows).
5.  **Run:**
    *   Via Maven: `./mvnw spring-boot:run` or `mvnw.cmd spring-boot:run`
    *   Via JAR: `java -jar target/AMS_API_V2-0.0.1-SNAPSHOT.jar` (Check JAR name)

*(For more detailed backend information, refer to `AMS_API_V10/README.md`)*

---

## 2. Frontend Application (`AMS_APP_V1/`)

Provides the web interface for users (Admins, Instructors, Students) to interact with the Attendance Management System.

### Technology Stack

*   **Framework:** Angular 16.2.5
*   **Language:** TypeScript (~5.1.3)
*   **Styling:** CSS
*   **State Management:** RxJS (~7.8.0)
*   **Build Tool:** Angular CLI

### Key Features

*   User login, password recovery (OTP), and password change UI.
*   Role-specific dashboards for Admins, Instructors, and Students.
*   **Admin:** UI for managing departments, courses, batches, instructors, students; assigning instructors; promoting/removing students.
*   **Instructor:** UI for managing attendance and viewing assigned schedules/details.
*   **Student:** UI for viewing attendance and personal details.
*   Navigation bar and standardized forms/lists.

### Project Structure

```
AMS_APP_V1/
├── src/
│   ├── app/
│   │   ├── core/          # Core components (login, nav, lists, forms, etc.)
│   │   ├── dashboard/     # Role-specific dashboard modules (admin, instructor, student)
│   │   ├── interceptors/  # HTTP interceptors (e.g., authentication)
│   │   ├── models/        # TypeScript interfaces for data structures
│   │   ├── register/      # Components for registering entities
│   │   ├── services/      # Services for API interaction and data handling
│   │   ├── app-routing.module.ts # Main application routing
│   │   ├── app.component.ts      # Root application component
│   │   └── app.module.ts         # Root application module
│   ├── assets/          # Static assets (images, etc.)
│   ├── environments/    # Environment configuration (if used)
│   ├── index.html       # Main HTML page
│   ├── main.ts          # Main entry point
│   └── styles.css       # Global styles
├── angular.json         # Angular CLI configuration
├── package.json         # Project dependencies and scripts
├── tsconfig.json        # Base TypeScript configuration
└── README.md            # Frontend-specific README
```

### Setup & Running

1.  **Prerequisites:** Node.js (>=16 recommended), npm, Angular CLI (`npm install -g @angular/cli@16.2.5`).
2.  **Navigate:** `cd AMS_APP_V1`
3.  **Install Dependencies:** `npm install`
4.  **Configure API URL:** Ensure services in `src/app/services/` point to the running backend API (e.g., `http://localhost:8080`).
5.  **Run:** `ng serve` or `npm start`. Access via `http://localhost:4200/`.

*(For more detailed frontend information, refer to `AMS_APP_V1/README.md`)*

---

## Running the Full System

1.  **Start the Backend API:** Follow the setup and running instructions in the `AMS_API_V10/` section. Ensure it's running (typically on `http://localhost:8080`).
2.  **Start the Frontend Application:** Follow the setup and running instructions in the `AMS_APP_V1/` section. Ensure the frontend is configured to connect to the correct backend URL. Access the application (typically via `http://localhost:4200/`).
