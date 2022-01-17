package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl userdaoJDBC = new UserDaoJDBCImpl();
    public void createUsersTable() {
        userdaoJDBC.createUsersTable();
    }

    public void dropUsersTable() {
        userdaoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userdaoJDBC.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userdaoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userdaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        userdaoJDBC.cleanUsersTable();
    }
}
