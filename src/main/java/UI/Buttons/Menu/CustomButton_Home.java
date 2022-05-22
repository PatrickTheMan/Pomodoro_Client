package UI.Buttons.Menu;

import Application.Singleton.ControllerSingleton;
import UI.Enums.ButtonType;

public class CustomButton_Home extends CustomMenuButton {
    public CustomButton_Home() {
        super(ButtonType.Home);
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-menu-button-home");

        // Give the button the controls
        this.setOnAction(e -> {
            ControllerSingleton.getInstance().setSceneHome();
        });
    }
}
