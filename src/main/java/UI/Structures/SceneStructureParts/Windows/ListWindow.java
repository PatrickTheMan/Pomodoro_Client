package UI.Structures.SceneStructureParts.Windows;

import Domain.Singletons.TimerSingleton;
import UI.Enums.MyPos;
import UI.Enums.MyShape;
import UI.Structures.SceneStructureParts.CustomWindow;
import UI.Structures.SceneStructureParts.SmallParts.Headline;

public class ListWindow extends CustomWindow {

    /**
     *
     */
    public ListWindow(){
        // Normal setup
        NormalSetup();
    }


    /**
     *
     */
    private void NormalSetup(){

        // Make this object use the custom css styling
        this.getStyleClass().add("custom-window-pomodoro");


        // Set the headline & bind the timerLabel to the timer
        Headline timerLabel = new Headline("", MyPos.CENTER, MyShape.ROUND,25,75,10);
        timerLabel.getLabel().textProperty().bind(TimerSingleton.getInstance().timeProperty());






        // Add the content to the class object
        this.getChildren().addAll();

    }

}
