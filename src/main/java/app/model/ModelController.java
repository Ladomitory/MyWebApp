package app.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModelController {
    private static final ModelController instance = new ModelController();

    private final String driverPackage = "org.postgresql.Driver";
    private final String jdbcURL = "jdbc:postgresql://localhost:5432/MyWebApp";
    private final String dbUsername = "root";
    private final String dbPassword = "password";
    private final Connection connection;

    private ModelController() {
        try {
            Class.forName(driverPackage);
            connection = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ModelController getInstance() {
        return instance;
    }

    public int add(String username, String password, String name, LocalDate birthDate) {
        if (username.length() > 30) {
            return 101;
        } if (checkUsername(username)) {
            return 201;
        } if (password.length() > 30) {
            return 102;
        } if (name.length() > 60) {
            return 103;
        } if (birthDate.isAfter(LocalDate.now())) {
            return 104;
        }
        try {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT id FROM users");
            ResultSet result = selectStatement.getResultSet();
//            Statement selectStatement = connection.createStatement();
//            String sqlRequest = "SELECT id FROM users";
//            ResultSet result = selectStatement.executeQuery(sqlRequest);
            int id = 0;
            while (result.next()) {
                int _id = result.getInt("id");
                if (_id > id) {
                    id = _id;
                }
            }
            id += 1;

            PreparedStatement insertStatement = connection.prepareStatement(
                    """
                            INSERT INTO users
                            (
                                id,
                                username,
                                password,
                                name,
                                birthdate
                            )
                            VALUES (?, ?, ?, ?, ?)
                            """
            );
            insertStatement.setInt(1, id);
            insertStatement.setString(2, username);
            insertStatement.setString(3, password);
            insertStatement.setString(4, name);
            insertStatement.setDate(5, Date.valueOf(birthDate));
            insertStatement.executeUpdate();

//            String sqlRequest2 = "INSERT INTO users (id, username, password, name, birthdate) VALUES ('"
//                    + id + "','"
//                    + username + "','"
//                    + password + "','"
//                    + name + "','"
//                    + Date.valueOf(birthDate) + "')";
//            selectStatement.executeUpdate(sqlRequest2);

            selectStatement.close();
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int changePassword(String username, String oldPassword, String newPassword) {
        if (!checkUser(username, oldPassword)) {
            return 100;
        } if (newPassword.length() > 30) {
            return 103;
        }
        try {
            PreparedStatement updateStatement = connection.prepareStatement(
                    """
                            UPDATE users 
                            SET password = ? 
                            WHERE username = ?
                            """
            );
            updateStatement.setString(1, newPassword);
            updateStatement.setString(2, username);
            updateStatement.executeUpdate();
//            Statement statement = connection.createStatement();
//            String sqlRequest = "UPDATE users SET password='"
//                    + newPassword + "' WHERE username='"
//                    + username + "'";
//            statement.executeUpdate(sqlRequest);
//            statement.close();
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int changeName(String username, String password, String name) {
        if (!checkUser(username, password)) {
            return 100;
        } if (name.length() > 60) {
            return 103;
        }
        try {
            PreparedStatement updateStatement = connection.prepareStatement(
                    """
                            UPDATE users
                            SET name = ?
                            WHERE username = ?
                            """
            );
            updateStatement.setString(1, name);
            updateStatement.setString(2, username);
            updateStatement.executeUpdate();
//            Statement statement = connection.createStatement();
//            String sqlRequest = "UPDATE users SET name='"
//                    + name + "' WHERE username='"
//                    + username + "'";
//            statement.executeUpdate(sqlRequest);
//            statement.close();
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int changeBirthDate(String username, String oldPassword, LocalDate birthDate) {
        if (!checkUser(username, oldPassword)) {
            return 100;
        } if (birthDate.isAfter(LocalDate.now())) {
            return 103;
        }
        try {
            PreparedStatement updateStatement = connection.prepareStatement(
                    """
                            UPDATE users
                            SET birthdate = ?
                            WHERE username = ?
                            """
            );
            updateStatement.setDate(1, Date.valueOf(birthDate));
            updateStatement.setString(2, username);
            updateStatement.executeUpdate();
//            Statement statement = connection.createStatement();
//            String sqlRequest = "UPDATE users SET birthdate='"
//                    + Date.valueOf(birthDate) + "' WHERE username='"
//                    + username + "'";
//            statement.executeUpdate(sqlRequest);
//            statement.close();
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkUser(String username, String password) {
        try {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT username, password FROM users");
            ResultSet result = selectStatement.getResultSet();
//            Statement statement = connection.createStatement();
//            String sqlRequest = "SELECT username, password FROM users";
//            ResultSet result = statement.executeQuery(sqlRequest);
            while (result.next()) {
                String _username = result.getString("Username");
                String _password = result.getString("Password");

                if (_username.equals(username) && _password.equals(password)) {
                    return true;
                }
            }
//            statement.close();
            return false;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean checkUsername(String username) {
        try {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT username FROM users");
            ResultSet result = selectStatement.getResultSet();
//            Statement statement = connection.createStatement();
//            String sqlRequest = "SELECT username FROM users";
//            ResultSet result = statement.executeQuery(sqlRequest);
            while (result.next()) {
                String _username = result.getString("Username");

                if (_username.equals(username)) {
                    return true;
                }
            }
//            statement.close();
            return false;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<String> getListUsers() {
        try {
            PreparedStatement selectStatement = connection.prepareStatement("SELECT username, name, birthdate FROM users");
            ResultSet result = selectStatement.getResultSet();
//            Statement statement = connection.createStatement();
//            String sqlRequest = "SELECT username, name, birthdate FROM users";
//            ResultSet result = statement.executeQuery(sqlRequest);
            List<String> answer = new ArrayList<>();
            while (result.next()) {
                String _username = result.getString("username");
                String _name = result.getString("name");
                Date _birthDate = result.getDate("birthDate");
                answer.add(_username + " - " + _name + " - " + _birthDate.toString());
            }
//            statement.close();
            return answer;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<String> getUser(String username) {
        try {
            PreparedStatement selectStatment = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            selectStatment.setString(1, username);
            ResultSet result = selectStatment.getResultSet();
//            Statement statement = connection.createStatement();
//            String sqlReques = "SELECT * FROM users WHERE username='" + username + "'";
//            ResultSet result = statement.executeQuery(sqlReques);
            List<String> answer = new ArrayList<>();
            result.next();
            answer.add(result.getString("username"));
            answer.add(result.getString("name"));
            answer.add(result.getDate("birthdate").toString());
//            statement.close();
            return answer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
