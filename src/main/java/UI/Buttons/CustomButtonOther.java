package UI.Buttons;

import Application.Singleton.ControllerSingleton;
import Domain.Consultant;
import Domain.Singletons.ConsultantSingleton;
import Domain.Task;
import UI.Structures.SceneStructureParts.CustomWindow;
import UI.Structures.SceneStructureParts.SmallParts.ChoiceComboBox;
import UI.Structures.SceneStructureParts.Windows.SettingsWindow;

import java.util.ArrayList;

public class CustomButtonOther extends CustomButton {

    protected CustomButtonOther(){}

    public CustomButton Add(Consultant consultant){
        // Get the setup
        NormalAddSetup();
        // Give the button the controls
        this.setOnAction(e -> {

        });
        return this;
    }

    public CustomButton Add(Task task){
        // Get the setup
        NormalAddSetup();
        // Give the button the controls
        this.setOnAction(e -> {

        });
        return this;
    }

    private void NormalAddSetup(){

        // Get the normal setup
        NormalSetup();

        // Set the text for the hover text
        this.buttonText = "Add";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-add");
    }

    public CustomButton Remove(Consultant consultant){
        // Get the setup
        NormalRemoveSetup();
        // Give the button the controls
        this.setOnAction(e -> {

        });
        return this;
    }

    public CustomButton Remove(Task task){
        // Get the setup
        NormalRemoveSetup();
        // Give the button the controls
        this.setOnAction(e -> {

        });
        return this;
    }

    private void NormalRemoveSetup(){

        // Get the normal setup
        NormalSetup();

        // Set the text for the hover text
        this.buttonText = "Remove";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-remove");
    }

    public CustomButton Save(ChoiceComboBox consultantChoice, ArrayList<Consultant> consultants){
        // Get the setup
        NormalSaveSetup();
        // Saves the consultants settings
        this.setOnAction(e -> {
            if (consultantChoice.getChoicebox().getValue() != null && !consultantChoice.getChoicebox().getValue().toString().equals("")){
                for (Consultant c: consultants) {
                    if (consultantChoice.getChoicebox().getValue().equals(c.getFirstName()+" "+c.getLastName())){
                        ControllerSingleton.getInstance().setConsultant(c);
                    }
                }
            }
        });
        return this;
    }

    private void NormalSaveSetup(){

        // Get the normal setup
        NormalSetup();

        // Set the text for the hover text
        this.buttonText = "Save";

        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button-save");
    }







    private void NormalSetup(){
        // Make this button use the custom-button css styling
        this.getStyleClass().add("custom-other-button");
        // Set the selection function
        this.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });
    }

}
