package ru.kpfu.itis.servlets.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by vladislav on 15.05.14.
 */
public class DBConnect {

    private volatile static Connection connection = null;

    static final String JDBC_DRIVER="org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/justfortest";
    static final String USER = "postgres";
    static final String PASS = "12345";

    public static Connection getConnection(){
        if (connection == null){
            synchronized (DBConnect.class) {
                if (connection == null) {
                    try {
                        Class.forName(JDBC_DRIVER);

                        connection = DriverManager.getConnection(DB_URL, USER, PASS);

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }
}
