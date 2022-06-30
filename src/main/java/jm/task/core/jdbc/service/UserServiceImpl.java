package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao hib = new UserDaoHibernateImpl();

    public void createUsersTable() {
        hib.createUsersTable();
    }

    public void dropUsersTable() {
        hib.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        hib.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        hib.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return hib.getAllUsers();
    }

    public void cleanUsersTable() {
        hib.cleanUsersTable();
    }

}
