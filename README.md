# 🏨 Hotel & Lodging Management System  
### Java Desktop Application | Apache NetBeans | MySQL

---

## 📌 Project Overview

The **Hotel & Lodging Management System** is a Java-based desktop application developed to automate and manage hotel operations efficiently.

The system provides functionality for room booking, customer management, employee records, billing, and administrative control using a structured multi-layer architecture.

This project demonstrates practical implementation of:
- Java OOP principles
- JDBC database connectivity
- Relational database design
- Desktop GUI development using Swing

---

## 🏗 System Architecture

The application follows a layered architecture:

- **Presentation Layer** → Java Swing GUI
- **Business Logic Layer** → Core Java classes
- **Data Access Layer** → JDBC
- **Database Layer** → MySQL

This separation improves maintainability and scalability.

---

## 🚀 Features

✔ User authentication (Admin login)  
✔ Employee management  
✔ Room availability management  
✔ Customer check-in & check-out  
✔ Booking system  
✔ Billing & deposit tracking  
✔ Department budget management  
✔ Driver management  

---

## 🛠 Tech Stack

| Technology | Purpose |
|------------|----------|
| Java | Core Programming |
| Java Swing | GUI Development |
| MySQL | Database |
| JDBC | Database Connectivity |
| Apache NetBeans | IDE |

---

## 🗄 Database Setup

1. Install **MySQL Server**
2. Open MySQL Workbench
3. Run the SQL file located at:
4. 
database/hotel_management.sql

This will automatically:
- Create database
- Create tables
- Insert default records

---

## ▶️ How To Run The Project

1. Clone the repository:
 git clone https://github.com/pralhadchape24/Hotel-and-lodging-system-desktop-application.git

2. Open the project in **Apache NetBeans**

3. Configure database credentials in your connection class:
```java
jdbc:mysql://localhost:3306/hotelmanagementsystem
```
4. Run the project.
   
🔐 Default Login Credentials
Username: admin
Password: 12345
