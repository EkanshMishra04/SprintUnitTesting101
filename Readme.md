# Spring Boot CRUD Application

This is a Spring Boot CRUD (Create, Read, Update, Delete) application developed using Java 17, Maven, Mockito, and JUnit 5. The application demonstrates basic CRUD operations for a `Product` entity.

## Table of Contents

- [Project Overview](#project-overview)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Running Tests](#running-tests)
- [Directory Structure](#directory-structure)
- [License](#license)

## Project Overview

This project is a Spring Boot application that provides a simple REST API for managing `Product` entities. It supports CRUD operations with endpoints for creating, reading, updating, and deleting products. It is basic application to apply and learn junit testing using mockito for all 3 layers of controller, service and repository.

## Prerequisites

- Java 17
- Maven 3.8.1 or higher

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/your-repository.git
   ```

2. **Navigate to the project directory:**

   ```bash
   cd your-repository
   ```

3. **Build the project using Maven:**

   ```bash
   mvn clean install
   ```

## Running the Application

1. **Start the Spring Boot application:**

   ```bash
   mvn spring-boot:run
   ```

   By default, the application will start on [http://localhost:8085](http://localhost:8085).

## API Endpoints

### Product Endpoints

- **Add a Product**
    - **Endpoint:** `POST /addProduct`
    - **Request Body:**
      ```json
      {
        "name": "mobile",
        "price": 1500,
        "quantity": 5
      }
      ```

- **Add Multiple Products**
    - **Endpoint:** `POST /addProducts`
    - **Request Body:**
      ```json
      [
        {
          "name": "mobile",
          "price": 1500,
          "quantity": 5
        },
        {
          "name": "laptop",
          "price": 3000,
          "quantity": 2
        }
      ]
      ```

- **Get All Products**
    - **Endpoint:** `GET /products`

- **Get Product by ID**
    - **Endpoint:** `GET /productById/{id}`
    - **Path Variable:** `id` - Product ID

- **Get Product by Name**
    - **Endpoint:** `GET /productByName/{name}`
    - **Path Variable:** `name` - Product Name

- **Update a Product**
    - **Endpoint:** `PUT /updateProduct`
    - **Request Body:**
      ```json
      {
        "id": 1,
        "name": "updated mobile",
        "price": 1600,
        "quantity": 10
      }
      ```

- **Delete a Product**
    - **Endpoint:** `DELETE /product/{id}`
    - **Path Variable:** `id` - Product ID

## Running Tests

1. **Run unit tests with Maven:**

   ```bash
   mvn test
   ```

   This command will execute all the unit tests defined in the project using JUnit 5 and Mockito.

## Directory Structure

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── demo
│   │               ├── controller
│   │               │   └── ProductController.java
│   │               ├── entity
│   │               │   └── Product.java
│   │               ├── repository
│   │               │   └── ProductRepository.java
│   │               └── service
│   │                   └── ProductService.java
│   └── resources
│       └── application.properties
└── test
    ├── java
    │   └── com
    │       └── example
    │           └── demo
    │               ├── controller
    │               │   └── ProductControllerTest.java
    │               └── service
    │                   └── ProductServiceTest.java
    └── resources
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Feel free to modify or add details based on the specific configurations and requirements of your project. If there are any other aspects of the project you want to highlight or if you have specific instructions or dependencies, let me know so I can include them in the `README.md`.