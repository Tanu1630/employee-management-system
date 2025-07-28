# Employee Assignment Management API

This is a Spring Boot REST API for managing **Employees** and their **Assignments**. It supports full CRUD operations, input validation, exception handling, and includes unit and integration tests using JUnit and MockMvc.

---

## 🧾 Features

- Create, Read, Update, and Delete (CRUD) Employees and Assignments
- Validate input using annotations like `@NotNull`, `@Size`, `@Min`
- Entity relationships (Many-to-One: Assignment → Employee)
- Exception handling with meaningful messages
- RESTful API design using Spring Web
- Unit and integration tests with JUnit and Mockito

---

## 🏗️ Technologies Used

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database (for development/testing)
- JUnit 5
- Mockito
- MockMvc
- Maven

🛠️ API Endpoints

📁 Employee

http://localhost:8080/emp/home
http://localhost:8080/emp/all
http://localhost:8080/emp/addEmp
http://localhost:8080/emp/removeEmp/id
http://localhost:8080/emp/findEmp/id
http://localhost:8080/emp/update/id
http://localhost:8080/emp/updatePartial/id

📄 Assignment

http://localhost:8080/assignment/all
http://localhost:8080/assignment/add
http://localhost:8080/assignment/find/id
http://localhost:8080/assignment/remove/id
http://localhost:8080/assignment/update/id
http://localhost:8080/assignment/partialupdate/id

## 📁 Project Structure

├── controller/
│ ├── EmployeeController.java
│ └── AssignmentController.java
├── service/
│ ├── EmployeeService.java
│ ├── EmployeeServiceImpl.java
│ ├── AssignmentService.java
│ └── AssignmentServiceImpl.java
├── repository/
│ ├── EmployeeRepository.java
│ └── AssignmentRepository.java
├── model/
│ ├── Employee.java
│ └── Assignment.java
├── tests/
│ ├── EmployeeServiceTest.java
│ ├── EmployeeControllerTest.java
│ └── AssignmentControllerTest.java
└── application.properties

 Build and Run the Application

./mvnw spring-boot:run

 http://localhost:8080
 https://web-production-7619f.up.railway.app


🙋‍♀️ Author
Chetna Ghengare