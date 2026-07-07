# Installation Guide

## Requirements

- Java 21
- Maven
- MySQL 8+
- Node.js 18+
- Git

---

# Clone Repository

```bash
git clone https://github.com/Raghunath09/TeamFlow.git

cd TeamFlow
```

---

# Backend Setup

```bash
cd backend
```

Configure

```
src/main/resources/application.properties
```

Example

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/teamflow_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

Run

```bash
mvn spring-boot:run
```

Backend URL

```
http://localhost:8080
```

Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---

# Frontend Setup

```bash
cd frontend

npm install

npm run dev
```

Frontend

```
http://localhost:5173
```

---

# Login

Register a new account

or

Use an existing account

---

# Build Production

Backend

```bash
mvn clean package
```

Frontend

```bash
npm run build
```