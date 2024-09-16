# Account Kotlin & Spring Boot API

## Overview

The Account API is a Spring Boot application designed to manage accounts and transactions. It provides RESTful endpoints for creating, updating, and retrieving account and transaction information.

## Technologies Used

- Java 17
- Spring Boot
- Maven
- H2 Database
- Docker

## Prerequisites

- Java 17
- Maven
- Docker

## Getting Started

### Clone the Repository

```sh
git clone https://github.com/atagunay/account-api.git
cd account-api
```

### Build the Project

```sh
./mvnw clean package
```

### Run the Application

```sh
java -jar target/account-api.jar
```

The application will start on `http://localhost:8080`.

## Docker

### Build Docker Image

```sh
docker build -t account-api .
```

### Run Docker Container

```sh
docker run -p 8080:8080 account-api
```

## Endpoints

### Account Endpoints

- **Create Account With an Initial Transaction**
    - **URL:** `http://localhost:8080/v1/account`
    - **Method:** `POST`
    - **Request Body:**
      ```json
      {
            "customerId":"1bcf7b25-8ee2-4907-9ace-916aa7befeef",
            "initialCredit": "100"
      }
      ```
      - **Response:**
        ```json
        {
          "id": "0ec91590-ee51-4dca-ad0c-afed5d5a8430",
          "balance": 100,
          "created": "2024-09-16T17:07:25.5305025",
          "customer": {
          "id": "0ef2b24a-f9d8-4329-9702-fb02bc90329a",
          "name": "John",
          "surname": "Doe"
        },
          "transactions": [
            {
              "id": "d4062ce1-f3b5-4634-bb6f-421b2fd30e08",
              "transactionType": "INITIAL",
              "amount": 100,
              "transactionDate": "2024-09-16T17:07:25.5305025"
            }
          ]
        }
        ```

### Customer Endpoints

- **Get Customer By ID**
  - **URL:** `http://localhost:8080/v1/customer/{customerId}`
    - **Method:** `GET`
    - **Response:**
      ```json
      {
        "id": "0ef2b24a-f9d8-4329-9702-fb02bc90329a",
        "name": "John",
        "surname": "Doe",
        "accounts": [
          {
            "id": "0ec91590-ee51-4dca-ad0c-afed5d5a8430",
            "balance": 100.00,
            "transactions": [
              {
                "id": "d4062ce1-f3b5-4634-bb6f-421b2fd30e08",
                "transactionType": "INITIAL",
                "amount": 100.00,
                "transactionDate": "2024-09-16T17:07:25.530503"
              }
            ],
            "creationDate": "2024-09-16T17:07:25.530503"
          }
        ]
      }

      ```


## Running Tests

### Unit Tests

```sh
./mvnw test
```
