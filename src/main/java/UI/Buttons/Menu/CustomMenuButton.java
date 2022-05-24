package UI.Buttons.Menu;

import Application.Singleton.ControllerSingleton;
import UI.Buttons.CustomButton;

public class CustomMenuButton extends CustomButton {

    private String buttonText;

    public String getButtonText() {
        return buttonText;
    }

    public CustomMenuButton(){
        // Make this button use the custom-menu-button css styling
        this.getStyleClass().add("custom-menu-button");
    }

    public CustomMenuButton CustomButton_Home() {

        // Set the text for the hover text
        this.buttonText = "Home";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-menu-button-home");

        // Give the button the controls
        this.setOnAction(e -> {
            ControllerSingleton.getInstance().setSceneHome();
        });

        return this;
    }


    public CustomMenuButton CustomButton_Overview() {

        // Set the text for the hover text
        this.buttonText = "Overview";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-menu-button-overview");

        // Give the button the controls
        this.setOnAction(e -> {
            ControllerSingleton.getInstance().setSceneOverview();
        });

        return this;
    }

    public CustomMenuButton CustomButton_DoToday() {

        // Set the text for the hover text
        this.buttonText = "Do-Today";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-menu-button-dotoday");

        // Give the button the controls
        this.setOnAction(e -> {
            ControllerSingleton.getInstance().setSceneDoToday();
        });

        return this;
    }

}
