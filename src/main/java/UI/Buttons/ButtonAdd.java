package UI.Buttons;

import Application.Singleton.ControllerSingleton;
import Domain.Consultant;
import Domain.Task;
import UI.Structures.SceneStructureParts.SmallParts.Taskline;

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

    public ButtonAdd setToAddConsultant(Consultant consultant){
        this.setOnAction(e -> {

            // Remove focus
            this.setFocused(false);

        });
        return this;
    }

    public ButtonAdd setToAddTask(Task task){
        this.setOnAction(e -> {

            // Remove focus
            this.setFocused(false);

        });
        return this;
    }

    public ButtonAdd setToAddPomodoro(Taskline taskline){
        this.setOnAction(e -> {

            // Remove focus
            this.setFocused(false);

        });
        return this;
    }

}
