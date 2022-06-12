package Testing.Singletons;

import Testing.Testing;

/**
 * @author Patrick G. Schemel
 */
public class TestingSingleton {

    private static Testing instance;

    // Private so you can't "new" it
    private TestingSingleton(){}

    /**
     * <Strong>Gets the instance, it is synchronized because in teori it can be used by multiple threads</Strong>
     * @return the instance
     */
    public synchronized static Testing getInstance(){
        // Create the instance if it hasn't been initiated
        if (instance==null){
            instance = new Testing();
        }
        // Return the instance
        return instance;
    }

}
