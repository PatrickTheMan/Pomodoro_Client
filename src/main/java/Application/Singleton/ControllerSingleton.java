package Application.Singleton;

import Application.Controller;

public class ControllerSingleton {

    private static Controller controller;

    public ControllerSingleton(){}

    public static Controller getInstance(){
        // Create the instance if it hasn't been initiated
        if (controller==null){
            controller = new Controller();
        }
        // Return the instance
        return controller;
    }

}
