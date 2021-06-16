package test;

import java.sql.*;

public class DataBaseTest {

	public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "mocis";
        final String url = "jdbc:postgresql://localhost:5432/phones_magazine";
        
        final Connection connection = DriverManager.getConnection(url, user, password);

        
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = (?)")) {

            statement.setInt(1, 2);

            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String byName = "login: " + resultSet.getString("login");
                String byIndex = "password: " + resultSet.getString(3);
                final int role = resultSet.getInt("role");
                System.out.println(byName);
                System.out.println(byIndex);
                System.out.println("role: " +role);
            }
        } finally {
            connection.close();
        }
    }
}
