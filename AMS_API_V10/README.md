# Attendance Management System API (AMS_API_V10)

This project provides the backend API for an Attendance Management System (AMS). It is built using Java, Spring Boot, and Maven.

## Features

*   **User Authentication & Authorization:**
    *   Secure login using email and password.
    *   JWT (JSON Web Token) based authentication for stateless sessions.
    *   Refresh token mechanism for extended sessions.
    *   Role-based access control (Admin, Instructor, Student - inferred).
    *   OTP (One-Time Password) validation for certain operations (e.g., password recovery).
    *   Password change functionality.
*   **Core Data Management:**
    *   Manage Departments (CRUD).
    *   Manage Courses (CRUD).
    *   Manage Batches (CRUD).
    *   Manage Instructors (CRUD).
    *   Manage Students (CRUD).
*   **Relationships & Assignments:**
    *   Assign Instructors to Courses.
    *   Assign Instructors to Batches.
    *   Assign Batches to Students during registration.
*   **Attendance Tracking:**
    *   Record student attendance.
    *   Retrieve attendance records by student or multiple students.
    *   Delete attendance records by student.
*   **Student Promotion & Removal:**
    *   Functionality to promote student groups.
    *   Functionality to remove student groups.
*   **Email Notifications:**
    *   Integrated email service (likely for OTP, notifications).

## Technology Stack

*   **Language:** Java 17
*   **Framework:** Spring Boot 3.2.2
    *   Spring Web (REST APIs)
    *   Spring Data JPA (Database interaction)
    *   Spring Security (Authentication & Authorization)
    *   Spring Mail (Email sending)
*   **Database:** Supports MySQL and PostgreSQL (based on dependencies)
*   **Authentication:** JWT (jjwt library)
*   **Build Tool:** Maven
*   **Other:** Lombok (Reduces boilerplate code)

## Project Structure

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

## Setup and Installation

1.  **Prerequisites:**
    *   Java Development Kit (JDK) 17 or later.
    *   Maven (Optional, as the project includes a Maven wrapper).
    *   A running instance of MySQL or PostgreSQL database.

2.  **Clone the repository:**
    ```bash
    git clone <your-repository-url>
    cd AMS_API_V10
    ```

3.  **Configure Database:**
    *   Open `src/main/resources/application.properties`.
    *   Update the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` properties to match your database setup.
    *   Configure other properties as needed (e.g., `spring.jpa.hibernate.ddl-auto`, mail server settings).

4.  **Build the project:**
    *   Using Maven Wrapper (Recommended):
        *   On Linux/macOS: `./mvnw clean install`
        *   On Windows: `mvnw.cmd clean install`
    *   Using system Maven: `mvn clean install`

## Running the Application

1.  **Run from IDE:**
    *   Import the project into your favorite IDE (IntelliJ IDEA, Eclipse/STS, VS Code with Java extensions).
    *   Locate the `AmsApiV2Application.java` file and run it as a Java application.

2.  **Run using Maven Wrapper:**
    *   On Linux/macOS: `./mvnw spring-boot:run`
    *   On Windows: `mvnw.cmd spring-boot:run`

3.  **Run the packaged JAR:**
    *   After building (step 4 above), navigate to the `target/` directory.
    *   Run the application using: `java -jar AMS_API_V2-0.0.1-SNAPSHOT.jar` (Adjust the JAR filename if necessary).

The API will typically be available at `http://localhost:8080` (or the port configured in `application.properties`).

## API Endpoints

### Authentication (`/api/v1/auth`)
*   `POST /authenticate`: Authenticate user with credentials (e.g., email/password) and receive JWT/set cookies.
*   `POST /validate-otp`: Validate a One-Time Password (OTP).
*   `POST /change-password`: Change or update user password (likely requires prior OTP validation).

### Admin - Student Management (`/api/v1/admin`)
*   `POST /saveStudent`: Register a new student and assign batches.
*   `PATCH /updateStudent`: Update an existing student's information.
*   `GET /get-student-list`: Retrieve a list of all students.
*   `GET /getStudent/{id}`: Get details for a specific student by ID.
*   `GET /getStudentsOfDepartmentOfSemester`: Get students filtered by department ID and semester.
*   `DELETE /deleteStudent/{id}`: Delete a student by ID.
*   `GET /studentCount`: Get the total number of registered students.

### Admin - Instructor Management (`/api/v1/admin`)
*   `POST /saveInstructor`: Register a new instructor.
*   `PATCH /updateInstructor`: Update an existing instructor's information.
*   `GET /get-instructor-list`: Retrieve a list of all instructors.
*   `GET /getInstructor/{id}`: Get details for a specific instructor by ID.
*   `DELETE /deleteInstructor/{id}`: Delete an instructor by ID.
*   `GET /instructorCount`: Get the total number of registered instructors.

### Admin - Department Management (`/api/v1/admin`)
*   `POST /saveDepartment`: Create a new department.
*   `GET /getAllDepartment`: Retrieve a list of all departments.
*   `GET /getDepartment/{id}`: Get details for a specific department by ID.
*   `PATCH /updateDepartment`: Update an existing department's information.
*   `DELETE /deleteDepartment/{id}`: Delete a department by ID.
*   `GET /departmentCount`: Get the total number of departments.

### Admin - Course Management (`/api/v1/admin`)
*   `POST /saveCourse`: Create a new course.
*   `PATCH /updateCourse`: Update an existing course's information.
*   `GET /getCourseList`: Retrieve a list of all courses.
*   `GET /getCourse/{id}`: Get details for a specific course by ID.
*   `DELETE /deleteCourse/{id}`: Delete a course by ID.
*   `GET /courseCount`: Get the total number of courses.

### Admin - Batch Management (`/api/v1/admin`)
*   `POST /saveBatch`: Create a new batch.
*   `PATCH /updateBatch`: Update an existing batch's information.
*   `DELETE /deleteBatch/{batchId}`: Delete a batch by ID.
*   `GET /get-batch-list`: Retrieve a list of all batches.
*   `GET /getBatch/{batchId}`: Get details for a specific batch by ID.
*   `GET /batchCount`: Get the total number of batches.

### Admin - Instructor-Batch Assignment (`/api/v1/admin/instructor-batch`)
*   `POST /assign`: Assign an instructor to one or more batches.

### Admin - Instructor-Course Assignment (`/api/v1/admin/instructor-course`)
*   `POST /assign`: Assign an instructor to one or more courses.

### Admin - Student Promotion & Removal (`/api/v1/admin`)
*   `POST /promote`: Promote a list of students (likely to the next semester/year).
*   `GET /promote-group`: Get a list of final year students eligible for promotion/removal (?).
*   `POST /removeStudents`: Remove a group of students from the system.

### Attendance Management (`/api/attendance`)
*   `GET /student/{studentId}`: Get attendance records for a specific student.
*   `GET /students?studentIds={id1},{id2},...`: Get attendance records for multiple students by their IDs.
*   `POST /`: Create/record a new attendance entry.
*   `DELETE /student/{studentId}`: Delete all attendance records for a specific student.

*(Note: There might be other controllers like `EmailController`, `RefreshController`, `StatusController`, `CheckAuthenticationController` which could expose more endpoints. This list covers the main CRUD and functional operations identified.)*

---

*This README was generated based on project structure, configuration files, and controller analysis.*
