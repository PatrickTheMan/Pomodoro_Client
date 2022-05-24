package UI.Buttons;

import Domain.Consultant;
import Domain.Task;

public class CustomButtonOther extends CustomButton {

    protected CustomButtonOther(){}

    public CustomButton add(Consultant consultant){
        // Get the setup
        normalAddSetup();
        // Give the button the controls
        this.setOnAction(e -> {

        });
        return this;
    }

    public CustomButton add(Task task){
        // Get the setup
        normalAddSetup();
        // Give the button the controls
        this.setOnAction(e -> {

        });
        return this;
    }

    private void normalAddSetup(){
        // Set the text for the hover text
        this.buttonText = "Add";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-menu-button-add");
    }

    public CustomButton remove(Consultant consultant){
        // Get the setup
        normalRemoveSetup();
        // Give the button the controls
        this.setOnAction(e -> {

        });
        return this;
    }

    public CustomButton remove(Task task){
        // Get the setup
        normalRemoveSetup();
        // Give the button the controls
        this.setOnAction(e -> {

        });
        return this;
    }

    private void normalRemoveSetup(){
        // Set the text for the hover text
        this.buttonText = "Remove";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-menu-button-remove");
    }

    public CustomButton save(){
        // Get the setup
        normalSaveSetup();
        // Give the button the controls
        this.setOnAction(e -> {

        });
        return this;
    }

    private void normalSaveSetup(){
        // Set the text for the hover text
        this.buttonText = "Save";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-menu-button-save");
    }

}
