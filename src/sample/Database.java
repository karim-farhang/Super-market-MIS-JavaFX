package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection INSTANCE = null;

    private Database() {
    }

    public synchronized static Connection getInstance() throws SQLException, ClassNotFoundException {
        if (INSTANCE == null) {

             Class.forName("org.sqlite.JDBC");
            INSTANCE = DriverManager.getConnection("JDBC:SQLITE:D:/java_project/Safdar_Market - Copy/Database/Data_center - Copy.db");

           // INSTANCE = DriverManager.getConnection("JDBC:MYSQL://localhost/super_market", "root", "");
        }
        return INSTANCE;
    }
}