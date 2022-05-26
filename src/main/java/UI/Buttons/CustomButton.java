package UI.Buttons;

import Application.Singleton.ControllerSingleton;
import javafx.scene.control.Button;

public class CustomButton extends Button {

    protected String buttonText;

    public String getButtonText() {
        return buttonText;
    }

    public CustomButton(){
        // Make this button use the custom-menu-button css styling
        this.getStyleClass().add("custom-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });
    }

    public CustomButtonMenu Menu(){
        return new CustomButtonMenu();
    }

    public CustomButtonOther Other(){
        return new CustomButtonOther();
    }

}
