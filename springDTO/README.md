# springDTO

Spring Boot REST API for user management (DTO + MapStruct + JPA/MySQL).

## Prerequisites

- Java 21
- MySQL running on `localhost:3306` (database `userdb` is auto-created)
- Update `spring.datasource.username/password` in `src/main/resources/application.properties`

## Run

```bash
./mvnw spring-boot:run
```

App starts at `http://localhost:9090`.

## Swagger UI

- Swagger UI: <http://localhost:9090/swagger-ui/index.html>
- OpenAPI JSON: <http://localhost:9090/v3/api-docs>

You can also import `http://localhost:9090/v3/api-docs` into Postman
(**Import → Link**) to generate the whole collection automatically.

## Testing the API in Postman

Base URL: `http://localhost:9090/api/v1/users`

For requests with a body, set **Body → raw → JSON** (header `Content-Type: application/json`).

### 1. Create user — `POST /api/v1/users`

```json
{
  "fName": "John",
  "lName": "Doe",
  "emailAddress": "john.doe@example.com",
  "phone": "9876543210"
}
```

Expected: `201 Created` with the saved user (including `id`).

### 2. Get user by id — `GET /api/v1/users/{id}`

Example: `GET http://localhost:9090/api/v1/users/1` → `200 OK`
(unknown id → not found error).

### 3. Get all users (paginated) — `GET /api/v1/users`

Query params (optional): `page`, `size` (default 20), `sort`.

`sort` uses DTO field names only: `id`, `fName`, `lName`, `emailAddress`, `phone`
(e.g. `sort=lName,asc`). Separate multiple fields with `;`
(e.g. `sort=fName,asc;id,desc`). Any other field returns `400`.

Example: `GET http://localhost:9090/api/v1/users?page=0&size=10&sort=id,desc` → `200 OK`

### 4. Update user — `PUT /api/v1/users/{id}`

```json
{
  "fName": "Johnny",
  "lName": "Doe",
  "emailAddress": "johnny.doe@example.com",
  "phone": "9876543210"
}
```

Expected: `200 OK` with the updated user.

### 5. Delete user — `DELETE /api/v1/users/{id}`

Expected: `204 No Content`.

## Validation rules

| Field          | Rule                          |
|----------------|-------------------------------|
| `fName`        | required, 3–20 characters     |
| `lName`        | required                      |
| `emailAddress` | required, valid email format  |
| `phone`        | optional                      |

Invalid input returns an error response from `GlobalExceptionHandler`.

## Postman tests

A ready-made test file, `springDTO.postman_tests.json`, is in the project root.
Import it via **Import → File** in Postman and run it with the Collection Runner.
