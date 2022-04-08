package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Tom", "Sullivan", (byte) 34);
        userService.saveUser("Bob", "Williams", (byte) 51);
        userService.saveUser("Jack", "Peterson", (byte) 20);
        userService.saveUser("Richard", "Harrison", (byte) 25);
//        userService.removeUserById(3);

        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();



    }
}
