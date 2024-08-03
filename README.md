# JournalApp

JournalApp is a simple journal management application that allows users to create, read, update, and delete journal entries. It also includes user management features. This application uses Spring Boot, Spring Security for authentication and authorization, and MongoDB Atlas as the cloud database.

## Features

- User authentication and authorization using JWT
- Create, read, update, and delete journal entries
- Basic health check endpoint
- User management (create, update, delete)
- MongoDB Atlas for cloud-based storage
- Weather update facility with the help of a weather API
- Scheduled message sending through email using JavaMailSender
- Redis cloud integration and caching

## Prerequisites

- Java installed
- API platform installed (e.g., Postman or Hoppscotch)
- VS Code or IntelliJ installed

## Installation

1. **Clone the Repository**
    ```bash
    git clone https://github.com/Medhansh-32/Journal_Spring.git
    ```

2. **Navigate to the Project Directory**
    ```bash
    cd journalApp
    ```

3. **Build the Project**
    ```bash
    mvn clean package
    ```

4. **Locate the Executable .jar File**
    ```
    JournalApp/Journal_Spring/journalApp/target/journalApp-0.0.1-SNAPSHOT.jar
    ```

## Executing

Open a terminal and navigate to the directory where `journalApp-0.0.1-SNAPSHOT.jar` is stored. Execute the command:
```bash
java -jar journalApp-0.0.1-SNAPSHOT.jar
Access the Application

Open any API platform.

The application will be available at http://localhost:8080.
API Endpoints
Demo for JSON Body

User:

json

{
    "userName": "John Doe",
    "password": "********"
}

Journal Entry:

json

{
    "title": "Here Comes The Title",
    "content": "Here is the content"
}

Public Endpoints

    Health Check
        GET /public/health-check
        Response: "ok"

    Get All Users
        GET /public
        Response: List of Users

    Create a User
        POST /public/create-user
        Body: User JSON
        Response: Created User

User Endpoints (Authenticated)

    Get All Journal Entries of a User
        GET /journal
        Response: List of Journal Entries

    Create a Journal Entry
        POST /journal
        Body: JournalEntry JSON
        Response: Created Journal Entry

    Get a Journal Entry by ID
        GET /journal/id/{myId}
        Response: Journal Entry

    Delete a Journal Entry by ID
        DELETE /journal/id/{myId}
        Response: No Content (204) or Not Found (404)

    Update a Journal Entry by ID
        PATCH /journal/id/{myId}
        Body: JournalEntry JSON
        Response: Updated Journal Entry

User Management Endpoints (Authenticated)

    Update User
        PUT /user
        Body: User JSON
        Response: No Content (204)

    Delete User
        DELETE /user
        Response: No Content (204)

Authentication and Authorization

The application uses JWT-based authentication.

    /journal/ and /user/ endpoints require authentication.
    /admin/ endpoints require the user to have the role ADMIN.
    All other endpoints are public and do not require authentication.

Example of Basic Authentication

You can use tools like Postman or cURL to interact with the endpoints.

Using cURL

curl -u username:password http://localhost:8080/journal

Using Postman

    Open Postman.
    Set the request type (GET, POST, etc.) and the endpoint URL.
    Go to the "Authorization" tab.
    Select "Jwt Token" and enter your Token provided after successfull signup.
    Send the request.

Security Configuration

The security configuration is defined in the SpringSecurity class.

    AuthenticationManagerBuilder is configured to use the custom UserDetailsServiceImpl and BCryptPasswordEncoder.
    SecurityFilterChain defines the security filter chain, specifying which endpoints require authentication and authorization.

User Service and Repository

    UserService provides methods to interact with the user data.
    UserRepository interacts with MongoDB to perform CRUD operations on user data.

Journal Entry Service and Repository

    JournalEntryService provides methods to manage journal entries.
    JournalEntryRepository interacts with MongoDB to perform CRUD operations on journal data.

Conclusion

This README provides a comprehensive guide on how to use the JournalApp. If you have any questions or issues, feel free to open an issue on the GitHub repository.

Enjoy journaling with JournalApp!






