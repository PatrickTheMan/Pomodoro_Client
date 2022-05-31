package UI.Buttons;

import Application.Singleton.ControllerSingleton;
import Domain.Singletons.TimerSingleton;
import UI.Singletons.ScenehandlerSingleton;

import java.util.ArrayList;

public class CustomButtonControls extends CustomButton {

    protected CustomButtonControls(){}


    private static ArrayList<CustomButtonControls> allPlayAndPauseButtons = new ArrayList<>();

    public CustomButton PlayAndPause() {

        // Get the normal setup
        NormalSetup();

        // Set the text for the hover text
        this.buttonText = "Play & Pause";

        // Make this button use the custom-button css styling
        if (TimerSingleton.getInstance().timeRunningProperty().getValue()){
            //this.getStyleClass().add("custom-controls-button-pause");
            this.getStyleClass().add("custom-controls-button-pause");
        } else {
            //this.getStyleClass().add("custom-controls-button-play");
            this.getStyleClass().add("custom-controls-button-play");
        }

        // Connects all playAndPause buttons
        allPlayAndPauseButtons.add(this);

        // Give the button the controls and styling change
        this.setOnAction(e -> {
            ControllerSingleton.getInstance().playOrPause(this);

            for (CustomButtonControls c:allPlayAndPauseButtons) {
                if (c.getStyleClass().contains("custom-controls-button-pause")){
                    c.getStyleClass().remove("custom-controls-button-pause");
                    c.getStyleClass().add("custom-controls-button-play");
                } else {
                    c.getStyleClass().remove("custom-controls-button-play");
                    c.getStyleClass().add("custom-controls-button-pause");
                }
            }

        });

        return this;
    }


    public CustomButton Stop() {

        // Get the normal setup
        NormalSetup();

        // Set the text for the hover text
        this.buttonText = "Stop";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-controls-button-stop");

        // Give the button the controls
        this.setOnAction(e -> {
            ControllerSingleton.getInstance().stop();

            // Reset the play buttons to play
            for (CustomButtonControls c:allPlayAndPauseButtons) {
                if (c.getStyleClass().contains("custom-controls-button-pause")){
                    c.getStyleClass().remove("custom-controls-button-pause");
                    c.getStyleClass().add("custom-controls-button-play");
                }
            }
        });

        return this;
    }

    public CustomButton Skip() {

        // Get the normal setup
        NormalSetup();

        // Set the text for the hover text
        this.buttonText = "Skip";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-controls-button-skip");

        // Give the button the controls
        this.setOnAction(e -> {
            ControllerSingleton.getInstance().skip();
        });

        return this;
    }

    public CustomButton Popout() {

        // Get the normal setup
        NormalSetup();

        // Set the text for the hover text
        this.buttonText = "Popout";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-controls-button-popout");

        // Give the button the controls
        this.setOnAction(e -> {
            if (ScenehandlerSingleton.getInstance().getMiniStage()==null || !ScenehandlerSingleton.getInstance().getMiniStage().isShowing() ){
                ControllerSingleton.getInstance().startMiniStage();
            } else {
                ControllerSingleton.getInstance().closeMiniStage();
            }
        });

        return this;
    }



    private void NormalSetup(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-controls-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });
    }

}
