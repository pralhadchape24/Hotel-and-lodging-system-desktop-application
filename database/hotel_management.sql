-- Create Database
CREATE DATABASE IF NOT EXISTS hotelmanagementsystem;
USE hotelmanagementsystem;

-- =========================
-- LOGIN TABLE
-- =========================
CREATE TABLE IF NOT EXISTS login (
    username VARCHAR(25) PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

INSERT INTO login (username, password)
VALUES ('admin', '12345');

-- =========================
-- EMPLOYEE TABLE
-- =========================
CREATE TABLE IF NOT EXISTS employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    gender VARCHAR(15),
    job VARCHAR(50),
    salary DECIMAL(10,2),
    phone VARCHAR(15),
    email VARCHAR(50),
    aadhar VARCHAR(20)
);

-- =========================
-- ROOM TABLE
-- =========================
CREATE TABLE IF NOT EXISTS room (
    roomnumber INT PRIMARY KEY,
    availability VARCHAR(20),
    cleaning_status VARCHAR(20),
    price DECIMAL(10,2),
    bed_type VARCHAR(20)
);

INSERT INTO room VALUES
(101, 'Available', 'Clean', 1000, 'Single Bed'),
(102, 'Available', 'Dirty', 2000, 'Double Bed'),
(103, 'Available', 'Clean', 1000, 'Single Bed'),
(104, 'Available', 'Clean', 1000, 'Single Bed'),
(105, 'Available', 'Dirty', 2000, 'Double Bed'),
(106, 'Available', 'Clean', 2000, 'Double Bed'),
(107, 'Available', 'Clean', 1000, 'Single Bed'),
(108, 'Available', 'Clean', 2000, 'Double Bed'),
(109, 'Available', 'Dirty', 1000, 'Single Bed'),
(110, 'Available', 'Clean', 2000, 'Double Bed');

-- =========================
-- CUSTOMER TABLE
-- =========================
CREATE TABLE IF NOT EXISTS customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    document VARCHAR(20),
    document_number VARCHAR(30),
    name VARCHAR(50),
    gender VARCHAR(15),
    country VARCHAR(30),
    room INT,
    checkintime DATETIME,
    deposit DECIMAL(10,2),
    FOREIGN KEY (room) REFERENCES room(roomnumber)
);

-- =========================
-- DRIVER TABLE
-- =========================
CREATE TABLE IF NOT EXISTS driver (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    gender VARCHAR(15),
    company VARCHAR(50),
    brand VARCHAR(50),
    available VARCHAR(20),
    location VARCHAR(50)
);

-- =========================
-- DEPARTMENT TABLE
-- =========================
CREATE TABLE IF NOT EXISTS department (
    id INT AUTO_INCREMENT PRIMARY KEY,
    department VARCHAR(50),
    budget DECIMAL(12,2)
);

INSERT INTO department (department, budget) VALUES
('Front Office',500000),
('Housekeeping',40000),
('Food and Beverage',23000),
('Kitchen',540000),
('Security',320000);