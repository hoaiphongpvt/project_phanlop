package DAL;
import java.sql.*;

public class Conn {
    private static Connection c;
    static Statement s;
    
    private static String host, port, dbName, dbUser, dbPassword;

    Conn () {
        host = "localhost";
        port = "3306";
        dbUser = "root";
        dbName = "school";
        dbPassword = "";
        String dbPath = "jdbc:mysql://" + host + ":" + port + "/"
                + dbName + "?useUnicode=yes&characterEncoding=UTF-8";
        try {
            c = (Connection) DriverManager.getConnection(dbPath, dbUser, dbPassword);
            s = c.createStatement();
            
            System.out.println("Connected");
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
