package Domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Consultant {

    private String firstName;
    private String lastName;
    private double taskTime;
    private double breakTime;
    private double longBreakTime;

    private ArrayList<Task> taskList = new ArrayList<>();

    private StringProperty firstNameProperty = new SimpleStringProperty("");
    private BooleanProperty existsProperty = new SimpleBooleanProperty(false);



    public StringProperty firstNameProperty() {return firstNameProperty;}
    public BooleanProperty existsProperty() {return existsProperty;}

    public Consultant(String firstName, String lastName, double taskTime, double breakTime, double longBreakTime){
        this.firstName = firstName;
        this.lastName = lastName;
        this.taskTime = taskTime;
        this.breakTime = breakTime;
        this.longBreakTime = longBreakTime;

        this.firstNameProperty.set(firstName);
        this.existsProperty.setValue(true);
    };

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName(){
        return getFirstName()+" "+getLastName();
    }

    public double getTaskTime() {
        return taskTime;
    }

    public int getTaskTimeMin(){return (int)taskTime;}

    public int getTaskTimeSec(){return (int)((taskTime-(int)taskTime)*10);}

    public void setTaskTime(double taskTime) {
        this.taskTime = taskTime;
    }

    public double getBreakTime() {
        return breakTime;
    }

    public int getBreakTimeMin(){return (int)breakTime;}

    public int getBreakTimeSec(){return (int)((breakTime-(int)breakTime)*10);}

    public void setBreakTime(double breakTime) {
        this.breakTime = breakTime;
    }

    public double getLongBreakTime() {
        return longBreakTime;
    }

    public int getLongBreakTimeMin(){return (int)longBreakTime;}

    public int getLongBreakTimeSec(){return (int)((longBreakTime-(int)longBreakTime)*10);}

    public void setLongBreakTime(double longBreakTime) {
        this.longBreakTime = longBreakTime;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void clearTaskList(){
        this.taskList.clear();
    }

    public boolean exists(){
        if (getFirstName().equals("")){
            return false;
        } else {
            return true;
        }
    }

    public void setConsultant(Consultant consultant){
        this.firstName = consultant.getFirstName();
        this.lastName = consultant.getLastName();
        this.taskTime = consultant.getTaskTime();
        this.breakTime = consultant.getBreakTime();
        this.longBreakTime = consultant.getLongBreakTime();

        this.firstNameProperty.set(consultant.getFullName());
    }

    public void loadTestConsultant(){
        firstName = "Jhon";
        lastName = "Nielsen";
        taskTime = 35;
        breakTime = 10;
        longBreakTime = 45;
    }

    /**
     *
     *
     * @return
     */
    public ArrayList<Consultant> getTestConsultants(){

        ArrayList<Consultant> consultants = new ArrayList<>();

        consultants.add(new Consultant("Lea","Jefferson",30,10,20));
        consultants.add(new Consultant("Jeff","Jhonson",25,10,15));
        consultants.add(new Consultant("Tom","Christensen",60,20,40));
        consultants.add(new Consultant("Liam","Arnolsen",15,3,15));
        consultants.add(new Consultant("Bo","Schnell",25,5,30));

        return consultants;
    }

    public  ArrayList<String> getTestConsultantsNames(){

        ArrayList<String> names = new ArrayList<>();

        ArrayList<Consultant> consultants = getTestConsultants();

        for (Consultant c: consultants) {
            names.add(c.getFirstName()+" "+getLastName());
        }

        return names;
    }
}
