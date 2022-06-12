package UI.Buttons;

import Application.Singleton.ControllerSingleton;
import Domain.Singletons.TimerSingleton;

import java.util.ArrayList;

/**
 * @author Patrick G. Schemel
 */
public class CustomButtonControls extends CustomButton {

    //region [Variables]

    private static ArrayList<CustomButtonControls> allPlayAndPauseButtons = new ArrayList<>();

    //endregion

    //region [Constructor]

    protected CustomButtonControls(){}

    //endregion

    //region [Buttons]

    /**
     * <Strong>Use the PlayAndPause button setup</Strong>
     * @return the button
     */
    public CustomButton playAndPause() {

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

            // Remove focus
            this.setFocused(false);

            // Play - Pause
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

    /**
     * <Strong>Use the Stop button setup</Strong>
     * @return the button
     */
    public CustomButton stop() {

        // Get the normal setup
        NormalSetup();

        // Set the text for the hover text
        this.buttonText = "Stop";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-controls-button-stop");

        // Give the button the controls
        this.setOnAction(e -> {

            // Remove focus
            this.setFocused(false);

            // Stop
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

    /**
     * <Strong>Use the Skip button setup</Strong>
     * @return the button
     */
    public CustomButton skip() {

        // Get the normal setup
        NormalSetup();

        // Set the text for the hover text
        this.buttonText = "Skip";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-controls-button-skip");

        // Give the button the controls
        this.setOnAction(e -> {
            // Remove focus
            this.setFocused(false);
            // Skip
            ControllerSingleton.getInstance().skip();
        });

        return this;
    }

    /**
     * <Strong>Use the Popout button setup</Strong>
     * @return the button
     */
    public CustomButton popout() {

        // Get the normal setup
        NormalSetup();

        // Set the text for the hover text
        this.buttonText = "Popout";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-controls-button-popout");

        // Give the button the controls
        this.setOnAction(e -> {

            // Remove focus
            this.setFocused(false);

            // Popout
            ControllerSingleton.getInstance().openCloseMiniStage();

            // Make this button use the custom-button marked css styling
            if (this.getStyleClass().contains("custom-controls-button-popout-marked")){
                this.getStyleClass().remove("custom-controls-button-popout-marked");
            } else {
                this.getStyleClass().add("custom-controls-button-popout-marked");
            }
        });

        return this;
    }

    /**
     * <Strong>Use the Sound button setup</Strong>
     * @return the button
     */
    public CustomButton sound() {

        // Get the normal setup
        NormalSetup();

        // Set the text for the hover text
        this.buttonText = "Sound";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-controls-button-sound");

        // Give the button the controls
        this.setOnAction(e -> {

            // Sound turned on or off
            ControllerSingleton.getInstance().soundOnOff();

            // Remove focus
            this.setFocused(false);

            // Make this button use the custom-button marked css styling
            if (this.getStyleClass().contains("custom-controls-button-sound-marked")){
                this.getStyleClass().remove("custom-controls-button-sound-marked");
            } else {
                this.getStyleClass().add("custom-controls-button-sound-marked");
            }
        });

        return this;
    }

    //endregion

    //region [Normal Setup]

    /**
     * <Strong>The standard setup of control buttons</Strong>
     */
    private void NormalSetup(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-controls-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });
    }

    //endregion

}
