-- Office Management System Database Schema

CREATE DATABASE IF NOT EXISTS office_management;
USE office_management;

-- Table for Departments
CREATE TABLE IF NOT EXISTS Department (
    deptId BIGINT AUTO_INCREMENT PRIMARY KEY,
    deptName VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL
);

-- Table for Employees
-- Note: 'role' signifies application access role (e.g., ADMIN, MANAGER, EMPLOYEE)
--       'designation' represents job title
--       'status' could be ACTIVE, INACTIVE, etc.
CREATE TABLE IF NOT EXISTS Employee (
    empId BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(100) NOT NULL,
    middleName VARCHAR(100),
    lastName VARCHAR(100) NOT NULL,
    dob DATE,
    address TEXT,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20),
    userName VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    designation VARCHAR(100),
    basicPay DECIMAL(10, 2),
    credits VARCHAR(50),
    reportingTo BIGINT,
    status VARCHAR(50),
    FOREIGN KEY (reportingTo) REFERENCES Employee(empId) ON DELETE SET NULL
);

-- Table for Tasks (Assignments by Managers)
CREATE TABLE IF NOT EXISTS task (
    taskId BIGINT AUTO_INCREMENT PRIMARY KEY,
    taskName VARCHAR(255) NOT NULL,
    assignedTo BIGINT,
    assignedBy BIGINT,
    status VARCHAR(50) DEFAULT 'Pending',
    description TEXT,
    FOREIGN KEY (assignedTo) REFERENCES Employee(empId) ON DELETE SET NULL,
    FOREIGN KEY (assignedBy) REFERENCES Employee(empId) ON DELETE SET NULL
);

-- Note: The User.java model maps to the Employee table (fetching username and role).
-- The Admin and Manager models likely extend or rely on these core tables.

-- Insert a default Administrator account so the system can be accessed initially
INSERT INTO Employee (firstName, lastName, email, userName, password, role, status) 
VALUES ('System', 'Admin', 'admin@oms.local', 'admin', 'admin123', 'ADMIN', 'ACTIVE')
ON DUPLICATE KEY UPDATE userName=userName;
