package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Statement statement = Util.getStatement();
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String create = "CREATE TABLE UsersTable (id int auto_increment primary key, name varchar(255) not null, lastName varchar(255) not null, age TINYINT(255) not null)";
        try {
            statement.executeUpdate(create);
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Таблица с таким именем уже существует");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE UsersTable";
        try {
            statement.executeUpdate(drop);
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Таблицы с таким именем не существует");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String save = "INSERT INTO UsersTable (name, lastName, age) VALUES (" + "\"" + name + "\"" + "," + "\"" + lastName + "\"" + "," + "\"" + age + "\"" + ");";
        try {
            statement.executeUpdate(save);
            System.out.println("User с именем " + name + "и возрастом" + age + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String remove = "DELETE FROM UsersTable WHERE id=" + id;
        try {
            statement.executeUpdate(remove);
        } catch (SQLSyntaxErrorException e) {
            System.out.println("User с таким ID не существует");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List <User> usersList = new ArrayList<>();
        try {
            ResultSet resultset = statement.executeQuery("SELECT * FROM UsersTable");
            while (resultset.next()) {
                User user = new User(resultset.getString(2), resultset.getString(3), resultset.getByte(4));
                user.setId(resultset.getLong(1));
                usersList.add(user);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return usersList;
    }

    public void cleanUsersTable() {
        String clean = "DELETE FROM UsersTable";
        try {
            statement.executeUpdate(clean);
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Таблицы с таким именем не существует");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
