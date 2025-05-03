# Loan User Management Microservice

A Spring Boot microservice for managing users and their associated loans. This service provides comprehensive APIs for user and loan management, including features like Out-Of-Office (OOO) status tracking and loan transfers between users.

## Features

- Complete CRUD operations for users and loans
- User OOO status management
- Loan transfer functionality
- Swagger UI documentation
- In-memory H2 database
- Sample data generation

## Technical Stack

- Java 21
- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database
- OpenAPI/Swagger
- Lombok
- Maven

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

## Getting Started

1. Clone the repository
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start on port 8080.

## API Documentation

Once the application is running, you can access the Swagger UI documentation at:
```
http://localhost:8080/swagger-ui.html
```

## API Endpoints

### User Management
- `POST /api/users` - Create a new user
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users/username/{username}` - Get user by username
- `PUT /api/users/{id}` - Update a user
- `DELETE /api/users/{id}` - Delete a user
- `GET /api/users/count` - Get total count of users
- `GET /api/users/exists/{id}` - Check if user exists by ID
- `GET /api/users/exists/username/{username}` - Check if user exists by username
- `GET /api/users/{id}/ooo` - Check if user is OOO by ID
- `GET /api/users/username/{username}/ooo` - Check if user is OOO by username

### Loan Management
- `POST /api/loans` - Create a new loan
- `GET /api/loans` - Get all loans
- `GET /api/loans/{id}` - Get loan by ID
- `GET /api/loans/user/{userId}` - Get all loans for a user
- `PUT /api/loans/{id}` - Update a loan
- `DELETE /api/loans/{id}` - Delete a loan
- `GET /api/loans/count` - Get total count of loans
- `GET /api/loans/exists/{id}` - Check if loan exists
- `POST /api/loans/transfer` - Transfer loans between users

## Sample Data

The application comes pre-populated with:
- 10 sample users (some with OOO status)
- 100 sample loans (10 per user)

## H2 Console

You can access the H2 database console at:
```
http://localhost:8080/h2-console
```

Use the following connection details:
- JDBC URL: `jdbc:h2:mem:loandb`
- Username: `sa`
- Password: (leave empty)

## Testing

Run the tests using:
```bash
mvn test
``` 