package com.homedeve.tenis.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceProvider {

    private static Connection singleDataSource;

    public static Connection getSingleDataSourceInstance() {
        Connection conn = null;
        if (singleDataSource==null) {

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?serverTimezone=GMT","homedeve","root");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

}
