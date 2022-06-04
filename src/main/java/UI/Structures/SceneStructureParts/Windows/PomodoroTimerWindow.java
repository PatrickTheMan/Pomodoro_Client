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
import javafx.scene.layout.VBox;

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

        // Add a timer and status container
        VBox timerStatusContainer = new VBox();

        // Make this object use the custom css styling
        timerStatusContainer.getStyleClass().add("headline-timerandstatus");

        // Set the headline & bind the timerLabel to the timer
        Headline timerLabel = new Headline("");
        timerLabel.setPos(MyPos.CENTER);
        timerLabel.setShape(MyShape.ROUND);
        timerLabel.setScaling(25,75,10);
        timerLabel.getLabel().textProperty().bind(TimerSingleton.getInstance().timeProperty());

        // Set the headline & bind the timerLabel to the timer
        Headline statusLabel = new Headline("");
        statusLabel.setPos(MyPos.CENTER);
        statusLabel.setShape(MyShape.ROUND);
        statusLabel.setScaling(15,40,18);
        statusLabel.getLabel().textProperty().bind(TimerSingleton.getInstance().timeTypeProperty());

        // Add content to the timer and status container
        timerStatusContainer.getChildren().addAll(timerLabel,statusLabel);


        // Add a button container
        VBox buttonContainer = new VBox();
        buttonContainer.getStyleClass().add("headline-buttoncontainer");
        buttonContainer.setAlignment(Pos.TOP_RIGHT);

        // Add popout button
        CustomButton buttonPopout = new CustomButton().Controls().Popout();
        // Styling of button
        buttonPopout.setStyle("-fx-border-color: transparent; -fx-background-color: transparent");

        // Add sound button
        CustomButton buttonSound = new CustomButton().Controls().Sound();
        // Styling of button
        buttonPopout.setStyle("-fx-border-color: transparent; -fx-background-color: transparent");

        // Add the buttons to the container
        buttonContainer.getChildren().addAll(buttonPopout,buttonSound);



        // Add content to the timerSection
        timerSection.getChildren().addAll(timerStatusContainer,buttonContainer);


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
