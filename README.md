# Banking Application API

This project provides a RESTful API for managing banking accounts. 
It supports CRUD operations, deposit and withdrawal functionalities, and account management.

## Features

- **Account Management**
    - Create, retrieve, update, and delete accounts.
- **Transaction Operations**
    - Deposit and withdraw funds.
 
## API Endpoints

### **Base URL:** `/api/bankingapp`

| HTTP Method | Endpoint            | Description                      |
| ----------- | ------------------- | -------------------------------- |
| `GET`       | `/{id}`             | Get details of an account by ID. |
| `GET`       | `/accounts`         | Get a list of all accounts.      |
| `POST`      | `/`                 | Create a new account.            |
| `DELETE`    | `/{id}`             | Delete an account by ID.         |
| `PUT`       | `/{id}`             | Update the name of an account.   |
| `POST`      | `/{id}/{operation}` | Deposit or withdraw funds.       |

## Key Functionalities

### 1. Account Creation
- **Logic**:
    - Retrieves the smallest available account number from the AccountPool. If available, assigns that number to the new account.
    - If no available account number is found, it generates a new account number by fetching the maximum existing account number from the repository.

- **Dependencies**: AccountRepository, AccountPoolRepository.

### 2.  Account Deletion
- **Logic**: Deletes the specified account by ID and returns the account number to the AccountPool for future use.

- **Dependencies**: AccountRepository, AccountPoolRepository.

### 3. Get All Accounts
- **Logic**: Retrieves all accounts from the database.

- **Dependencies**: AccountRepository.

### 4. Update Account Name
- **Logic**: Updates the name of the account holder for a given account ID.

- **Dependencies**: AccountRepository.

### 5. Get Account Details
- **Logic**: Retrieves an account by its ID.

- **Dependencies**: AccountRepository.

### 6. Deposit Funds
- **Logic**: Increases the account balance by the specified deposit amount.

- **Dependencies**: AccountRepository.

### 7. Withdraw Funds
- **Logic**: Decreases the account balance by the specified withdrawal amount. Ensures that the balance doesn't go below zero.

- **Dependencies**: AccountRepository.

## Request and Response Examples

### 1. Get Account by ID

Endpoint: `GET /{id}`  
Response:

```json
{
  "id": 1,
  "name": "John Doe",
  "balance": 1000.0
}
```
### 2. Create an Account
Endpoint: `POST /`

Request:

```json
{
  "name": "John Doe",
  "balance": 500.0
}
```
Response:

```json
{
  "id": 2,
  "name": "John Doe",
  "balance": 500.0
}
```
### 3. Update Account Name
Endpoint: `PUT /{id}`

Request:

```json
{
  "newName": "Jane Doe"
}
```
Response:

```json
{
  "id": 2,
  "name": "Jane Doe",
  "balance": 500.0
}
```
### 4. Deposit Funds
Endpoint: `POST /{id}/deposit`

Request:

```json
{
  "amount": 200.0
}
```
Response:

```json
{
  "id": 2,
  "name": "Jane Doe",
  "balance": 700.0
}
```
### 5. Withdraw Funds
Endpoint: `POST /{id}/withdraw`

Request:

```json
{
  "amount": 100.0
}
```
Response:

```json
{
  "id": 2,
  "name": "Jane Doe",
  "balance": 600.0
}
```

**Technologies Used**
- Java
- Spring Boot
- MySQL Database
- Spring Data JPA

_Author
Karthik Panagar_
For any queries or suggestions, feel free to reach out!
