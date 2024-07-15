JournalApp is a simple journal management application that allows users to create, read, update, and delete journal entries. It also includes user management features. This application uses Spring Boot, Spring Security for authentication and authorization, and MongoDB Atlas as the cloud database.

<h2>Features</h2>

    User authentication and authorization
    Create, read, update, and delete journal entries
    Basic health check endpoint
    User management (create, update, delete)
    MongoDB Atlas for cloud-based storage

<h2>Prerequisites</h2>

    Java installed
    API platform installed like : Postman or Hoppscotch
    VS Code or IntelliJ Installed
    
<h2>Installation</h2>
Clone the Repository <br>

<pre>
  <code>
git clone https://github.com/Medhansh-32/Journal_Spring.git
  </code>
  </pre>
  <pre>
  <code>
cd journalApp
  </code>
  </pre>
  <pre>
  <code>
mvn clean package
  </code>
</pre>



At this location you will find executable .jar file : JournalApp\Journal_Spring\journalApp\target\journalApp-0.0.1-SNAPSHOT.jar
<br>
<h2>Executing</h2>
Open terminal and come into the directory where journalApp-0.0.1-SNAPSHOT.jar is stored.
Execute the command :
<pre><code>java -jar journalApp-0.0.1-SNAPSHOT.jar</code></pre>

    
<h2>Access the application</h2>
Open any API platform <br>  
The application will be available at http://localhost:8080.

<h2>API Endpoints</h2>

<h3>Public Endpoints</h3>

<strong>Health Check</strong>
<pre>
<code>GET /public/health-check
Response: "ok"
</code>
</pre>

<strong>Get All Users</strong>
<pre>
<code>GET /public
Response: List of Users
</code>
</pre>

<strong>Create a User</strong>
<pre>
<code>POST /public/create-user
Body: User JSON
Response: Created User
</code>
</pre>

<h3>User Endpoints (Authenticated)</h3>

<strong>Get All Journal Entries of a User</strong>
<pre>
<code>GET /journal
Response: List of Journal Entries
</code>
</pre>

<strong>Create a Journal Entry</strong>
<pre>
<code>POST /journal
Body: JournalEntry JSON
Response: Created Journal Entry
</code>
</pre>

<strong>Get a Journal Entry by ID</strong>
<pre>
<code>GET /journal/id/{myId}
Response: Journal Entry
</code>
</pre>

<strong>Delete a Journal Entry by ID</strong>
<pre>
<code>DELETE /journal/id/{myId}
Response: No Content (204) or Not Found (404)
</code>
</pre>

<strong>Update a Journal Entry by ID</strong>
<pre>
<code>PATCH /journal/id/{myId}
Body: JournalEntry JSON
Response: Updated Journal Entry
</code>
</pre>

<h3>User Management Endpoints (Authenticated)</h3>

<strong>Update User</strong>
<pre>
<code>PUT /user
Body: User JSON
Response: No Content (204)
</code>
</pre>

<strong>Delete User</strong>
<pre>
<code>DELETE /user
Response: No Content (204)
</code>
</pre>


Authentication and Authorization

The application uses basic HTTP authentication.

    /journal/ and /user/ endpoints require authentication.
    /admin/ endpoints require the user to have the role ADMIN.
    All other endpoints are public and do not require authentication.

Example of Basic Authentication

You can use tools like Postman or cURL to interact with the endpoints.

    Using cURL

    bash

    curl -u username:password http://localhost:8080/journal

    Using Postman
        Open Postman.
        Set the request type (GET, POST, etc.) and the endpoint URL.
        Go to the "Authorization" tab.
        Select "Basic Auth" and enter your username and password.
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
    JournalEntryRepository interacts with MongoDB to perform CRUD operations on user data.

Conclusion

This README provides a comprehensive guide on how to use the JournalApp. If you have any questions or issues, feel free to open an issue on the GitHub repository.

Enjoy journaling with JournalApp!
