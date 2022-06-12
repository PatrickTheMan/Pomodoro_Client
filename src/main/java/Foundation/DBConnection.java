package Foundation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Patrick G. Schemel
 */
public class DBConnection {

    //region [Variables]

    private Connection con;

    //endregion

    //region [Constructor]

    public Connection getConnection(){

        if (this.con == null){
            connect();
            //connectTestServer();
        }

        return this.con;
    }

    //endregion

    //region [Methods]

    /**
     * <Strong>Establishes the connection to the database of the pomodoro programs</Strong>
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
     * <Strong>Establishes the connection to the database of the test pomodoro programs</Strong>
     */
    public void connectTestServer() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://vps-837bc3ec.vps.ovh.net:1433;databaseName=TOMATOSOUPclone", "SA", "Datamatiker-2021");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    //endregion

    //region [Testing Methods]

    /**
     * <Strong>Checks if there is a connection</Strong>
     * @return true if there is a connection
     */
    public boolean isConnected() {
        if (con != null) {
            return true;
        } else {
            return false;
        }
    }

    //endregion

}
