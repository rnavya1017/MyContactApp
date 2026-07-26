# MyContactApp

# 📇 MyContacts App

A **Java Console-Based Contact Management System** built using **Core Java**, **Object-Oriented Programming (OOP)** principles, and multiple **Design Patterns**. The project demonstrates clean architecture, reusable code, and real-world software design by implementing a complete contact management solution with authentication, contact management, searching, filtering, tagging, grouping, and bulk operations.

> **Purpose:** To learn and demonstrate Core Java, Java 8 features, OOP concepts, Design Patterns, and software engineering best practices through a real-world application.

---

# ✨ Features

### 👤 User Management
- User Registration
- User Authentication (Login)
- Secure Password Hashing
- Session Management
- Profile Management

### 📞 Contact Management
- Create Person Contacts
- Create Organization Contacts
- View Contact Details
- Edit Contacts
- Delete Contacts
- Soft Delete & Hard Delete

### 👥 Group Management
- Create Contact Groups
- Add/Remove Contacts
- Bulk Operations on Groups

### 🏷 Tag Management
- Create Tags
- Assign Multiple Tags
- Remove Tags
- Reusable Tag Objects

### 🔍 Search & Filter
- Search by Name
- Search by Email
- Search by Phone Number
- Search by Tags
- Search by Organization
- Advanced Filtering
- Sorting Contacts

### ⚡ Additional Features
- Undo / Redo Contact Changes
- Batch Operations
- Secure Password Encryption
- UUID-based Contact IDs
- Validation & Exception Handling

---

# 🎯 Objectives

- Demonstrate Object-Oriented Programming concepts.
- Implement commonly used Design Patterns.
- Apply Java Collections Framework effectively.
- Use Java 8 Functional Programming features.
- Build a modular and maintainable application.
- Follow industry-standard coding practices.

---

# 🛠 Technologies Used

- Java
- Java Collections Framework
- Java 8 Stream API
- Lambda Expressions
- Functional Interfaces
- LocalDateTime API
- UUID
- Optional
- Regular Expressions
- MessageDigest (Password Hashing)

---

# 📂 Project Structure

```text
MyContactsApp
│
├── model
├── builder
├── factory
├── authentication
├── repository
├── service
├── decorator
├── command
├── observer
├── composite
├── strategy
├── specification
├── memento
├── flyweight
├── util
├── ui
└── Main.java
```

---

# 📋 Use Cases

| Use Case | Description |
|----------|-------------|
| UC-01 | User Registration |
| UC-02 | User Authentication |
| UC-03 | User Profile Management |
| UC-04 | Create Contact |
| UC-05 | View Contact Details |
| UC-06 | Edit Contact |
| UC-07 | Delete Contact |
| UC-08 | Bulk Operations |
| UC-09 | Search Contacts |
| UC-10 | Advanced Filtering & Sorting |
| UC-11 | Create & Manage Tags |
| UC-12 | Apply Tags to Contacts |

---

# 🏗 OOP Concepts Used

### Encapsulation
- Private fields
- Getters and Setters
- Validation methods

### Inheritance
- User → FreeUser, PremiumUser
- Contact → Person, Organization

### Polymorphism
- Authentication strategies
- Filter strategies
- Search implementations

### Abstraction
- Interfaces
- Abstract classes
- Repository abstraction

### Relationships
- Association
- Aggregation
- Composition
- Dependency

---

# 🎨 Design Patterns Used

## Creational Patterns

- Factory Pattern
- Builder Pattern
- Singleton Pattern

## Structural Patterns

- Decorator Pattern
- Composite Pattern
- Flyweight Pattern

## Behavioral Patterns

- Strategy Pattern
- Observer Pattern
- Command Pattern
- Memento Pattern
- Specification Pattern
- Chain of Responsibility Pattern

---

# ☕ Java Concepts Demonstrated

- Collections Framework
- Stream API
- Lambda Expressions
- Method References
- Comparator
- Predicate
- Optional Class
- UUID
- LocalDateTime
- Regular Expressions
- Exception Handling
- Password Hashing
- equals() & hashCode()
- Deep Copy & Defensive Copy

---

# 📖 Application Workflow

```text
Start
   │
   ▼
Register
   │
   ▼
Login
   │
   ▼
Manage Profile
   │
   ▼
Create Contacts
   │
   ▼
View / Edit / Delete Contacts
   │
   ▼
Manage Groups
   │
   ▼
Manage Tags
   │
   ▼
Search Contacts
   │
   ▼
Filter & Sort Contacts
   │
   ▼
Bulk Operations
   │
   ▼
Logout
   │
   ▼
Exit
```

---

# 📚 Learning Outcomes

This project demonstrates practical implementation of:

- Core Java
- Object-Oriented Programming (OOP)
- Java Collections Framework
- Java 8 Features
- Software Design Patterns
- SOLID Principles
- Clean Code Practices
- Exception Handling
- Layered Architecture

---

# 🚀 Future Enhancements

- JDBC Integration
- MySQL Database Support
- Spring Boot REST API
- React Frontend
- JWT Authentication
- Contact Import & Export (CSV/PDF)
- Email Notifications
- Cloud Backup
- Mobile Application

---

# ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/your-username/MyContactsApp.git
```

### 2. Navigate to the project

```bash
cd MyContactsApp
```

### 3. Compile the project

```bash
javac Main.java
```

### 4. Run the application

```bash
java Main
```

---

# 👨‍💻 Author

**R. Navya**

**Java Full Stack Developer**

- Passionate about Java, Spring Boot, and Full Stack Development.
- This project was developed as a learning project to demonstrate Java, OOP, and Design Patterns through a real-world application.

---

# ⭐ If you found this project helpful, consider giving it a Star!
