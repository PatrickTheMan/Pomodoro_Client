package Domain;

import Application.Singleton.ControllerSingleton;
import Domain.Singletons.ConsultantSingleton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.util.Duration;

import java.sql.Time;

public class Timer {

    private Timeline timeline;

    private Time standardTaskTime = Time.valueOf("00:25:00");
    private Time standardBreakTime = Time.valueOf("00:05:00");
    private Time standardLongBreakTime = Time.valueOf("00:30:00");

    private Time time=Time.valueOf("00:00:00");
    private int cycle = 1;

    private boolean sound = true; //TODO

    private StringProperty timeTypeProperty = new SimpleStringProperty("Task");
    private BooleanProperty timeRunningProperty = new SimpleBooleanProperty(false);
    private StringProperty timeProperty = new SimpleStringProperty("");



    public StringProperty timeTypeProperty() {return this.timeTypeProperty;}
    public BooleanProperty timeRunningProperty() {return this.timeRunningProperty;}
    public StringProperty timeProperty() {return this.timeProperty;}


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

    public void setTime(Time time){

        this.time = time;
        this.timeProperty.set(this.time.toString());

    }

    public void resetTimer(){
        this.setTime(ConsultantSingleton.getInstance().getTaskTime());
        this.cycle=1;
        this.timeRunningProperty.setValue(false);
        this.timeTypeProperty.setValue("Task");
        this.timeline.stop();

        // Set stage title, so you can se the time
        ControllerSingleton.getInstance().setTimerTitle();
    }

    public void pauseTimer(){
        this.timeRunningProperty.setValue(false);
        if (this.timeline!=null){
            this.timeline.pause();
        }
    }

    public void startTimer(){
        this.timeRunningProperty.setValue(true);
        if (this.timeline!=null){
            this.timeline.play();
        }
    }

    public void skipTimer(){
        if (timeTypeProperty.getValue()=="Task"){
            if (this.cycle==4){
                this.timeTypeProperty.setValue("Long Break");
                this.time = (ConsultantSingleton.getInstance().exists() ?
                        ConsultantSingleton.getInstance().getLongBreakTime() :
                        this.standardLongBreakTime);
            } else {
                this.timeTypeProperty.setValue("Break");
                this.time = (ConsultantSingleton.getInstance().exists() ?
                        ConsultantSingleton.getInstance().getBreakTime() :
                        this.standardBreakTime);
            }
        } else {
            if (this.cycle==4){
                this.cycle=1;
            } else {
                this.cycle++;
            }
            this.timeTypeProperty.setValue("Task");
            this.time = (ConsultantSingleton.getInstance().exists() ?
                    ConsultantSingleton.getInstance().getTaskTime() :
                    this.standardTaskTime);
        }

        // Change the property
        this.timeProperty.set(this.time.toString());

        // Set stage title, so you can se the time
        ControllerSingleton.getInstance().setTimerTitle();
    }

    public synchronized void timerTick(){

        if (this.time.getHours()==0 && this.time.getMinutes()==0 && this.time.getSeconds()==0){
            // Set the next timer to the right state (Task, Break or Long Break)
            if (this.timeTypeProperty.getValue()=="Long Break" || timeTypeProperty.getValue()=="Break"){

                // Next cycle
                cycle++;

                this.timeTypeProperty.setValue("Task");
                this.setTime((ConsultantSingleton.getInstance().exists() ?
                        ConsultantSingleton.getInstance().getTaskTime() :
                        this.standardTaskTime));
            } else if (cycle==4){
                // Set long break
                this.timeTypeProperty.setValue("Long Break");
                this.setTime((ConsultantSingleton.getInstance().exists() ?
                        ConsultantSingleton.getInstance().getLongBreakTime() :
                        this.standardLongBreakTime));
                this.cycle=1;
            } else {
                // Set break
                this.timeTypeProperty.setValue("Break");
                this.setTime((ConsultantSingleton.getInstance().exists() ?
                        ConsultantSingleton.getInstance().getBreakTime() :
                        this.standardBreakTime));
            }
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



}