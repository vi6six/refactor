package com.refactor.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {

    /**
     * TODO Should be replaced with normal Connection pool lib aka Apache DBCP or SpringTemplate
     */
    public static Connection getConnection() {
        Properties properties = new Properties();
        Connection connection = null;

        try (FileInputStream inStream = new FileInputStream("db.properties")) {
            properties.load(inStream);

            // todo can be skipped for new drivers
            Class.forName(properties.getProperty("DB_DRIVER"));

            connection = DriverManager.getConnection(
                    properties.getProperty("DB_URL"),
                    properties.getProperty("DB_USERNAME"),
                    properties.getProperty("DB_PASSWORD"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
