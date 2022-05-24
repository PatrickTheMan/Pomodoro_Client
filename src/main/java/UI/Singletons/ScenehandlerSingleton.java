package UI.Singletons;

import UI.Scenehandler;

public class ScenehandlerSingleton {

    private static Scenehandler instance;

    // Private so you can't "new" it
    private ScenehandlerSingleton(){}

    /**
     * Gets the instance, it is synchronized because in teori it can be used by multiple threads
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
