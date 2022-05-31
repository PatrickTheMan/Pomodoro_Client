package UI.Structures.SceneStructureParts.Windows;

import Domain.Singletons.TimerSingleton;
import UI.Buttons.CustomButton;
import UI.Buttons.CustomButtonControls;
import UI.Enums.MyPos;
import UI.Enums.MyShape;
import UI.Structures.SceneStructureParts.CustomWindow;
import UI.Structures.SceneStructureParts.SmallParts.Headline;
import UI.Structures.SceneStructureParts.SmallParts.NodeBarH;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

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

        // Make the stackpane for the button on top of timersection
        StackPane timerSection = new StackPane();
        timerSection.setAlignment(Pos.TOP_RIGHT);

        // Set the headline & bind the timerLabel to the timer
        Headline timerLabel = new Headline("", MyPos.CENTER, MyShape.ROUND,25,75);
        timerLabel.getLabel().textProperty().bind(TimerSingleton.getInstance().timeProperty());

        // Make this object use the custom css styling
        timerLabel.getStyleClass().add("headline-timer");

        // Add popout button
        CustomButton buttonPopout = new CustomButton().Controls().Popout();
        // Styling of button
        buttonPopout.setStyle("-fx-border-color: transparent; -fx-background-color: transparent");

        // Add content to the timerSection
        timerSection.getChildren().addAll(timerLabel,buttonPopout);


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
        this.getChildren().addAll(timerSection,buttonBarH);

    }

}
