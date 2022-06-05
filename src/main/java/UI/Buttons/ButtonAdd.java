package UI.Buttons;

import Application.Singleton.ControllerSingleton;

public class ButtonAdd extends CustomButtonOther {

    protected ButtonAdd(){

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });

        // Set the text for the hover text
        this.buttonText = "Add";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-add");
    }

}
