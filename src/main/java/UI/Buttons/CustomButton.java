package UI.Buttons;

import UI.Enums.ButtonType;
import javafx.scene.control.Button;

public class CustomButton extends Button {

    public CustomButton(ButtonType buttonType){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-button");
    }

}
