package com.pao.project.fooddeliveryplatform.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private String url;
    private String user;
    private String pass;

    private DatabaseConnection() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("resources/db.properties"));
            this.url = props.getProperty("db.url");
            this.user = props.getProperty("db.user");
            this.pass = props.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Nu s-a putut citi db.properties");
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
