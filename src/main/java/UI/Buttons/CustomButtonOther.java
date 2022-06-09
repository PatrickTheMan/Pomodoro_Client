package UI.Buttons;

import Application.Singleton.ControllerSingleton;

public class CustomButtonOther extends CustomButton {

    protected CustomButtonOther(){}

    public CustomButtonOther Add(){
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

        return this;
    }

    public CustomButtonOther Minus(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });

        // Set the text for the hover text
        this.buttonText = "Remove";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-remove");

        return this;
    }

    public CustomButtonOther Edit(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });

        // Set the text for the hover text
        this.buttonText = "Edit";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-edit");

        return this;
    }

    public CustomButtonOther Accept(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });

        // Set the text for the hover text
        this.buttonText = "Accept";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-accept");

        return this;
    }

    public CustomButtonOther Delete(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });

        // Set the text for the hover text
        this.buttonText = "Delete";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-delete");

        return this;
    }

    public CustomButtonOther Forward(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });

        // Set the text for the hover text
        this.buttonText = "Forward";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-forward");

        return this;
    }

    public CustomButtonOther Back(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });

        // Set the text for the hover text
        this.buttonText = "Back";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-back");

        return this;
    }



}
