package Foundation.Singletons;

import UI.Scenehandler;

/**
 * @author Patrick G. Schemel
 */
public class ScenehandlerSingleton {

    private static Scenehandler instance;

    // Private so you can't "new" it
    private ScenehandlerSingleton(){}

    /**
     * <Strong>Gets the instance, it is synchronized because in teori it can be used by multiple threads</Strong>
     * @return the instance
     */
    public synchronized static Scenehandler getInstance(){
        // Create the instance if it hasn't been initiated
        if (instance==null){
            instance = new Scenehandler();
        }
        // Return the instance
        return instance;
    }

}
