package UI.Buttons.Menu;

import UI.Buttons.CustomButton;
import UI.Enums.ButtonType;

public class CustomMenuButton extends CustomButton {

    private String buttonText;

    public String getButtonText() {
        return buttonText;
    }

    public CustomMenuButton(ButtonType buttonType){

        // Run the super-classes constructor
        super(buttonType);

        // Make this button use the custom-menu-button css styling
        this.getStyleClass().add("custom-menu-button");

        // Give the button a behind the icon text
        switch (buttonType){
            case Home -> buttonText = "Home";
            case Overview -> buttonText = "Overview";
            case DoToday -> buttonText = "Do-Today";
        }
    }

}
