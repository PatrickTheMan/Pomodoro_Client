package UI.Structures.SceneStructureParts.Windows;

import Application.Singleton.ControllerSingleton;
import Domain.Consultant;
import Domain.Singletons.ConsultantSingleton;
import UI.Buttons.CustomButton;
import UI.Structures.SceneStructureParts.SmallParts.NodeBarH;
import UI.Structures.SceneStructureParts.SmallParts.ChoiceComboBox;
import UI.Structures.SceneStructureParts.SmallParts.ChoiceTextField;
import UI.Structures.SceneStructureParts.CustomWindow;

import java.util.ArrayList;

public class SettingsWindow extends CustomWindow {

    private ChoiceComboBox consultantChoice;
    private ChoiceTextField taskTimeField;
    private ChoiceTextField breakTimeField;
    private ChoiceTextField longbreakTimeField;

    public void setTaskTimeFieldText(String text) {
        this.taskTimeField.getTextField().setText(text);
    }

    public void setBreakTimeFieldText(String text) {
        this.breakTimeField.getTextField().setText(text);
    }

    public void setLongbreakTimeFieldText(String text) {
        this.longbreakTimeField.getTextField().setText(text);
    }

    /**
     *
     */
    public SettingsWindow(){
        // Normal setup
        NormalSetup();
    }


    /**
     *
     */
    private void NormalSetup(){

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("custom-window-settings");

        // Create an arraylist with consultants
        ArrayList<Consultant> consultants = new ArrayList<>();

        // Get The different consultants
        consultants.addAll(ConsultantSingleton.getInstance().getTestConsultants());
        // DB FUNCTION HERE

        // Convert to names only
        ArrayList<String> consultantNames = new ArrayList<>();
        for (Consultant c: consultants) {
            consultantNames.add(c.getFirstName()+" "+c.getLastName());
        }

        // Add the combobox with the consultantlist, it changes the different values based on the consultant
        consultantChoice = new ChoiceComboBox("Consultant: ",consultantNames);
        consultantChoice.getChoicebox().setOnAction(actionEvent -> {
            if (consultantChoice.getChoicebox().getValue() != null && !consultantChoice.getChoicebox().getValue().toString().equals("")){
                for (Consultant c: consultants) {
                    if (consultantChoice.getChoicebox().getValue().equals(c.getFullName())){
                        ControllerSingleton.getInstance().updateConsultantValues(c,this);
                    }
                }
            }
        });

        // Add the other textFields
        taskTimeField = new ChoiceTextField("Task time: ");
        breakTimeField = new ChoiceTextField("Break time: ");
        longbreakTimeField = new ChoiceTextField("Long break time: ");

        // Add the save button
        CustomButton customButton = new CustomButton().Other().Save(consultantChoice,consultants);

        // Add the button to a bar
        NodeBarH buttonBarH = new NodeBarH(customButton);

        // Add the content to the class object
        this.getChildren().addAll(consultantChoice,taskTimeField,breakTimeField,longbreakTimeField,buttonBarH);

        // Set the initial values and consultant if chosen
        if (ConsultantSingleton.getInstance().exists()){
            consultantChoice.getChoicebox().setValue(ConsultantSingleton.getInstance().getFullName());
        }
        ControllerSingleton.getInstance().updateConsultantValues(ConsultantSingleton.getInstance(),this);

    }

}
