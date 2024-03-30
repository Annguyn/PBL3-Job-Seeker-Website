CREATE DATABASE IF NOT EXISTS DB_Website;
USE DB_Website;

CREATE TABLE Users (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Role ENUM('admin', 'user') NOT NULL
);

CREATE TABLE Companies (
    CompanyID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    ContactEmail VARCHAR(255) NOT NULL,
    ContactPhone VARCHAR(20),
    Website VARCHAR(255),
    Description TEXT
);

CREATE TABLE Jobs (
    JobID INT PRIMARY KEY AUTO_INCREMENT,
    CompanyID INT,
    Title VARCHAR(255) NOT NULL,
    Description TEXT NOT NULL,
    Location VARCHAR(255) NOT NULL,
    Salary VARCHAR(100),
    StartDate DATE,
    EndDate DATE,
    Requirements TEXT,
    FOREIGN KEY (CompanyID) REFERENCES Companies(CompanyID)
);

CREATE TABLE Applications (
    ApplicationID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    JobID INT,
    Status ENUM('pending', 'accepted', 'rejected') NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (JobID) REFERENCES Jobs(JobID)
);

CREATE TABLE Analytics (
    AnalyticID INT PRIMARY KEY AUTO_INCREMENT,
    JobID INT,
    Prediction VARCHAR(255),
    Trend VARCHAR(255),
    AnalysisDate DATE,
    FOREIGN KEY (JobID) REFERENCES Jobs(JobID)
);
