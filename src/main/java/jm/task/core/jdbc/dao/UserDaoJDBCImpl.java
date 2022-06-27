package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;
    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }
    @Override
    public void createUsersTable() throws SQLException {
        String create = "CREATE TABLE users (id int auto_increment primary key, name varchar(255) not null, lastName varchar(255) not null, age TINYINT(255) not null)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(create);
            connection.commit();
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Table with this name already exist");
        } catch (SQLException e1) {
            e1.printStackTrace();
            connection.rollback();
        }
    }
    @Override
    public void dropUsersTable() throws SQLException {
        String drop = "DROP TABLE users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(drop);
            connection.commit();
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Table with this name don't exist");
        } catch (SQLException e1) {
            e1.printStackTrace();
            connection.rollback();
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String save = "INSERT INTO users (name, lastName, age) VALUES (" + "\"" + name + "\"" + "," + "\"" + lastName + "\"" + "," + "\"" + age + "\"" + ");";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(save);
            connection.commit();
            System.out.println("User with the name " + name + " and at the age of " + age + " years was added to DB");
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }
    @Override
    public void removeUserById(long id) throws SQLException {
        String remove = "DELETE FROM users WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(remove);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }
    @Override
    public List<User> getAllUsers() throws SQLException {
        List <User> usersList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultset = statement.executeQuery("SELECT * FROM users");
            connection.commit();
            while (resultset.next()) {
                User user = new User(resultset.getString(2), resultset.getString(3), resultset.getByte(4));
                user.setId(resultset.getLong(1));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        return usersList;
    }
    @Override
    public void cleanUsersTable() throws SQLException {
        String clean = "DELETE FROM users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clean);
            connection.commit();
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Table with this name don't exist");
        } catch (SQLException e1) {
            e1.printStackTrace();
            connection.rollback();
        }
    }
}
