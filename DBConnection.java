import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    static final String URL = "jdbc:mysql://localhost:3306/billing_db";
    static final String USER = "root";
    static final String PASS = "My@2004";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}