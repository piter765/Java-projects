import java.sql.*;

public class DatabaseConnector {

    public static void main(String[] args) {
        // JDBC URL, username, and password of MySQL server
        String url = "jdbc:mysql://localhost:3306/javatest";
        String user = "root";
        String password = "Gothamcity123";

        // JDBC variables for opening, closing, and managing connection
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Open a connection
            connection = DriverManager.getConnection(url, user, password);

            // Execute a query
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM employees");

            // Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                System.out.println("ID: " + id + ", firstName: " + firstName + ", lastName: " + lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection and release resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
