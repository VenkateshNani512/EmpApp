# EmpApp

EmpApp is an Android Employee Management application built using modern Android development practices.

### Tech Stack

* Kotlin
* Jetpack Compose
* MVVM
* Clean Architecture
* Hilt Dependency Injection
* Retrofit & OkHttp
* Kotlin Coroutines & Flow
* DataStore
* JWT Authentication
* REST APIs
* Spring Boot backend
* PostgreSQL

### Features

* User registration and login
* JWT-based authentication
* Employee list
* Employee details
* API integration using Retrofit
* Loading and error state handling
* Clean separation of UI, domain, and data layers
* Secure token storage using DataStore

### Architecture

```text
Presentation
     ↓
ViewModel
     ↓
Domain / Repository
     ↓
Data / Repository Implementation
     ↓
Retrofit API
     ↓
Spring Boot REST API
     ↓
PostgreSQL
```

This project is also used as a practical learning project for modern Android architecture, API integration, authentication, testing, and backend communication.
