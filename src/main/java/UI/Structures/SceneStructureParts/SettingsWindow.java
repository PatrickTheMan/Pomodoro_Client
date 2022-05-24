package UI.Structures.SceneStructureParts;

import Domain.Consultant;
import Domain.Singletons.ConsultantSingleton;
import UI.Buttons.CustomButton;
import UI.Buttons.CustomButtonOther;
import UI.Structures.SceneStructureParts.SmallParts.ButtonBarH;
import UI.Structures.SceneStructureParts.SmallParts.ChoiceComboBox;
import UI.Structures.SceneStructureParts.SmallParts.ChoiceTextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SettingsWindow extends VBox {

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
     * @param height
     */
    public SettingsWindow(int height){
        // Normal setup
        normalSetup();

        // Set preferred size
        this.setPrefHeight(height);
        this.setMinHeight(height);
        this.setMaxHeight(height);
    }

    /**
     *
     * @param width
     * @param height
     */
    public SettingsWindow(int width, int height){
        // Normal setup
        normalSetup();

        // Set preferred size
        this.setPrefSize(width,height);
        this.setMinSize(width,height);
        this.setMaxSize(width,height);
    }

    /**
     *
     */
    private void normalSetup(){

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("window-settings");

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
