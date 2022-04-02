package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT NOT NULL AUTO_INCREMENT," +
                " name VARCHAR(255)," +
                " lastName VARCHAR(255)," +
                " age TINYINT," +
                " PRIMARY KEY(id));";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE IF EXISTS users;";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        String SQL = "INSERT INTO users (name, lastName, age) VALUES ('" +
                user.getName() + "', '" +
                user.getLastName() + "', " +
                user.getAge() + ");";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(SQL)){
            preparedStatement.execute();
            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String SQL = "DELETE from users where id=" + id + ";";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(SQL)){
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();


        String SQL = "SELECT * from users;";
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL);


            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String SQL = "DELETE from users;";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(SQL)) {
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
