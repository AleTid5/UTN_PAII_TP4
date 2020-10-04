package com.example.UTN.src.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseManager {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String host   = "52.5.226.201"; // sql9.freesqldatabase.com
    private static final String port   = "3306";
    private static final String nameBD = "sql9368571";
    private static final String user   = "sql9368571";
    private static final String pass   = "KFT4dAXNlP";

    private static String buildConnection() {
        return String.format("jdbc:mysql://%s:%s/%s", host, port, nameBD);
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        return DriverManager.getConnection(buildConnection(), user, pass);
    }
}
