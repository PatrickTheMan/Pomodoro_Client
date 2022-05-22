package UI.Singletons;

import UI.Scenehandler;

public class ScenehandlerSingleton {

    private static Scenehandler scenehandler;

    public ScenehandlerSingleton(){}

    public static Scenehandler getInstance(){
        // Create the instance if it hasn't been initiated
        if (scenehandler==null){
            scenehandler = new Scenehandler();
        }
        // Return the instance
        return scenehandler;
    }

}
