# Task Management API

This project is a RESTful API for managing tasks built with **Spring Boot**, **Spring Security**, **Spring Data JPA**, and **Lombok**. The API supports secure JWT-based user authentication and authorization of end points, full CRUD operations for tasks, pagination and filtering, caching for improved performance, and an efficient task scheduling system using a priority queue (min-heap). The project was developed under a tight 6‑hour deadline and includes unit tests using JUnit.

## Features

- **User Authentication**: 
  - Secure JWT-based authentication using Spring Security.
  - Endpoints for user registration and login.

    
- **End-Points Authorization**:
   - JWT Authentication Filter.
  
- **Task CRUD Operations**:
  - Create, update, delete, and retrieve tasks.
  - Each task includes a title, description, status (`pending`/`completed`), and priority (`low`/`medium`/`high`).

- **Pagination & Filtering**:
  - Retrieve tasks with pagination.
  - Filter tasks based on priority and status.

- **Caching**:
  - Uses IN-Memory to cache task data and improve performance on fetch operations.

- **Efficient Scheduling**:
  - Implements a scheduling algorithm using a min-heap (priority queue) that sorts tasks by priority (e.g., high = 1, medium = 2, low = 3) and creation timestamp.
  
- **Unit Testing**:
  - Unit tests implemented with JUnit (and optionally Mockito) to ensure core functionality.

## Tech Stack

- **Framework**: Spring Boot
- **Security**: Spring Security (JWT-based)
- **Data Access**: Spring Data JPA
- **Caching**: In Memor cache
- **Utilities**: Lombok
- **Testing**: JUnit
- **Database**: MySQL 

## Getting Started

### Prerequisites

- **Java**: 21
- **Build Tool**: Maven
- **Database**: MySQL

### Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/RitoBose/TaskManager.git
   cd task-management-api
