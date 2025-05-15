# Attendance Management System Frontend (AMS_APP_V1)

This project is the Angular frontend application for the Attendance Management System (AMS). It interacts with the corresponding backend API (`AMS_API_V10`) to provide a user interface for managing attendance, users, courses, batches, and departments.

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 16.2.5.

## Features

### Authentication & Authorization
*   **Login:** Secure user login via email and password (`core/login`).
*   **Password Recovery:** Multi-step process involving email/OTP (`core/recover`, `core/validate-otp`).
*   **Password Change:** Allows authenticated users to change their password (`core/change-password`).
*   **JWT Handling:** Automatically attaches authentication tokens to outgoing API requests (`interceptors/authentication.interceptor`).
*   **Role-Based Access:** Directs users to different dashboards based on their role (Admin, Instructor, Student).

### Admin Dashboard (`dashboard/admin`)
*   **Entity Management:**
    *   **Departments:** Create (`register/register-department`), View List (`core/department-list`), Update, Delete.
    *   **Courses:** Create (`register/register-course`), View List (`core/course-list`), Update, Delete.
    *   **Batches:** Create (`register/register-batch`), View List (`core/batch-list`), Update, Delete.
    *   **Instructors:** Create (`register/register-instructor`), View List (`core/instructor-list`), View Details (`core/instructor-details`), Update, Delete.
    *   **Students:** Create (`register/register-student`), View List (`core/student-list`), Update, Delete.
*   **Assignments:**
    *   Assign Instructors to Courses (`core/assign-course-to-instructor`).
    *   Assign Instructors to Batches (`core/assign-batch-to-instructor`).
*   **Student Lifecycle:**
    *   Promote student groups (via `core/student-groups` - inferred usage).
    *   Remove student groups (via `core/remove-list` - inferred usage).
*   **Overview:** Displays counts/summary information on the admin dashboard (`dashboard/admin/admin-dashboard`).

### Instructor Dashboard (`dashboard/instructor`)
*   **Attendance Management:** Mark and manage student attendance (`core/assign-attendance`).
*   **Information:** View assigned courses/batches and personal details (inferred from dashboard structure and `core/instructor-details`).
*   **Overview:** Displays relevant information on the instructor dashboard (`dashboard/instructor/instructor-dashboard`).

### Student Dashboard (`dashboard/student`)
*   **Information:** View personal details and attendance records (inferred from dashboard structure).
*   **Overview:** Displays relevant information on the student dashboard (`dashboard/student/student-dashboard`).

### Core UI Components (`core/`)
*   **Navigation:** Provides consistent navigation across the application (`core/navigation-bar`).
*   **Data Display:** Standardized lists for viewing various entities.
*   **Forms:** Dedicated forms for registration and updates.

## Technology Stack

*   **Framework:** Angular 16.2.5
*   **Language:** TypeScript (~5.1.3)
*   **Styling:** CSS
*   **State Management:** RxJS (~7.8.0)
*   **Build Tool:** Angular CLI

## Project Structure Overview

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
└── README.md            # This file
```

## Prerequisites

*   Node.js and npm (Check `package.json` for recommended versions, likely Node >= 16)
*   Angular CLI (`npm install -g @angular/cli@16.2.5`)
*   A running instance of the backend API (`AMS_API_V10`) accessible from the frontend (typically configured in service files or environment settings).

## Setup

1.  **Clone the repository:**
    ```bash
    git clone <your-repository-url>
    cd AMS_APP_V1
    ```
2.  **Install dependencies:**
    ```bash
    npm install
    ```
3.  **Configure Backend API URL:**
    *   Locate the service files within `src/app/services/`.
    *   Ensure the base URL used for API calls points to your running `AMS_API_V10` instance (e.g., `http://localhost:8080`). This might be hardcoded or ideally configured in environment files (`src/environments/`).

## Development server

Run `ng serve` or `npm start` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/ams-app-v1` directory. Use `ng build --configuration production` for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

---

*This README provides a more detailed overview based on the project's structure and components.*
