package UI.Structures.SceneStructureParts.Windows;

import Application.Singleton.ControllerSingleton;
import Domain.Consultant;
import Domain.Singletons.ConsultantSingleton;
import Domain.Singletons.TimerSingleton;
import Foundation.Singletons.InformationContainerSingleton;
import UI.Buttons.CustomButton;
import UI.Structures.SceneStructureParts.SmallParts.NodeBarH;
import UI.Structures.SceneStructureParts.SmallParts.ChoiceComboBox;
import UI.Structures.SceneStructureParts.SmallParts.ChoiceTextField;
import UI.Structures.SceneStructureParts.CustomWindow;

import java.sql.Time;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * @author Patrick G. Schemel
 */
public class SettingsWindow extends CustomWindow {

    //region [Variables]

    private ChoiceComboBox consultantChoice;
    private ChoiceTextField taskTimeField;
    private ChoiceTextField breakTimeField;
    private ChoiceTextField longbreakTimeField;

    //endregion

    //region [Normal Getters & Setters]

    public void setTaskTimeFieldText(String text) {
        this.taskTimeField.getTextField().setText(text);
    }

    public void setBreakTimeFieldText(String text) {
        this.breakTimeField.getTextField().setText(text);
    }

    public void setLongbreakTimeFieldText(String text) {
        this.longbreakTimeField.getTextField().setText(text);
    }

    //endregion

    //region [Constructor]

    public SettingsWindow(){
        // Normal setup
        normalSetup();
    }

    //endregion

    //region [Normal Setup]

    /**
     * <Strong>The normal setup for the settingWindow, contains 3 choiceTextField, a choiceComboBox, a headline and a nodeBarH</Strong>
     */
    private void normalSetup(){

        // Make this object use the custom css styling
        this.getStyleClass().add("custom-window-settings");

        // Create an arraylist with consultants
        ArrayList<Consultant> consultants = new ArrayList<>();

        // Get The different consultants' names
        consultants.addAll(InformationContainerSingleton.getInstance().getConsultants());

        // Convert to names only, only the active ones
        ArrayList<String> consultantNames = new ArrayList<>();
        for (Consultant c: consultants) {
            if (c.isActive()){
                consultantNames.add(c.getName());
            }
        }

        // Add the combobox with the consultantlist, it changes the different values based on the consultant
        this.consultantChoice = new ChoiceComboBox("Consultant: ");
        this.consultantChoice.setContent(consultantNames);
        this.consultantChoice.setScaling(true);
        this.consultantChoice.getChoicebox().setOnAction(actionEvent -> {
            if (this.consultantChoice.getChoicebox().getValue() != null && !this.consultantChoice.getChoicebox().getValue().toString().equals("")){
                for (Consultant c: consultants) {
                    if (this.consultantChoice.getChoicebox().getValue().equals(c.getName())){
                        ControllerSingleton.getInstance().getConsultantValues(c,this);
                    }
                }
            }
        });

        // Add the other textFields
        this.taskTimeField = new ChoiceTextField("Task time: ");
        this.taskTimeField.setScaling(true);
        this.breakTimeField = new ChoiceTextField("Break time: ");
        this.breakTimeField.setScaling(true);
        this.longbreakTimeField = new ChoiceTextField("Long break time: ");
        this.longbreakTimeField.setScaling(true);


        Pattern timePattern = Pattern.compile("^(\\d?\\d):(\\d?\\d):(\\d?\\d)$");

        // Add the save button
        CustomButton saveButton = new CustomButton().other().accept();
        saveButton.setOnAction(e -> {
            // Saves the consultants settings

            boolean error=false;

            // Validation
            Matcher matcher = timePattern.matcher(this.taskTimeField.getTextField().getText());
            if (!matcher.find()){
                if (!this.taskTimeField.getStyleClass().contains("choice-textfield-error")){
                    this.taskTimeField.getStyleClass().add("choice-textfield-error");
                }
                error=true;
            } else {
                this.taskTimeField.getStyleClass().remove("choice-textfield-error");
            }
            matcher = timePattern.matcher(this.breakTimeField.getTextField().getText());
            if (!matcher.find()){
                if (!this.breakTimeField.getStyleClass().contains("choice-textfield-error")){
                    this.breakTimeField.getStyleClass().add("choice-textfield-error");
                }
                error=true;
            } else {
                this.taskTimeField.getStyleClass().remove("choice-textfield-error");
            }
            matcher = timePattern.matcher(this.longbreakTimeField.getTextField().getText());
            if (!matcher.find()){
                if (!this.longbreakTimeField.getStyleClass().contains("choice-textfield-error")){
                    this.longbreakTimeField.getStyleClass().add("choice-textfield-error");
                }
                error=true;
            } else {
                this.longbreakTimeField.getStyleClass().remove("choice-textfield-error");
            }


            if (!error){
                // Set the times from the settings window and the consultant (Also in DB)
                ControllerSingleton.getInstance().setTimes(
                        this.consultantChoice,
                        Time.valueOf(this.taskTimeField.getTextField().getText()),
                        Time.valueOf(this.breakTimeField.getTextField().getText()),
                        Time.valueOf(this.longbreakTimeField.getTextField().getText())
                );

                // Reset timer
                ControllerSingleton.getInstance().setTimeOnTimer(Time.valueOf(this.taskTimeField.getTextField().getText()));

                if (this.consultantChoice.getChoicebox().getValue() != null && !this.consultantChoice.getChoicebox().getValue().toString().equals("")){
                    for (Consultant c: consultants) {
                        if (this.consultantChoice.getChoicebox().getValue().equals(c.getName())){
                            ControllerSingleton.getInstance().setConsultantTitle(c);
                        }
                    }
                }
            }

            // Clear and create all pomodoros
            if (ConsultantSingleton.getInstance().exists() && InformationContainerSingleton.getInstance().getDoTodayList().size()>0){

                InformationContainerSingleton.getInstance().clearDoTodayList();

                if (InformationContainerSingleton.getInstance().getAmountOfActivePomodoros()>0){
                    InformationContainerSingleton.getInstance().clearAndRemakePomodoros();
                }

                TimerSingleton.getInstance().setTimerTypeTaskWithTaskName();
            }

            // Remove focus
            this.setFocused(false);

        });

        // Add the button to a bar
        NodeBarH buttonBarH = new NodeBarH(saveButton);

        // Add the content to the class object
        this.getChildren().addAll(
                this.consultantChoice,
                this.taskTimeField,
                this.breakTimeField,
                this.longbreakTimeField,
                buttonBarH);

        // Set the initial values and consultant if chosen
        if (ConsultantSingleton.getInstance().exists()){
            consultantChoice.getChoicebox().setValue(ConsultantSingleton.getInstance().getName());
        }
        ControllerSingleton.getInstance().getConsultantValues(ConsultantSingleton.getInstance(),this);

        // Lock the fields when the timer runs
        this.consultantChoice.disableProperty().bind(TimerSingleton.getInstance().timeRunningProperty());
        this.taskTimeField.disableProperty().bind(TimerSingleton.getInstance().timeRunningProperty());
        this.breakTimeField.disableProperty().bind(TimerSingleton.getInstance().timeRunningProperty());
        this.longbreakTimeField.disableProperty().bind(TimerSingleton.getInstance().timeRunningProperty());
        saveButton.disableProperty().bind(TimerSingleton.getInstance().timeRunningProperty());

    }

    //endregion

}
