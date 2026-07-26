# MyContactApp

Overview

MyContacts App is a Java-based console application that helps users manage their personal and professional contacts efficiently. The application is developed using Core Java, Object-Oriented Programming (OOP) concepts, and various Design Patterns to demonstrate real-world software development practices.

The project is organized into multiple use cases, where each use case represents a specific functionality of the contact management system. The application emphasizes clean code, modular architecture, reusable components, and maintainability.

Problem Statement

Managing contacts manually becomes difficult as the number of contacts increases. Users need an application that allows them to securely store, organize, search, and manage contacts with advanced features like filtering, grouping, tagging, and bulk operations.

The MyContacts App solves these problems using Java OOP principles and design patterns.

Features
User Registration
User Authentication (Login)
Profile Management
Create Person and Organization Contacts
View Contact Details
Edit Contacts
Delete Contacts (Soft & Hard Delete)
Contact Groups
Bulk Operations
Search Contacts
Advanced Filtering & Sorting
Tag Management
Undo & Redo Functionality
Session Management
Secure Password Hashing
Technologies Used
Java
Object-Oriented Programming (OOP)
Java Collections Framework
Java 8 Stream API
Lambda Expressions
Functional Interfaces
LocalDateTime API
UUID
Optional Class
Regular Expressions
MessageDigest (Password Hashing)
Project Structure
MyContactsApp
│
├── model
├── service
├── repository
├── builder
├── factory
├── authentication
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
Functional Modules
1. User Management

Responsible for managing user accounts.

Features:

Register User
Login
Logout
Session Management
Password Security
2. Profile Management

Allows users to update their profile information.

Features:

Update Name
Change Password
Update Preferences
3. Contact Management

Allows users to create and manage contacts.

Features:

Add Contact
View Contact
Edit Contact
Delete Contact

Supports:

Person Contact
Organization Contact
4. Group Management

Organizes multiple contacts into groups.

Features:

Create Group
Add Contacts
Remove Contacts
Bulk Operations
5. Search Module

Provides advanced searching capabilities.

Search By:

Name
Phone Number
Email
Tag
Organization
6. Filter & Sorting Module

Allows users to organize contacts.

Filter By:

Tags
Date
Favorites

Sort By:

Name
Created Date
Modified Date
7. Tag Management

Organizes contacts using reusable tags.

Examples:

Family
Friends
Office
College
Use Cases
UC-01 User Registration
Description

A new user creates an account by providing personal details, email, and password.

Implementation
Validate input
Create User object
Encrypt password
Store user
OOP Concepts
Encapsulation
Design Patterns
Factory Pattern
Builder Pattern
UC-02 User Authentication
Description

A registered user logs into the application.

Implementation
Validate credentials
Verify password
Create session
OOP Concepts
Abstraction
Polymorphism
Design Pattern
Strategy Pattern
Singleton Pattern
UC-03 Profile Management
Description

Users can update their personal information and password.

Implementation
Edit profile
Save changes
Design Pattern
Command Pattern
UC-04 Create Contact
Description

Users create Person or Organization contacts.

Implementation
Enter details
Generate UUID
Save contact
OOP Concepts
Inheritance
Composition
Design Pattern
Factory Pattern
Builder Pattern
UC-05 View Contact
Description

Displays complete information of a contact.

Implementation
Fetch contact
Format output
Display details
Design Pattern
Decorator Pattern
UC-06 Edit Contact
Description

Users modify existing contacts.

Implementation
Update information
Store previous state
Support Undo/Redo
Design Patterns
Command Pattern
Memento Pattern
UC-07 Delete Contact
Description

Removes contacts from the application.

Implementation
Soft Delete
Hard Delete
Notify related components
Design Pattern
Observer Pattern
UC-08 Bulk Operations
Description

Perform operations on multiple contacts simultaneously.

Examples:

Delete
Export
Apply Tags
Design Pattern
Composite Pattern
UC-09 Search Contacts
Description

Search contacts using multiple search criteria.

Search using:

Name
Email
Phone
Tags
Design Patterns
Specification Pattern
Chain of Responsibility Pattern
UC-10 Advanced Filtering
Description

Filter and sort contacts.

Examples:

Filter by Tags
Sort by Name
Sort by Date
Design Pattern
Strategy Pattern
UC-11 Create Tags
Description

Users create reusable tags for organizing contacts.

Examples:

Family
Friends
Office
Design Pattern
Flyweight Pattern
UC-12 Apply Tags
Description

Assign one or multiple tags to contacts.

Design Pattern
Observer Pattern
OOP Concepts Used

The project demonstrates the following Object-Oriented Programming principles:

Encapsulation
Inheritance
Polymorphism
Abstraction
Association
Aggregation
Composition
Design Patterns Used
Creational Patterns
Factory Pattern
Builder Pattern
Singleton Pattern
Structural Patterns
Decorator Pattern
Composite Pattern
Flyweight Pattern
Behavioral Patterns
Strategy Pattern
Observer Pattern
Command Pattern
Memento Pattern
Specification Pattern
Chain of Responsibility Pattern
