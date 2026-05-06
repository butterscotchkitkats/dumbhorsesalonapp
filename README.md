# 💈 Dumb Horse Salon App

A Java-based salon booking and management application built with object-oriented design principles. The app models a hair salon's core operations — managing users (customers and stylists), booking appointments, and handling appointment status tracking.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Class Overview](#class-overview)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [Running Tests](#running-tests)
- [JavaDoc](#javadoc)

---

## Overview

**Dumb Horse Salon App** is a console-based Java application that simulates a hair salon management system. Users can register as either a **Customer** or a **Stylist**, manage their profiles, and book or cancel appointments through an interactive menu.

---

## Features

- User registration and profile management (create, read, update, delete)
- Role-based user types: **Customer** and **Stylist**
- Appointment booking between customers and stylists
- Appointment status tracking: `ACTIVE` or `CANCELLED`
- Service layer architecture with controllers and services
- Console-driven interactive menus
- JUnit 5 unit test coverage

---

## Project Structure

```
dumbhorsesalonapp/
├── src/
│   └── edu/
│       └── secourse/
│           ├── Main.java
│           └── salonapp/
│               ├── components/
│               │   └── Appointment.java        # Appointment entity + Status enum
│               ├── models/
│               │   ├── User.java               # Base user class
│               │   ├── Customer.java           # Customer subclass
│               │   └── Stylist.java            # Stylist subclass
│               └── services/
│                   ├── UserService.java         # CRUD operations for users
│                   ├── UserController.java      # Console menu for user actions
│                   ├── AppointmentService.java  # Appointment handling logic
│                   └── AppointmentController.java # Console menu for appointments
├── pom.xml
└── Test Coverage Screenshot.png
```

---

## Class Overview

| Class | Package | Description |
|---|---|---|
| `Main` | `edu.secourse` | Application entry point |
| `User` | `edu.secourse.salonapp.models` | Base class representing any person in the system |
| `Customer` | `edu.secourse.salonapp.models` | Represents a customer user |
| `Stylist` | `edu.secourse.salonapp.models` | Represents a hair stylist user |
| `Appointment` | `edu.secourse.salonapp.components` | Represents a booking between a customer and a stylist |
| `Appointment.Status` | `edu.secourse.salonapp.components` | Enum: `ACTIVE` or `CANCELLED` |
| `UserService` | `edu.secourse.salonapp.services` | Add, view, update, and delete user profiles |
| `UserController` | `edu.secourse.salonapp.services` | Console interface for user-related actions |
| `AppointmentService` | `edu.secourse.salonapp.services` | Handles appointment business logic |
| `AppointmentController` | `edu.secourse.salonapp.services` | Console menu for appointment actions |

---

## Technologies

- **Java 25**
- **Maven** (build and dependency management)
- **JUnit Jupiter 5.9.1** (unit testing)
- **JavaDoc** (auto-generated API documentation included)

---

## Getting Started

### Prerequisites

- Java 25+
- Maven 3.x

### Clone the Repository

```bash
git clone https://github.com/butterscotchkitkats/dumbhorsesalonapp.git
cd dumbhorsesalonapp
```

### Build the Project

```bash
mvn compile
```

### Run the Application

```bash
mvn exec:java -Dexec.mainClass="edu.secourse.Main"
```

---

## Running Tests

Unit tests are written with JUnit Jupiter 5 and cover user creation, retrieval, deletion, and console I/O behavior. Tests simulate console input using `ByteArrayInputStream` and capture output via `ByteArrayOutputStream`.

```bash
mvn test
```

Test results are also available in the included `Test Results - edu_secourse_salonapp_in_DumbHorseSalon.html` file.

---

## JavaDoc

Auto-generated JavaDoc documentation is included in the root of the repository. Open `index.html` in your browser to browse the full API reference.

```bash
open "Portfolio Documentation/JavaDoc/index.html"
```

---

## License

See the [`legal/`](legal/) directory for license information.
