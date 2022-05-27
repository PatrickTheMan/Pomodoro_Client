package UI.Structures.SceneStructureParts.Windows;

import Application.Singleton.ControllerSingleton;
import Domain.Consultant;
import Domain.Singletons.ConsultantSingleton;
import Domain.Task;
import UI.Buttons.CustomButton;
import UI.Structures.SceneStructureParts.CustomWindow;
import UI.Structures.SceneStructureParts.SmallParts.LabelWithSizing;
import UI.Structures.SceneStructureParts.SmallParts.NodeBarH;
import javafx.scene.Node;

import java.util.ArrayList;

public class PomodoroTimerWindow extends CustomWindow {

    private LabelWithSizing timerLabel;
    private LabelWithSizing statusLabel;

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

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("custom-window-pomodoro");

        // Create an arraylist with consultants
        ArrayList<Consultant> consultants = new ArrayList<>();

        // Get The different consultants
        consultants.addAll(ConsultantSingleton.getInstance().getTestConsultants());
        // DB FUNCTION HERE



        // Add the buttons
        CustomButton buttonStop = new CustomButton().Other().Add(new Task());
        CustomButton buttonPausePlay = new CustomButton().Other().Add(new Task());
        CustomButton buttonSkip = new CustomButton().Other().Add(new Task());

        // Add the button to a bar
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(buttonStop);
        nodes.add(buttonPausePlay);
        nodes.add(buttonSkip);
        NodeBarH buttonBarH = new NodeBarH(nodes);

        // Add the content to the class object
        this.getChildren().addAll(buttonBarH);

        // Set the initial values and consultant if chosen
        if (ConsultantSingleton.getInstance().exists()) {
            // Values
        }

    }

}
