package Foundation.Singletons;

import Foundation.InformationContainer;

public class InformationContainerSingleton {

    private static InformationContainer instance;

    // Private so you can't "new" it
    private InformationContainerSingleton(){}

    /**
     * Gets the instance, it is synchronized because in teori it can be used by multiple threads
     * @return the instance
     */
    public synchronized static InformationContainer getInstance(){
        // Create the instance if it hasn't been initiated
        if (instance==null){
            instance = new InformationContainer();
        }
        // Return the instance
        return instance;
    }

}
