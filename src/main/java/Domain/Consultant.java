package Domain;

import java.util.ArrayList;

public class Consultant {

    private String firstName;
    private String lastName;
    private double taskTime;
    private double breakTime;
    private double longBreakTime;

    public Consultant(String firstName, String lastName, double taskTime, double breakTime, double longBreakTime){
        this.firstName = firstName;
        this.lastName = lastName;
        this.taskTime = taskTime;
        this.breakTime = breakTime;
        this.longBreakTime = longBreakTime;
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

    public double getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(double taskTime) {
        this.taskTime = taskTime;
    }

    public double getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(double breakTime) {
        this.breakTime = breakTime;
    }

    public double getLongBreakTime() {
        return longBreakTime;
    }

    public void setLongBreakTime(double longBreakTime) {
        this.longBreakTime = longBreakTime;
    }

    public void setConsultant(Consultant consultant){
        this.firstName = consultant.getFirstName();
        this.lastName = consultant.getLastName();
        this.taskTime = consultant.getTaskTime();
        this.breakTime = consultant.getBreakTime();
        this.longBreakTime = consultant.getLongBreakTime();
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
