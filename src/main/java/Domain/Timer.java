package Domain;

import Domain.Singletons.ConsultantSingleton;
import Domain.Singletons.TimerSingleton;
import UI.Buttons.CustomButtonControls;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.util.Duration;

public class Timer {

    private Timeline timeline;

    private String sec = "0";
    private String min = "0";
    private int cycle = 1;

    /*
    1 = Task, 2 = Break, 3 = Long Break
     */
    private IntegerProperty timeTypeProperty = new SimpleIntegerProperty(1);

    private BooleanProperty timeRunningProperty = new SimpleBooleanProperty(false);
    private StringProperty timeProperty = new SimpleStringProperty("");



    public BooleanProperty timeRunningProperty() {return this.timeRunningProperty;}
    public StringProperty timeProperty() {return this.timeProperty;}


    private String getSec() {

        String secText;

        if (Integer.parseInt(this.sec) < 10){
            secText = "0"+this.sec;
        } else {
            secText = this.sec;
        }

        return secText;
    }

    private String getMin() {

        String minText;

        if (Integer.parseInt(this.min) < 10){
            minText = "0"+this.min;
        } else {
            minText = this.min;
        }

        return minText;
    }

    public Timer(){

        // Set the time to the consultants time
        this.setTime(
                ""+ConsultantSingleton.getInstance().getTaskTimeMin(),
                ""+ConsultantSingleton.getInstance().getTaskTimeSec()
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

    public void setTime(String min, String sec){
        this.min = min;
        this.sec = sec;
        // Set the time
        this.timeProperty.set(getMin()+" : "+getSec());
    }

    public void resetTimer(){
        this.min = ""+ConsultantSingleton.getInstance().getTaskTimeMin();
        this.sec = ""+ConsultantSingleton.getInstance().getTaskTimeSec();
        this.cycle=1;
        this.timeRunningProperty.setValue(false);
        this.timeTypeProperty.setValue(1);
        this.timeline.stop();

        setTime(""+min,""+sec);
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
        if (timeTypeProperty.getValue()==1){
            if (this.cycle==4){
                this.timeTypeProperty.setValue(3);
                this.min = ""+ConsultantSingleton.getInstance().getLongBreakTimeMin();
                this.sec = ""+ConsultantSingleton.getInstance().getLongBreakTimeSec();
            } else {
                this.timeTypeProperty.setValue(2);
                this.min = ""+ConsultantSingleton.getInstance().getBreakTimeMin();
                this.sec = ""+ConsultantSingleton.getInstance().getBreakTimeSec();
            }
        } else {
            if (this.cycle==4){
                this.cycle=1;
            } else {
                this.cycle++;
            }
            this.timeTypeProperty.setValue(1);
            this.min = ""+ConsultantSingleton.getInstance().getTaskTimeMin();
            this.sec = ""+ConsultantSingleton.getInstance().getTaskTimeSec();
        }

        setTime(""+min,""+sec);

        System.out.println(cycle);
    }

    public synchronized void timerTick(){
        if (this.sec.equals("0")){
            if (this.min.equals("0")){
                // Set the next timer to the right state (Task, Break or Long Break)
                if (this.timeTypeProperty.getValue()==3 || timeTypeProperty.getValue()==2){

                    // Next cycle
                    cycle++;

                    this.timeTypeProperty.setValue(1);
                    this.setTime(""+ ConsultantSingleton.getInstance().getTaskTimeMin(),
                            ""+ConsultantSingleton.getInstance().getTaskTimeSec());
                } else if (cycle==4){
                    // Set long break
                    this.timeTypeProperty.setValue(3);
                    this.setTime(""+ ConsultantSingleton.getInstance().getLongBreakTimeMin(),
                            ""+ConsultantSingleton.getInstance().getLongBreakTimeSec());
                    this.cycle=1;
                } else {
                    // Set break
                    this.timeTypeProperty.setValue(2);
                    this.setTime(""+ ConsultantSingleton.getInstance().getBreakTimeMin(),
                            ""+ConsultantSingleton.getInstance().getBreakTimeSec());
                }

            } else {
                this.sec = "59";
                this.min = ""+(Integer.parseInt(this.min)-1);
            }
        } else {
            this.sec = ""+(Integer.parseInt(this.sec)-1);
        }
        this.timeProperty.set(getMin()+" : "+getSec());
        System.out.println(cycle);
    }

}
