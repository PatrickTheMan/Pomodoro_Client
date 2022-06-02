package UI.Buttons;

import Application.Singleton.ControllerSingleton;
import Domain.Consultant;
import Domain.Task;
import UI.Structures.SceneStructureParts.SmallParts.Taskline;

public class ButtonMinus extends CustomButtonOther{

    protected ButtonMinus(){

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
    }

    public ButtonMinus setToRemoveConsultant(Consultant consultant){
        this.setOnAction(e -> {

            // Remove focus
            this.setFocused(false);

        });
        return this;
    }

    public ButtonMinus setToRemoveTask(Task task){
        this.setOnAction(e -> {

            // Remove focus
            this.setFocused(false);

        });
        return this;
    }

    public ButtonMinus setToRemovePomodoro(Taskline taskline){
        this.setOnAction(e -> {

            // Remove focus
            this.setFocused(false);

        });
        return this;
    }

}
