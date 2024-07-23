package banking.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLJDBC {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bankmanagementsystem";
        String username = "root";
        String password = "Pranu@123";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Load MySQL JDBC Driver (Not required in newer JDBC versions)
            // Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Create a statement
            statement = connection.createStatement();

            // Execute a query
            String sql = "SELECT * FROM Signup2"; // Replace with your actual table name
            resultSet = statement.executeQuery(sql);

            // Process the result set
            while (resultSet.next()) {
                System.out.println("Column1: " + resultSet.getString("column1"));
                System.out.println("Column2: " + resultSet.getString("column2"));
                // Adjust column names according to your table structure
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the result set, statement, and connection in the finally block
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
