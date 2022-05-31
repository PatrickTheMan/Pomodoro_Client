package UI.Structures.SceneStructureParts.Windows;

import Domain.Singletons.TimerSingleton;
import UI.Buttons.CustomButton;
import UI.Enums.MyPos;
import UI.Enums.MyShape;
import UI.Structures.SceneStructureParts.CustomWindow;
import UI.Structures.SceneStructureParts.SmallParts.Headline;
import UI.Structures.SceneStructureParts.SmallParts.NodeBarH;
import javafx.scene.Node;

import java.util.ArrayList;

public class PomodoroTimerWindow extends CustomWindow {

    /**
     *
     */
    public PomodoroTimerWindow(){
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
        Headline timerLabel = new Headline("", MyPos.CENTER, MyShape.ROUND,25,75);
        timerLabel.getLabel().textProperty().bind(TimerSingleton.getInstance().timeProperty());

        // Make this object use the custom css styling
        timerLabel.getStyleClass().add("headline-timer");

        // Add the buttons
        CustomButton buttonStop = new CustomButton().Controls().Stop();
        CustomButton buttonPausePlay = new CustomButton().Controls().PlayAndPause();
        CustomButton buttonSkip = new CustomButton().Controls().Skip();

        // Add the button to a bar
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(buttonStop);
        nodes.add(buttonPausePlay);
        nodes.add(buttonSkip);
        NodeBarH buttonBarH = new NodeBarH(nodes, MyPos.CENTER);

        // Add the content to the class object
        this.getChildren().addAll(timerLabel,buttonBarH);

    }

}
