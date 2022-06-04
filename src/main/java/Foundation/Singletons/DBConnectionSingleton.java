package Foundation.Singletons;

import Foundation.DBConnection;

public class DBConnectionSingleton {

    private static DBConnection instance;

    // Private so you can't "new" it
    private DBConnectionSingleton(){}

    /**
     * Gets the instance, it is synchronized because in teori it can be used by multiple threads
     * @return the instance
     */
    public synchronized static DBConnection getInstance(){
        // Create the instance if it hasn't been initiated, set the standard values
        if (instance==null){
            instance = new DBConnection();
        }
        // Return the instance
        return instance;
    }

}
