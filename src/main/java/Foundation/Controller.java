package Foundation;

import Domain.Consultant;
import Domain.Singletons.ConsultantSingleton;
import Domain.Singletons.TimerSingleton;
import Domain.Task;
import Foundation.Singletons.DBSingleton;
import Domain.Singletons.InformationContainerSingleton;
import UI.Buttons.CustomButton;
import UI.Buttons.CustomButtonControls;
import UI.Buttons.CustomButtonOther;
import Foundation.Enums.SceneType;
import Foundation.Singletons.ScenehandlerSingleton;
import UI.Structures.SceneStructureParts.SmallParts.*;
import UI.Structures.SceneStructureParts.Windows.SettingsWindow;

import java.sql.Time;

/**
 * @author Patrick G. Schemel
 */
public class Controller {

    //region [Scene]

    /**
     * <Strong>Set the scene to home</Strong>
     */
    public void setSceneHome(){
        ScenehandlerSingleton.getInstance().setStage(SceneType.HOME);
    }

    /**
     * <Strong>Set the scene to overview</Strong>
     */
    public void setSceneOverview(){
        ScenehandlerSingleton.getInstance().setStage(SceneType.OVERVIEW);
    }

    /**
     * <Strong>Set the scene to dotoday</Strong>
     */
    public void setSceneDoToday(){
        ScenehandlerSingleton.getInstance().setStage(SceneType.DOTODAY);
    }

    /**
     * <Strong>open or close the mini pomodoro window</Strong>
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
     * <Strong>Set the consultants name in the stage title</Strong>
     * @param consultant is the consultant that is chosen
     */
    public void setConsultantTitle(Consultant consultant){

        // Change title so consultant is added
        ScenehandlerSingleton.getInstance().getStage().setTitle(
                (this.timerSetAlready ? TimerSingleton.getInstance().timeProperty().getValue() : "No user")+
                    consultant.getName()+
                        ScenehandlerSingleton.getInstance().getSceneTitle()
        );

        // Update boolean
        this.consultantSetAlready = true;

        // Update title timer
        this.setTimerTitle();
    }

    /**
     * <Strong>Set the different times for the settings-window fields to the consultants chosen once</Strong>
     * @param consultant is the consultant, which has the different times
     * @param settingsWindow is the window, which has the different fields that needs to be updated
     */
    public void getConsultantValues(Consultant consultant, SettingsWindow settingsWindow){
        // Update fields
        settingsWindow.setTaskTimeFieldText(consultant.getTaskTime().toString());
        settingsWindow.setBreakTimeFieldText(consultant.getBreakTime().toString());
        settingsWindow.setLongbreakTimeFieldText(consultant.getLongBreakTime().toString());
    }

    //endregion

    //region [Timer]

    private boolean timerSetAlready=false;

    /**
     * <Strong>Set the time in the stage title</Strong>
     */
    public void setTimerTitle(){

        // Change title so timer is added
        ScenehandlerSingleton.getInstance().getStage().setTitle(
                TimerSingleton.getInstance().timeProperty().getValue()+" - "+
                        (this.consultantSetAlready ? ConsultantSingleton.getInstance().getName()+" - " : "")+
                        ScenehandlerSingleton.getInstance().getSceneTitle()
        );

        // Update the boolean
        this.timerSetAlready = true;

    }

    /**
     * <Strong>Set the time on timer</Strong>
     * @param time is the time, that the timer gets set to
     */
    public void setTimeOnTimer(Time time){
        TimerSingleton.getInstance().setTime(time);
    }

    /**
     * <Strong>Set the times on the consultantsingleton or set the standardtimes</Strong>
     * @param choiceComboBox is the choiceComboBox with the consultants name
     * @param taskTime is the task time chosen
     * @param breakTime is the break time chosen
     * @param longBreakTime is the long break time chosen
     */
    public void setTimes(ChoiceComboBox choiceComboBox,Time taskTime, Time breakTime, Time longBreakTime){
        if (choiceComboBox.getChoicebox().getValue()!=null && !choiceComboBox.getChoicebox().getValue().equals("")){

            // Set the consultant & Set the times to the ones chosen
            ConsultantSingleton.getInstance().setConsultant(
                    InformationContainerSingleton.getInstance().getConsultant(choiceComboBox.getChoicebox().getValue().toString()),
                    taskTime,
                    breakTime,
                    longBreakTime);

            // Update the consultant with the new preferred times in DB
            DBSingleton.getInstance().updateConsultant(ConsultantSingleton.getInstance());

            // Reset Timer
            TimerSingleton.getInstance().resetTimer();

        } else {
            TimerSingleton.getInstance().setStandardTaskTime(taskTime);
            TimerSingleton.getInstance().setStandardBreakTime(breakTime);
            TimerSingleton.getInstance().setStandardLongBreakTime(longBreakTime);
        }
    }

    /**
     * <Strong>Play or pause the timer, depending on the button state</Strong>
     * @param customButtonControls is the play/pause button
     */
    public void playOrPause(CustomButtonControls customButtonControls){
        if (customButtonControls.getStyleClass().contains("custom-controls-button-play")){
            TimerSingleton.getInstance().startTimer();
        } else {
            TimerSingleton.getInstance().pauseTimer();
        }
    }

    /**
     * <Strong>Resets the timer</Strong>
     */
    public void stop(){
        TimerSingleton.getInstance().resetTimer();
    }

    /**
     * <Strong>Skips the timers current state and goes to the next one</Strong>
     */
    public void skip(){
        TimerSingleton.getInstance().skipTimer();
    }

    /**
     * <Strong>Changes the sound boolean in the timersingleton to the  of what it is</Strong>
     */
    public void soundOnOff(){
        if (TimerSingleton.getInstance().isSound()){
            TimerSingleton.getInstance().setSound(false);
        } else {
            TimerSingleton.getInstance().setSound(true);
        }
    }

    //endregion

    //region [DB]

    /**
     * <Strong>Update a task</Strong>
     * @param task is the task to be updated
     */
    public void updateTaskDB(Task task){
        DBSingleton.getInstance().updateTask(task);
        InformationContainerSingleton.getInstance().updateTasks();
    }

    //endregion

    //region [InformationContainer]

    /**
     * <Strong>Add to the amount of active pomodoros int</Strong>
     * @param amount is the amount to be added
     */
    public void addActivePomodoro(int amount){
        InformationContainerSingleton.getInstance().setAmountOfActivePomodoros(
                InformationContainerSingleton.getInstance().getAmountOfActivePomodoros()+amount
        );
    }

    /**
     * <Strong>Remove from the active </Strong>
     * @param amount is the amount that will be removed
     */
    public void removeActivePomodoro(int amount){
        InformationContainerSingleton.getInstance().setAmountOfActivePomodoros(
                InformationContainerSingleton.getInstance().getAmountOfActivePomodoros()-amount
        );
    }

    /**
     * <Strong>Sets the amount of active pomodoros to 0</Strong>
     */
    public void clearActivePomodoro(){
        InformationContainerSingleton.getInstance().setAmountOfActivePomodoros(0);
    }

    //endregion

    //region [Buttons]

    /**
     * <Strong>Adds 1 to the headline, used to set the pomodoro amount on the taskline</Strong>
     * @param headline is the targetet headline, which contains the label with the pomodoroamount
     */
    public void addToCounter(Headline headline){

        headline.getLabel().setText(""+(Integer.parseInt(headline.getLabel().getText())+1));

    }

    /**
     * <Strong>Removes 1 from the headline, used to set the pomodoro amount on the taskline</Strong>
     * @param headline is the targetet headline, which contains the label with the pomodoroamount
     */
    public void subtractToCounter(Headline headline){

        if (!headline.getLabel().getText().equals("1")){
            headline.getLabel().setText(""+(Integer.parseInt(headline.getLabel().getText())-1));
        }

    }

    /**
     * <Strong>Adds a taskline to the informationcontainers dotoday arraylist</Strong>
     * @param taskline is the taskline to be added
     */
    public void newTasklineInDoToday(Taskline taskline){
        InformationContainerSingleton.getInstance().newTasklineInDoToday(taskline);
    }

    /**
     * <Strong>Removes a taskline from the informationcontainers dotoday arraylist</Strong>
     * @param taskline is the taskline to be removed
     */
    public void removeTasklineInDoToday(Taskline taskline){
        InformationContainerSingleton.getInstance().removeTasklineInDoToday(taskline);
    }

    //endregion

    //region [Buttons Selected/Hover]

    /**
     * <Strong>Makes the node use custom css for focused</Strong>
     * @param choiceComboBox is the node
     * @param focused is whether or not it is being focused or not
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
     * <Strong>Makes the node use custom css for focused</Strong>
     * @param choiceTextField is the node
     * @param focused is whether or not it is being focused or not
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
     * <Strong>Makes the node use custom css for focused</Strong>
     * @param customButton is the node
     * @param focused is whether or not it is being focused or not
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
     * <Strong>Makes the node use custom css for focused</Strong>
     * @param customButtonOther is the node
     * @param focused is whether or not it is being focused or not
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
     * <Strong>Makes the node use custom css for hovered</Strong>
     * @param choiceComboBox is the node
     * @param hovered is whether or not it is being hovederd or not
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
     * <Strong>Makes the node use custom css for hovered</Strong>
     * @param choiceTextField is the node
     * @param hovered is whether or not it is being hovederd or not
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
     * <Strong>Makes the node use custom css for hovered</Strong>
     * @param customButton is the node
     * @param hovered is whether or not it is being hovederd or not
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
     * <Strong>Makes the node use custom css for hovered</Strong>
     * @param customButtonOther is the node
     * @param hovered is whether or not it is being hovederd or not
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
     * <Strong>Makes the node use custom css for hovered</Strong>
     * @param customButtonControls is the node
     * @param hovered is whether or not it is being hovederd or not
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
