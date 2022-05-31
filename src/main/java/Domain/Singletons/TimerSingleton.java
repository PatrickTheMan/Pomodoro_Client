package Domain.Singletons;

import Domain.Timer;

public class TimerSingleton {

    private static Timer instance;

    // Private so you can't "new" it
    private TimerSingleton(){}

    /**
     * Gets the instance, it is synchronized because in teori it can be used by multiple threads
     * @return the instance
     */
    public synchronized static Timer getInstance(){
        // Create the instance if it hasn't been initiated
        if (instance==null){
            instance = new Timer();
        }
        // Return the instance
        return instance;
    }

}
