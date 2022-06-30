package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl bd = new UserServiceImpl();

        bd.createUsersTable();

        bd.saveUser("Ivan", "Petrov", (byte) 35);
        bd.saveUser("Arina", "Smirnova", (byte) 29);
        bd.saveUser("Alexey", "Kotov", (byte) 59);
        bd.saveUser("Petr", "Ivanov", (byte) 41);

        for (User user : bd.getAllUsers()) {
            System.out.println(user.toString());
        }

        bd.cleanUsersTable();

        bd.dropUsersTable();
    }
}
