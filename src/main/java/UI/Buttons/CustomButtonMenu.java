package UI.Buttons;

import Application.Singleton.ControllerSingleton;

public class CustomButtonMenu extends CustomButton {

    protected CustomButtonMenu(){}

    public CustomButton Home() {

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


    public CustomButton Overview() {

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

    public CustomButton DoToday() {

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
