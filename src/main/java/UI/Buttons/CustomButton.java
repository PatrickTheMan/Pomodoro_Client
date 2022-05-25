package UI.Buttons;

import javafx.scene.control.Button;

public class CustomButton extends Button {

    protected String buttonText;

    public String getButtonText() {
        return buttonText;
    }

    public CustomButton(){
        // Make this button use the custom-menu-button css styling
        this.getStyleClass().add("custom-button");
    }

    public CustomButtonMenu Menu(){
        return new CustomButtonMenu();
    }

    public CustomButtonOther Other(){
        return new CustomButtonOther();
    }

}
