package UI.Buttons.Menu;

import UI.Buttons.CustomButton;
import UI.Enums.ButtonType;

public class CustomMenuButton extends CustomButton {

    public CustomMenuButton(ButtonType buttonType){

        // Run the super-classes constructor
        super(buttonType);

        // Make this button use the custom-menu-button css styling
        this.getStyleClass().add("custom-menu-button");

    }

}
