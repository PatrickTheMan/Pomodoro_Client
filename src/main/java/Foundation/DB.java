package Foundation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private Connection con;

    /**
     *
     */
    public DB(){
        connect();
    }

    /**
     * establishes the connection to the database of the pomodoro programs
     */
    public void connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=AUTOCAMPER", "Patrick", "123456");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Checks if there is a connection
     * @return true if there is a connection
     */
    public boolean isConnected() {
        if (con != null) {
            return true;
        } else {
            return false;
        }
    }

}
