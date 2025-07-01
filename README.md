# SalesSystem-DataBase (Database Version + GUI)

This is a Java-based application that simulates a sales management system with a graphical user interface (GUI).  
Unlike the file-based version, this version uses a MySQL database to store and manage data.

## Features
- Add, edit, delete, and search products
- Manage user information
- Data is stored persistently in MySQL
- Graphical User Interface (GUI) using Java Swing

## Technologies Used
- Java (Advanced Level)
- Swing (for GUI)
- MySQL / SQL
- JDBC (Java Database Connectivity)
- NetBeans IDE

## Database Setup
Before running the project, create a MySQL database (e.g., `account`) and create the required tables such as:

- `ads`
- `chat`
- `useraccountinfo`

> ⚠️ The application will handle inserting, updating, and deleting records.  
> You only need to create the empty tables — no need to add any data manually.

Make sure to update your **database connection settings** (username/password, database name) in the source code if needed.

## How to Run
1. Clone the repository
2. Open the project in NetBeans
3. Set up the MySQL database and tables
4. Run the `Selldatabase.java` (or your GUI launcher class)

## Author
[AyMa84]

