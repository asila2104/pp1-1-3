package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            try {
                connection.setAutoCommit(false);

                Statement statement = connection.createStatement();
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                        "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                        "name VARCHAR(255), " +
                        "lastName VARCHAR(255), " +
                        "age INT)");

                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            try {
                connection.setAutoCommit(false);

                Statement statement = connection.createStatement();
                statement.executeUpdate("DROP TABLE IF EXISTS users");

                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            try {
                connection.setAutoCommit(false);

                PreparedStatement statement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?);");
                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setInt(3, age);
                statement.executeUpdate();

                connection.commit();
                System.out.printf("User с именем \"%s %s\" добавлен в базу данных%n", name, lastName);
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            try {
                connection.setAutoCommit(false);

                PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE Id = ?");
                statement.setLong(1, id);
                statement.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();

        try (Connection connection = Util.getConnection()) {
            try {
                String sql = "SELECT * FROM users;";
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);

                while (result.next()) {
                    User user = new User(
                            result.getString("name"),
                            result.getString("lastName"),
                            (byte) result.getInt("age")
                    );
                    user.setId(result.getLong("id"));

                    listUser.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listUser;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            try {
                connection.setAutoCommit(false);

                String sql = "DELETE FROM users";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);

                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
