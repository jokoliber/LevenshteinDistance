
package levenshtein.connection;

/**
 *
 * @author Joko Banjarnahor
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Database_Connection 
{
    private static Connection connection = null;
    
    public static Connection getConnection()
    {    
        if(connection == null)
        {
            try
            {
                String url = "jdbc:mysql://localhost/ta";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, "root", "");
                System.out.println("Connection successful.................");
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Connection failed");
                System.out.println("Connection failed\n" + e);
            }
        }
            return connection;
    }
}
