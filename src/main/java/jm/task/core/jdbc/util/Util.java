package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "armenchik";
    private static final String PASSWORD = "g3r7x2333";

    private static Connection connection;

    static {
        try {
//            DriverManager.registerDriver(new Driver());
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }


    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(AvailableSettings.URL, "jdbc:mysql://localhost:3306/mydb");
                settings.put(AvailableSettings.USER, "armenchik");
                settings.put(AvailableSettings.PASS, "g3r7x2333");
                settings.put(AvailableSettings.SHOW_SQL, "true");
                settings.put(AvailableSettings.FORMAT_SQL, "true");

//                settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//                settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
//                settings.put(AvailableSettings.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());

                sessionFactory = configuration.buildSessionFactory(ssrb.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


}
