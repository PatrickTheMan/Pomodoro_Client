package Application;

import Domain.Consultant;
import Domain.Singletons.ConsultantSingleton;
import Domain.Singletons.TimerSingleton;
import UI.Buttons.CustomButton;
import UI.Buttons.CustomButtonControls;
import UI.Buttons.CustomButtonOther;
import UI.Enums.SceneType;
import UI.Singletons.ScenehandlerSingleton;
import UI.Structures.SceneStructureParts.SmallParts.ChoiceComboBox;
import UI.Structures.SceneStructureParts.SmallParts.ChoiceTextField;
import UI.Structures.SceneStructureParts.Windows.SettingsWindow;

public class Controller {

    //region [Scene]

    /**
     *
     */
    public void setSceneHome(){
        ScenehandlerSingleton.getInstance().setStage(SceneType.Home);
    }

    /**
     *
     */
    public void setSceneOverview(){
        ScenehandlerSingleton.getInstance().setStage(SceneType.Overview);
    }

    /**
     *
     */
    public void setSceneDoToday(){
        ScenehandlerSingleton.getInstance().setStage(SceneType.DoToday);
    }

    /**
     *
     */
    public void openCloseMiniStage(){
        if (ScenehandlerSingleton.getInstance().getMiniStage()==null || !ScenehandlerSingleton.getInstance().getMiniStage().isShowing() ){
            ScenehandlerSingleton.getInstance().startMiniStage();
        } else {
            ScenehandlerSingleton.getInstance().closeMiniStage();
        }
    }

    //endregion

    //region [Consultant]

    private boolean consultantSetAlready=false;

    /**
     *
     * @param consultant
     */
    public void setConsultantTitle(Consultant consultant){

        // Change title so consultant is added
        ScenehandlerSingleton.getInstance().getStage().setTitle(
                (this.timerSetAlready ? TimerSingleton.getInstance().timeProperty().getValue() : "No user")+
                    consultant.getFullName()+
                        ScenehandlerSingleton.getInstance().getSceneTitle()
        );



        // Set the new consultant
        ConsultantSingleton.getInstance().setConsultant(consultant);

        // Update boolean
        this.consultantSetAlready = true;

        // Update the timer
        TimerSingleton.getInstance().resetTimer();

        // Update title timer
        this.setTimerTitle();
    }

    /**
     *
     * @param consultant
     * @param settingsWindow
     */
    public void updateConsultantValues(Consultant consultant, SettingsWindow settingsWindow){
        // Update fields
        settingsWindow.setTaskTimeFieldText(""+consultant.getTaskTimeMin()+":"+
                (consultant.getTaskTimeSec()>9? consultant.getTaskTimeSec() : "0"+consultant.getTaskTimeSec()));
        settingsWindow.setBreakTimeFieldText(""+consultant.getBreakTimeMin()+":"+
                (consultant.getBreakTimeSec()>9? consultant.getBreakTimeSec() : "0"+consultant.getBreakTimeSec()));
        settingsWindow.setLongbreakTimeFieldText(""+consultant.getLongBreakTimeMin()+":"+
                (consultant.getLongBreakTimeSec()>9? consultant.getLongBreakTimeSec() : "0"+consultant.getLongBreakTimeSec()));
    }

    //endregion

    //region [Timer]

    private boolean timerSetAlready=false;

    public void setTimerTitle(){

        // Change title so timer is added
        ScenehandlerSingleton.getInstance().getStage().setTitle(
                TimerSingleton.getInstance().timeProperty().getValue()+" - "+
                        (this.consultantSetAlready ? ConsultantSingleton.getInstance().getFullName()+" - " : "")+
                        ScenehandlerSingleton.getInstance().getSceneTitle()
        );

        // Update the boolean
        this.timerSetAlready = true;

    }

    public void setTimeOnTimer(){
        TimerSingleton.getInstance().setTime(
                ""+ConsultantSingleton.getInstance().getTaskTimeMin(),
                ""+ConsultantSingleton.getInstance().getTaskTimeSec()
        );
    }

    public void playOrPause(CustomButtonControls customButtonControls){
        if (customButtonControls.getStyleClass().contains("custom-controls-button-play")){
            TimerSingleton.getInstance().startTimer();
        } else {
            TimerSingleton.getInstance().pauseTimer();
        }
    }

    public void stop(){
        TimerSingleton.getInstance().resetTimer();
    }

    public void skip(){
        TimerSingleton.getInstance().skipTimer();
    }

    //endregion

    //region [Buttons]



    //endregion

    //region [Buttons Selected/Hover]

    /**
     *
     * @param choiceComboBox
     */
    public void setSelected(ChoiceComboBox choiceComboBox, boolean focused){
        // Make this object use the custom css styling
        if (focused){
            choiceComboBox.getStyleClass().add("choice-combobox-focused");
        } else {
            choiceComboBox.getStyleClass().remove("choice-combobox-focused");
        }
    }

    /**
     *
     * @param choiceTextField
     */
    public void setSelected(ChoiceTextField choiceTextField, boolean focused){
        // Make this object use the custom css styling
        if (focused){
            choiceTextField.getStyleClass().add("choice-textfield-focused");
        } else {
            choiceTextField.getStyleClass().remove("choice-textfield-focused");
        }
    }

    /**
     *
     * @param customButton
     */
    public void setSelected(CustomButton customButton, boolean focused){
        // Make this object use the custom css styling
        if (customButton.getStyleClass().contains("custom-button-text")){
            if (focused){
                customButton.getStyleClass().add("custom-button-text-focused");
            } else {
                customButton.getStyleClass().remove("custom-button-text-focused");
            }
        } else {
            if (focused){
                customButton.getStyleClass().add("custom-button-focused");
            } else {
                customButton.getStyleClass().remove("custom-button-focused");
            }
        }
    }

    /**
     *
     * @param customButtonOther
     */
    public void setSelected(CustomButtonOther customButtonOther, boolean focused){
        // Make this object use the custom css styling
        if (focused){
            customButtonOther.getStyleClass().add("custom-other-button-focused");
        } else {
            customButtonOther.getStyleClass().remove("custom-other-button-focused");
        }
    }

    /**
     *
     * @param choiceComboBox
     */
    public void setHovered(ChoiceComboBox choiceComboBox, boolean hovered){
        // Make this object use the custom css styling
        if (hovered){
            choiceComboBox.getStyleClass().add("choice-combobox-hovered");
        } else {
            choiceComboBox.getStyleClass().remove("choice-combobox-hovered");
        }
    }

    /**
     *
     * @param choiceTextField
     */
    public void setHovered(ChoiceTextField choiceTextField, boolean hovered){
        // Make this object use the custom css styling
        if (hovered){
            choiceTextField.getStyleClass().add("choice-textfield-hovered");
        } else {
            choiceTextField.getStyleClass().remove("choice-textfield-hovered");
        }
    }

    /**
     *
     * @param customButton
     */
    public void setHovered(CustomButton customButton, boolean hovered){
        // Make this object use the custom css styling
        if (customButton.getStyleClass().contains("custom-button-text")){
            if (hovered){
                customButton.getStyleClass().add("custom-button-text-hovered");
            } else {
                customButton.getStyleClass().remove("custom-button-text-hovered");
            }
        } else {
            if (hovered){
                customButton.getStyleClass().add("custom-button-hovered");
            } else {
                customButton.getStyleClass().remove("custom-button-hovered");
            }
        }
    }

    /**
     *
     * @param customButtonOther
     */
    public void setHovered(CustomButtonOther customButtonOther, boolean hovered){
        // Make this object use the custom css styling
        if (hovered){
            customButtonOther.getStyleClass().add("custom-other-button-hovered");
        } else {
            customButtonOther.getStyleClass().remove("custom-other-button-hovered");
        }
    }

    /**
     *
     * @param customButtonControls
     */
    public void setHovered(CustomButtonControls customButtonControls, boolean hovered){
        // Make this object use the custom css styling
        if (hovered){
            customButtonControls.getStyleClass().add("custom-controls-button-hovered");
        } else {
            customButtonControls.getStyleClass().remove("custom-controls-button-hovered");
        }
    }

    //endregion

}
