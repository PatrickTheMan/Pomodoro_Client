package Foundation.Singletons;

import Foundation.DBConnect;

public class DBConnectSingleton {

    private static DBConnect instance;

    // Private so you can't "new" it
    private DBConnectSingleton(){}

    /**
     * Gets the instance, it is synchronized because in teori it can be used by multiple threads
     * @return the instance
     */
    public synchronized static DBConnect getInstance(){
        // Create the instance if it hasn't been initiated
        if (instance==null){
            instance = new DBConnect();
        }
        // Return the instance
        return instance;
    }

}
