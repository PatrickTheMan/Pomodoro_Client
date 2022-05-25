package UI.Structures.SceneStructureParts.Windows;

import Domain.Consultant;
import Domain.Singletons.ConsultantSingleton;
import UI.Buttons.CustomButton;
import UI.Structures.SceneStructureParts.SmallParts.ButtonBarH;
import UI.Structures.SceneStructureParts.SmallParts.ChoiceComboBox;
import UI.Structures.SceneStructureParts.SmallParts.ChoiceTextField;
import UI.Structures.SceneStructureParts.CustomWindow;

import java.util.ArrayList;

public class SettingsWindow extends CustomWindow {

    private ArrayList<Consultant> consultants = new ArrayList<>();

    /**
     *
     */
    public SettingsWindow(){
        // Normal setup
        normalSetup();
    }

    /**
     *
     */
    private void normalSetup(){

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("custom-window-settings");

        // Get The different consultants
        ConsultantSingleton.getInstance().loadTestConsultants();
        // DB FUNCTION HERE

        // Convert to names only
        ArrayList<String> consultantNames = new ArrayList<>();
        for (Consultant c: this.consultants) {
            consultantNames.add(c.getFirstName()+" "+c.getLastName());
        }

        // Add the combobox with the consultantlist
        ChoiceComboBox concultantChoice = new ChoiceComboBox("Consultant: ",consultantNames);

        // Add the other textFields
        ChoiceTextField taskTimeField = new ChoiceTextField("Task time: ");
        ChoiceTextField breakTimeField = new ChoiceTextField("Break time: ");
        ChoiceTextField longbreakTimeField = new ChoiceTextField("Long break time: ");

        // Add the save button
        CustomButton customButton = new CustomButton().other().save();
        ButtonBarH buttonBarH = new ButtonBarH(customButton);

        // Add the content to the class object
        this.getChildren().addAll(concultantChoice,taskTimeField,breakTimeField,longbreakTimeField,buttonBarH);

    }

}
