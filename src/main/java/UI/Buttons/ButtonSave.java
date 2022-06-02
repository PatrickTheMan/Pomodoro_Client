package UI.Buttons;

import Application.Singleton.ControllerSingleton;
import Domain.Consultant;
import Domain.Task;
import UI.Structures.SceneStructureParts.SmallParts.ChoiceComboBox;
import UI.Structures.SceneStructureParts.SmallParts.ChoiceTextField;

import java.sql.Time;
import java.util.ArrayList;

public class ButtonSave extends CustomButtonOther{

    protected ButtonSave(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });

        // Set the text for the hover text
        this.buttonText = "Save";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-save");
    }

    public ButtonSave setToSaveConsultant(ChoiceTextField taskTimeField, ChoiceTextField breakTimeField, ChoiceTextField longBreakTimeField, ChoiceComboBox consultantChoice, ArrayList<Consultant> consultants){
        // Saves the consultants settings
        this.setOnAction(e -> {

            // Remove focus
            this.setFocused(false);

            if (consultantChoice.getChoicebox().getValue() != null && !consultantChoice.getChoicebox().getValue().toString().equals("")){
                for (Consultant c: consultants) {
                    if (consultantChoice.getChoicebox().getValue().equals(c.getName())){
                        ControllerSingleton.getInstance().setConsultantTitle(c);
                    }
                }
            }
            // Set the times from the settings window
            ControllerSingleton.getInstance().setTimes(
                    Time.valueOf(taskTimeField.getTextField().getText()),
                    Time.valueOf(breakTimeField.getTextField().getText()),
                    Time.valueOf(longBreakTimeField.getTextField().getText())
            );

            // Reset timer
            ControllerSingleton.getInstance().setTimeOnTimer(Time.valueOf(taskTimeField.getTextField().getText()));
        });
        return this;
    }

    public ButtonSave setToSaveTask(Task task){
        this.setOnAction(e -> {

            // Remove focus
            this.setFocused(false);

        });
        return this;
    }

}
