Perfect 🙌 thanks for sharing all your controllers — now I can make the README **very accurate** with actual endpoints from your project.

Here’s a refined **README.md** tailored to your codebase:

---

# 🛒 Ecommerce API - Spring Boot

A RESTful backend API for an e-commerce platform built with **Spring Boot**, **Spring Security (JWT)**, **JPA/Hibernate**, and **MySQL**.
Supports **authentication, user management, product catalog, categories, and orders**.

---

## 🚀 Features

* ✅ User registration & login with JWT authentication
* ✅ Role-based access (Admin/User)
* ✅ Manage **Products**, **Categories**, and **Orders**
* ✅ Secure password storage with BCrypt
* ✅ Search products and categories

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot 3.x
* Spring Security (JWT)
* Spring Data JPA (Hibernate)
* MySQL
* Lombok

---

## ⚙️ Setup Instructions

### Clone & Run

```bash
git clone https://gitlab.com/indrajit-group/E-Commerce-Spring-Boot.git
cd E-Commerce-Spring-Boot
mvn clean install
mvn spring-boot:run
```

### Configure Database

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

jwt.secret=your_jwt_secret_key
jwt.expiration=86400000
```

---

## 📌 API Endpoints

### 🔐 Authentication (`/api/auth`)

| Method | Endpoint  | Description                    | Auth |
| ------ | --------- | ------------------------------ | ---- |
| POST   | `/signin` | Authenticate user, returns JWT | ❌    |
| POST   | `/signup` | Register new user              | ❌    |

---

### 👤 Users (`/api/users`)

| Method | Endpoint         | Description       | Auth      |
| ------ | ---------------- | ----------------- | --------- |
| GET    | `/`              | Get all users     | ✅ (ADMIN) |
| GET    | `/{id}`          | Get user by ID    | ✅         |
| GET    | `/email/{email}` | Get user by email | ✅         |
| PUT    | `/{id}`          | Update user       | ✅         |
| DELETE | `/{id}`          | Delete user       | ✅ (ADMIN) |

---

### 📦 Products (`/api/products`)

| Method | Endpoint                 | Description              | Auth      |
| ------ | ------------------------ | ------------------------ | --------- |
| GET    | `/`                      | Get all products         | ❌         |
| GET    | `/{id}`                  | Get product by ID        | ❌         |
| GET    | `/name/{name}`           | Search products by name  | ❌         |
| GET    | `/category/{categoryId}` | Get products by category | ❌         |
| POST   | `/`                      | Create product           | ✅ (ADMIN) |
| PUT    | `/{id}`                  | Update product           | ✅ (ADMIN) |
| DELETE | `/{id}`                  | Delete product           | ✅ (ADMIN) |

---

### 🗂️ Categories (`/api/categories`)

| Method | Endpoint         | Description             | Auth      |
| ------ | ---------------- | ----------------------- | --------- |
| GET    | `/`              | Get all categories      | ❌         |
| GET    | `/{id}`          | Get category by ID      | ❌         |
| GET    | `/search?name=X` | Search category by name | ❌         |
| POST   | `/`              | Create category         | ✅ (ADMIN) |
| PUT    | `/{id}`          | Update category         | ✅ (ADMIN) |

---

### 🛒 Orders (`/api/orders`)

| Method | Endpoint                | Description         | Auth      |
| ------ | ----------------------- | ------------------- | --------- |
| GET    | `/`                     | Get all orders      | ✅ (ADMIN) |
| GET    | `/{id}`                 | Get order by ID     | ✅         |
| GET    | `/user/{userId}`        | Get orders by user  | ✅         |
| POST   | `/`                     | Create new order    | ✅         |
| PUT    | `/{id}/status?status=X` | Update order status | ✅ (ADMIN) |
| DELETE | `/{id}`                 | Delete/cancel order | ✅         |

---

## 🔑 Authentication Usage

1. **Signup**

```http
POST /api/auth/signup
Content-Type: application/json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "123456",
  "role": "USER"
}
```

2. **Signin**

```http
POST /api/auth/signin
Content-Type: application/json
{
  "email": "john@example.com",
  "password": "123456"
}
```

Response:

```json
"eyJhbGciOiJIUzI1NiJ9..."
```

3. **Use JWT Token**
   Send token in `Authorization` header:

```
Authorization: Bearer <your_token>
```

---

## 📂 Project Structure

```
src/main/java/com/Indrajit/ecommerce_api
├── controller/     # REST controllers
├── dto/            # Response DTOs
├── model/          # Entities (User, Product, Category, Order)
├── repository/     # JPA repositories
├── security/       # JWT utilities, filters, config
├── service/        # Business services
└── EcommerceApiApplication.java
```

---

## ✅ Future Improvements

* Shopping cart API
* Payment gateway integration
* Product reviews/ratings

---

👉 Do you also want me to generate **sample cURL requests** for testing each endpoint (so you can run them directly in terminal/Postman)?
