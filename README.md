# eBanking-Application
This Java program is a simple console-based banking application that connects to a MySQL database. Users can create an account, log in, deposit money, withdraw money, and check their balance.

## Features

1. **Create Account**: Users can create a new account by providing a username, password, and initial balance.
2. **Log In**: Users can log in with their username and password.
3. **Deposit Money**: Users can deposit money into their account.
4. **Withdraw Money**: Users can withdraw money from their account.
5. **Balance Enquiry**: Users can check their account balance.

## Prerequisites

1. **Java Development Kit (JDK)**: Ensure you have JDK installed on your machine.
2. **MySQL Database**: Ensure you have MySQL installed and running.
3. **JDBC Driver**: Ensure you have the MySQL JDBC driver (`mysql-connector-java`) in your classpath.
4. **Database Setup**: Create a MySQL database and a table with the following structure:

```sql
CREATE DATABASE pro1;

USE pro1;

CREATE TABLE account (
    uname VARCHAR(50) PRIMARY KEY,
    pass VARCHAR(50),
    bal INT
);
```

## How to Run

1. **Compile the Program**: Open a terminal and navigate to the directory containing the Java file. Compile the program using:
    ```bash
    javac Main.java
    ```

2. **Run the Program**: Execute the program using:
    ```bash
    java Main
    ```

3. **Create an Account**:
    - Choose option `1` to create a new account.
    - Enter a username, password, and initial balance.
    - The account details will be saved in the database.

4. **Log In**:
    - Choose option `2` to log in.
    - Enter your username and password.
    - If the credentials are correct, you will be logged in.

5. **Banking Operations**:
    - After logging in, choose one of the following options:
        1. Deposit money: Enter the amount to deposit.
        2. Withdraw money: Enter the amount to withdraw (ensure you have sufficient balance).
        3. Balance Enquiry: Check your current balance.

## Code Explanation

### Database Connection

- **Driver Registration**: Registers the MySQL JDBC driver.
    ```java
    Class.forName("com.mysql.cj.jdbc.Driver");
    ```

- **Connection Establishment**: Establishes a connection to the database.
    ```java
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pro1", "root", "Apavan");
    ```

### User Input and Operations

- **Scanner**: Used to take user input.
    ```java
    Scanner sc = new Scanner(System.in);
    ```

- **Account Creation**: Inserts user details into the database.
    ```java
    query = "INSERT INTO account values('"+Uname+"','"+pass+"',"+bal+");";
    st.execute(query);
    ```

- **Log In**: Verifies user credentials and allows further operations.
    ```java
    rs = st.executeQuery("SELECT pass from account where uname='"+u_name+"'");
    if(u_pass.equals(rs.getString(1))){
        // Perform banking operations
    } else {
        throw new invalidCredentials();
    }
    ```

### Banking Operations

- **Deposit**: Updates the balance in the database.
    ```java
    bal += amt;
    st.execute("UPDATE account SET bal ="+bal+" where uname='"+u_name+"'");
    ```

- **Withdraw**: Checks for sufficient balance and updates the database.
    ```java
    if(bal < amt){
        sopl("Insufficient Balance");
    } else {
        bal -= amt;
        st.execute("UPDATE account SET bal ="+bal+" where uname='"+u_name+"'");
    }
    ```

- **Balance Enquiry**: Retrieves and displays the balance.
    ```java
    rs = st.executeQuery("SELECT bal from account where uname='"+u_name+"'");
    sopl("Available Balance is: "+bal);
    ```

### Custom Exception

- **invalidCredentials**: Custom exception for invalid login credentials.
    ```java
    class invalidCredentials extends Exception {}
    ```

### Helper Methods

- `sop(String msg)`: Prints a message without a newline.
- `sopl(String msg)`: Prints a message with a newline.

## Note

- Ensure the MySQL server is running and accessible.
- Modify the database connection details (`jdbc:mysql://localhost:3306/pro1`, `root`, `Apavan`) as per your setup.
- Handle exceptions appropriately for production-grade applications.

## Author

- Developed by: [Your Name or Identifier]

## License

- This program is for educational purposes. Feel free to modify and enhance it.
