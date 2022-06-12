package Application.Singleton;

import Application.Controller;

/**
 * @author Patrick G. Schemel
 */
public class ControllerSingleton {

    private static Controller instance;

    // Private so you can't "new" it
    private ControllerSingleton(){}

    /**
     * <Strong>Gets the instance, it is synchronized because in teori it can be used by multiple threads</Strong>
     * @return the instance
     */
    public synchronized static Controller getInstance(){
        // Create the instance if it hasn't been initiated
        if (instance==null){
            instance = new Controller();
        }
        // Return the instance
        return instance;
    }

}
