package UI.Buttons;

import Foundation.Singletons.ControllerSingleton;

/**
 * @author Patrick G. Schemel
 */
public class CustomButtonMenu extends CustomButton {

    //region [Constructor]

    protected CustomButtonMenu(){}

    //endregion

    //region [Buttons]

    /**
     * <Strong>Use the Home button setup</Strong>
     * @return the button
     */
    public CustomButton home() {

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

    /**
     * <Strong>Use the Overview button setup</Strong>
     * @return the button
     */
    public CustomButton overview() {

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

    /**
     * <Strong>Use the DoToday button setup</Strong>
     * @return the button
     */
    public CustomButton doToday() {

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

    //endregion

}
