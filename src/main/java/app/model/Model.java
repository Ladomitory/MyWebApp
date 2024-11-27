package app.model;

import app.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private static Model instance = new Model();
    private final String driverPackage = "org.postgresql.Driver";
    private final String jdbcURL = "jdbc:postgresql://localhost:5432/testPSQL";
    private final String dbUsername = "postgres";
    private final String dbPassword = "ASDzxc123";

    private Model() {
        try {
            Class.forName(driverPackage);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Model getInstance() {
        return instance;
    }

    public void add(User user) {
        try {
            Connection conn = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
            Statement statement = conn.createStatement();
            String sqlRequest = "INSERT INTO users (username, password, age) VALUES ('"
                    + user.getUsername() + "', '"
                    + user.getPassword() + "', '0')";
            statement.executeUpdate(sqlRequest);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void changePassword(User user, String newPassword) {
        try {
            Connection conn = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
            Statement statement = conn.createStatement();
            String sqlRequest = "UPDATE users SET password='" + newPassword + "' WHERE username='" + user.getUsername() + "'";
            statement.executeUpdate(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkUser(User user) {
        try {
            Connection conn = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
            Statement statement = conn.createStatement();
            String sqlRequest = "SELECT username, password FROM users";
            ResultSet result = statement.executeQuery(sqlRequest);
            while (result.next()) {
                String _username = result.getString("username");
                String _password = result.getString("password");

                if (_username.equals(user.getUsername()) && _password.equals(user.getPassword())) {
                    return true;
                }
            }
            return false;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean checkUsername(String username) {
        try {
            Connection conn = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
            Statement statement = conn.createStatement();
            String sqlRequest = "SELECT username FROM users";
            ResultSet result = statement.executeQuery(sqlRequest);
            while (result.next()) {
                String _username = result.getString("username");

                if (_username.equals(username)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<String> getListUsernames() {
        try {
            Connection conn = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
            Statement statement = conn.createStatement();
            String sqlRequest = "SELECT username FROM users";
            ResultSet result = statement.executeQuery(sqlRequest);
            List<String> answer = new ArrayList<>();
            while (result.next()) {
                String username = result.getString("username");
                answer.add(username);
            }
            return answer;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
