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
        // Set the selection & hovered function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });
        this.hoverProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setHovered(this,newVal);
        });
    }

    public CustomButton(String text){
        // Make this button use the custom-menu-button css styling
        this.getStyleClass().add("custom-button-text");

        // Set the text
        this.setText(text);

        // Set the selection & hovered function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });
        this.hoverProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setHovered(this,newVal);
        });
    }

    public CustomButtonMenu Menu(){
        return new CustomButtonMenu();
    }

    public CustomButtonControls Controls(){return new CustomButtonControls();}

    public CustomButtonOther Other(){
        return new CustomButtonOther();
    }

    public void setCustomFocused(boolean focused) {
        this.setFocused(focused);
    }

    public void setSize(int size){
        // Set the button size
        this.setStyle("-fx-background-size: "+size+";");
    }
}
