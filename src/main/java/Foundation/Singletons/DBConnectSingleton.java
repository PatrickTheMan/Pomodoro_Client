package Foundation.Singletons;

import Foundation.DBConnect;

public class DBConnectSingleton {

    private DBConnect dbConnect;

    public DBConnectSingleton(){}

    // Get the instance
    public DBConnect getInstance(){
        // Create the instance if it hasn't been initiated
        if (dbConnect==null){
            dbConnect = new DBConnect();
        }
        // Return the instance
        return dbConnect;
    }

}
