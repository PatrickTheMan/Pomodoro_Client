package Domain;

import Application.Singleton.ControllerSingleton;
import Domain.Singletons.ConsultantSingleton;
import Domain.Singletons.TimerSingleton;
import Foundation.Singletons.InformationContainerSingleton;
import UI.Structures.SceneStructureParts.SmallParts.Taskline;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.sql.Time;
import java.util.Objects;

/**
 * @author Patrick G. Schemel
 */
public class Timer {

    //region [Variables]

    private Timeline timeline;

    private Time standardTaskTime = Time.valueOf("00:25:00");
    private Time standardBreakTime = Time.valueOf("00:05:00");
    private Time standardLongBreakTime = Time.valueOf("00:30:00");

    private Time taskOffsetTime = Time.valueOf("00:00:00");

    private Time time=Time.valueOf("00:00:00");
    private int cycle = 1;

    private boolean sound = true;

    //endregion

    //region [Properties]

    private final StringProperty timeTypeProperty = new SimpleStringProperty("Task");
    private final BooleanProperty timeRunningProperty = new SimpleBooleanProperty(false);
    private final StringProperty timeProperty = new SimpleStringProperty("");

    public StringProperty timeTypeProperty() {return this.timeTypeProperty;}
    public BooleanProperty timeRunningProperty() {return this.timeRunningProperty;}
    public StringProperty timeProperty() {return this.timeProperty;}

    //endregion

    //region [Normal Getters & Setters]

    public Time getStandardTaskTime() {
        return standardTaskTime;
    }

    public void setStandardTaskTime(Time standardTaskTime) {
        this.standardTaskTime = standardTaskTime;
    }

    public Time getStandardBreakTime() {
        return standardBreakTime;
    }

    public void setStandardBreakTime(Time standardBreakTime) {
        this.standardBreakTime = standardBreakTime;
    }

    public Time getStandardLongBreakTime() {
        return standardLongBreakTime;
    }

    public void setStandardLongBreakTime(Time standardLongBreakTime) {
        this.standardLongBreakTime = standardLongBreakTime;
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public Time getTaskOffsetTime() {
        return taskOffsetTime;
    }

    public void setTaskOffsetTime() {
        this.taskOffsetTime = getCurrentTimeSpent();
    }

    //endregion

    //region [Constructor]

    public Timer(){

        // Set the time to the consultants time
        this.setTime(
                ConsultantSingleton.getInstance().getTaskTime()
        );

        // Initiate and start timeline
        this.timeline = new Timeline();
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        ae -> {
                            timerTick();
                            if (!this.timeRunningProperty.getValue()) {
                                this.timeline.stop();
                            }
                        })
        );
    }

    //endregion

    //region [Button Functionality]

    /**
     * <Strong>Sets the timers time</Strong>
     * @param time is the time the timer gets set to
     */
    public void setTime(Time time){
        this.time = Time.valueOf(time.toString());
        this.timeProperty.setValue(this.time.toString());

    }

    /**
     * <Strong>Resets the timer to task first cycle</Strong>
     */
    public void resetTimer(){
        this.setTime(ConsultantSingleton.getInstance().getTaskTime());
        this.cycle=1;
        this.timeRunningProperty.setValue(false);
        setTimerTypeTaskWithTaskName();
        this.timeline.stop();

        // Set stage title, so you can se the time
        ControllerSingleton.getInstance().setTimerTitle();
    }

    /**
     * <Strong>Pauses the timer</Strong>
     */
    public void pauseTimer(){
        this.timeRunningProperty.setValue(false);
        if (this.timeline!=null){
            this.timeline.pause();
        }
    }

    /**
     * <Strong>Starts the timer</Strong>
     */
    public void startTimer(){
        this.timeRunningProperty.setValue(true);
        if (this.timeline!=null){
            this.timeline.play();
        }
    }

    /**
     * <Strong>Skips the current timertype (Task/Break/Long Break) and goes to the next type (Task->Break->Long Break->Task)</Strong>
     */
    public void skipTimer(){
        // Change timer type
        if (Objects.equals(timeTypeProperty.getValue().substring(0,4), "Task")){
            if (this.cycle==4){
                this.timeTypeProperty.setValue("Long Break");
                setTime((ConsultantSingleton.getInstance().exists() ?
                        ConsultantSingleton.getInstance().getLongBreakTime() :
                        this.standardLongBreakTime)
                );
            } else {
                this.timeTypeProperty.setValue("Break");
                setTime((ConsultantSingleton.getInstance().exists() ?
                        ConsultantSingleton.getInstance().getBreakTime() :
                        this.standardBreakTime)
                );
            }
        } else {

            if (this.cycle==4){
                this.cycle=1;
            } else {
                this.cycle++;
            }

            // Set the timertype to task + the task name
            setTimerTypeTaskWithTaskName();

            setTime((ConsultantSingleton.getInstance().exists() ?
                    ConsultantSingleton.getInstance().getTaskTime() :
                    this.standardTaskTime)
            );
        }

        // Change the property
        this.timeProperty.set(this.time.toString());

        // Set stage title, so you can se the time
        ControllerSingleton.getInstance().setTimerTitle();
    }

    //endregion

    //region [Sound]

    private Media media;

    /**
     * <Strong>Plays a sound, depending on the timertype (Task/Break/Long Break)</Strong>
     */
    public void playSound(){

        media = null;

        if (sound){
            switch (this.timeTypeProperty.getValue().substring(0,4)){
                case ("Task") -> media = new Media(new File("src/main/resources/Sound/taskSound.mp3").toURI().toString());
                case ("Brea") -> media = new Media(new File("src/main/resources/Sound/breakSound.mp3").toURI().toString());
                case ("Long") -> media = new Media(new File("src/main/resources/Sound/longbreakSound.mp3").toURI().toString());
            }
        }

        if (media != null){
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnEndOfMedia(mediaPlayer::stop);
            mediaPlayer.play();
        }

    }

    //endregion

    //region [TimerType]

    /**
     * <Strong>Sets the text underneath the timer, so one can se the current task</Strong>
     */
    public void setTimerTypeTaskWithTaskName(){
        // Set new title to the timertype
        if (InformationContainerSingleton.getInstance().getDoTodayList().size()>0 &&
                ((Taskline)InformationContainerSingleton.getInstance().getDoTodayList().get(0)).getTaskChoice().getChoicebox().getValue() != null &&
                !((Taskline)InformationContainerSingleton.getInstance().getDoTodayList().get(0)).getTaskChoice().getChoicebox().getValue().toString().equals("")){
            this.timeTypeProperty.setValue("Task - "+((Taskline)InformationContainerSingleton.getInstance().getDoTodayList().get(0)).getTaskChoice().getChoicebox().getValue().toString());
        } else {
            this.timeTypeProperty.setValue("Task");
        }
    }

    //endregion

    //region [Time]

    /**
     * <Strong>Gets the amount of time that has passed</Strong>
     * @return the time that has passed as Time
     */
    public Time getCurrentTimeSpent(){

        switch (timeTypeProperty.getValue().substring(0,4)) {
            case ("Task") -> {
                if (!ConsultantSingleton.getInstance().exists()){
                    return Time.valueOf((this.standardTaskTime.getHours()- this.time.getHours()) + ":" +
                            (this.standardTaskTime.getMinutes() - this.time.getMinutes()) + ":" +
                            (this.standardTaskTime.getSeconds() - this.time.getSeconds())
                    );
                } else {
                    return Time.valueOf((ConsultantSingleton.getInstance().getTaskTime().getHours()- this.time.getHours()) + ":" +
                            (ConsultantSingleton.getInstance().getTaskTime().getMinutes() - this.time.getMinutes()) + ":" +
                            (ConsultantSingleton.getInstance().getTaskTime().getSeconds() - this.time.getSeconds())
                    );
                }
            }
            case ("Brea") -> {
                if (!ConsultantSingleton.getInstance().exists()){
                    return Time.valueOf((this.standardBreakTime.getHours()- this.time.getHours()) + ":" +
                            (this.standardBreakTime.getMinutes() - this.time.getMinutes()) + ":" +
                            (this.standardBreakTime.getSeconds() - this.time.getSeconds())
                    );
                } else {
                    return Time.valueOf((ConsultantSingleton.getInstance().getBreakTime().getHours()- this.time.getHours()) + ":" +
                            (ConsultantSingleton.getInstance().getBreakTime().getMinutes() - this.time.getMinutes()) + ":" +
                            (ConsultantSingleton.getInstance().getBreakTime().getSeconds() - this.time.getSeconds())
                    );
                }
            }
            case ("Long") -> {
                if (!ConsultantSingleton.getInstance().exists()){
                    return Time.valueOf((this.standardLongBreakTime.getHours()- this.time.getHours()) + ":" +
                            (this.standardLongBreakTime.getMinutes() - this.time.getMinutes()) + ":" +
                            (this.standardLongBreakTime.getSeconds() - this.time.getSeconds())
                    );
                } else {
                    return Time.valueOf((ConsultantSingleton.getInstance().getLongBreakTime().getHours()- this.time.getHours()) + ":" +
                            (ConsultantSingleton.getInstance().getLongBreakTime().getMinutes() - this.time.getMinutes()) + ":" +
                            (ConsultantSingleton.getInstance().getLongBreakTime().getSeconds() - this.time.getSeconds())
                    );
                }
            }
        }
        return Time.valueOf("00:00:00");
    }

    /**
     * <Strong>Ticks the timer once forward</Strong>
     */
    public synchronized void timerTick(){

        // If the timer is done
        if (this.time.getHours()==0 && this.time.getMinutes()==0 && this.time.getSeconds()==0){
            // Set the next timer to the right state (Task, Break or Long Break)
            if (this.timeTypeProperty.getValue().equals("Long Break") || timeTypeProperty.getValue().equals("Break")){

                // Next cycle
                cycle++;

                // Set task + title
                TimerSingleton.getInstance().setTimerTypeTaskWithTaskName();

                setTime((ConsultantSingleton.getInstance().exists() ?
                        ConsultantSingleton.getInstance().getTaskTime() :
                        this.standardTaskTime)
                );

            } else {

                // Change the Task time spend on the task in DB and update the remaining time if any
                InformationContainerSingleton.getInstance().nextTask();

                if (cycle==4){

                    // Set long break
                    this.timeTypeProperty.setValue("Long Break");
                    setTime((ConsultantSingleton.getInstance().exists() ?
                            ConsultantSingleton.getInstance().getLongBreakTime() :
                            this.standardLongBreakTime)
                    );
                    this.cycle=1;

                } else {



                    // Set break
                    this.timeTypeProperty.setValue("Break");
                    setTime((ConsultantSingleton.getInstance().exists() ?
                            ConsultantSingleton.getInstance().getBreakTime() :
                            this.standardBreakTime)
                    );
                }

                // Reset the timeOffset
                this.taskOffsetTime = Time.valueOf("00:00:00");



            }

            // Play Sound
            playSound();

        } else {
            if (this.time.getSeconds() == 0){
                if (this.time.getMinutes() == 0){
                    this.time = Time.valueOf((this.time.getHours()-1)+":59:59");
                } else {
                    this.time = Time.valueOf((this.time.getHours())+":"+(this.time.getMinutes()-1)+":59");
                }
            } else {
                this.time.setSeconds(this.time.getSeconds()-1);
            }
        }

        // Change the property
        this.timeProperty.set(this.time.toString());

        // Set stage title, so you can se the time
        ControllerSingleton.getInstance().setTimerTitle();
    }

    //endregion

}
