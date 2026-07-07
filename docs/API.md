# TeamFlow API Documentation

## Base URL

```
http://localhost:8080/api
```

---

# Authentication

## Register

POST `/auth/register`

Request

```json
{
  "fullName": "John Doe",
  "email": "john@example.com",
  "password": "password123"
}
```

Response

```json
{
  "success": true,
  "message": "User Registered Successfully"
}
```

---

## Login

POST `/auth/login`

Request

```json
{
  "email": "john@example.com",
  "password": "password123"
}
```

Response

```json
{
  "token": "JWT_TOKEN"
}
```

---

# Dashboard

GET `/dashboard`

Returns:

- Total Projects
- Total Tasks
- Completed Tasks
- Pending Tasks
- Total Members
- Total Notifications

---

# Projects

GET `/projects`

POST `/projects`

PUT `/projects/{id}`

DELETE `/projects/{id}`

---

# Tasks

GET `/tasks`

POST `/tasks`

PUT `/tasks/{id}`

DELETE `/tasks/{id}`

---

# Comments

GET `/comments/task/{taskId}`

POST `/comments`

DELETE `/comments/{id}`

---

# Attachments

GET `/attachments/task/{taskId}`

POST `/attachments`

DELETE `/attachments/{id}`

---

# Notifications

GET `/notifications/{userId}`

---

# User

GET `/users/{id}`

PUT `/users/{id}`

---

## Authentication

All secured endpoints require

```
Authorization: Bearer <JWT_TOKEN>
```

---

Swagger Documentation

```
http://localhost:8080/swagger-ui/index.html
```