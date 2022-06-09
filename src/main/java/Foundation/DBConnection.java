package Foundation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private Connection con;

    public Connection getConnection(){

        if (this.con == null){
            //connect();
            connectTestServer();
        }

        return this.con;
    }

    /**
     * establishes the connection to the database of the pomodoro programs
     */
    public void connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://vps-837bc3ec.vps.ovh.net:1433;databaseName=TOMATOSOUP", "SA", "Datamatiker-2021");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * establishes the connection to the database of the test pomodoro programs
     */
    public void connectTestServer() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://vps-837bc3ec.vps.ovh.net:1433;databaseName=TOMATOSOUPclone", "SA", "Datamatiker-2021");
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
