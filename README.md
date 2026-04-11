# 🚀 NexWallet API - Crypto Portfolio Manager

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![OpenFeign](https://img.shields.io/badge/OpenFeign-API_Client-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white)
![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

**NexWallet** is a complete, enterprise-grade RESTful API for cryptocurrency portfolio management. It allows users to securely register, add assets to their wallets, and track their total balance in real-time by seamlessly integrating with the crypto market.

## Architecture & Technical Decisions

This project was built focusing on market best practices, resilience, and high performance:

- **Stateless Security:** Authentication via **JWT (JSON Web Token)** with `Spring Security`, storing *claims* (user data) within the token itself to avoid unnecessary database queries on every request.
- **External Integration:** Usage of **Spring Cloud OpenFeign** to consume the public CoinGecko API in an elegant, strongly-typed, and declarative way, calculating the wallet's real-time value.
- **High Performance:** NoSQL **MongoDB** database (excellent for document flexibility), paired with **Redis** for caching (ready to cache frequent quote returns and save network bandwidth).
- **Defensive Programming & API UX:** Implementation of `ControllerAdvice` to catch global exceptions and standardize API errors (preventing *Stack Trace* leaks and handling data conflicts like *409 Conflict*), along with `Bean Validation` to shield the system against inconsistent data.
- **Live Documentation:** Interactive and testable documentation generated with **Swagger/OpenAPI 3**, fully specifying authorization tokens and query parameters.

## How to Run the Project

The application is fully "Dockerized". You don't need to have Java, Mongo, or Redis installed on your local machine; the container will do all the heavy lifting.

1. Clone the repository:
```bash
git clone [https://github.com/YourUsername/NexWallet.git](https://github.com/YourUsername/NexWallet.git)
cd NexWallet
```

2. Spin up the infrastructure and the API with a single command:
```bash
docker-compose up -d --build
```

3. Access the Swagger documentation in your browser:
```text
http://localhost:8080/swagger-ui.html
```

## Main Endpoints

- `POST /nexwallet/auth/register` - Account creation (with database integrity constraints for duplicate emails).
- `POST /nexwallet/auth/login` - Authentication and JWT Token generation.
- `POST /nexwallet/user/{id}/assets` - Adds a cryptocurrency to the wallet (Updates the balance if the coin already exists in the position).
- `GET /nexwallet/user/{id}/portfolio` - Returns the complete wallet with CoinGecko quotes multiplied in real-time.
- `GET /market/top10` - Returns the top 10 cryptocurrencies in the market.

---
*Developed by https://www.linkedin.com/in/gabrieltiziano/ with 🤍 && ☕️*