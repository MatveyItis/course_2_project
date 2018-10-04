package ru.kpfu.itis.maletskov.app;

import java.sql.*;

public class Application {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12ER56ui78";
    private static final String URL = "jdbc:postgresql://localhost:5432/";

    public static void main(String[] args) {
        try {
            Connection connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery("SELECT client_id, first_name FROM client");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("client_id") + " " + resultSet.getString("first_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
