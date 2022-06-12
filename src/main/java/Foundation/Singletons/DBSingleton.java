package Foundation.Singletons;

import Foundation.DB;

/**
 * @author Patrick G. Schemel
 */
public class DBSingleton {

    private static DB instance;

    // Private so you can't "new" it
    private DBSingleton(){}

    /**
     * <Strong>Gets the instance, it is synchronized because in teori it can be used by multiple threads</Strong>
     * @return the instance
     */
    public synchronized static DB getInstance(){
        // Create the instance if it hasn't been initiated
        if (instance==null){
            instance = new DB();
        }
        // Return the instance
        return instance;
    }

}
