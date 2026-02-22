package hotel.management.system;

import java.sql.*;

public class conn {
    Connection c;
    Statement s;
    Object conn;

    public conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Use the latest MySQL driver
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagementsystem", "root", "pralhad@24");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add a method to return the connection
    public Connection getConnection() {
        return c;
    }
}
