package Domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Time;

public class Consultant {

    private String email;
    private String name;
    private String officeName;
    private Time taskTime;
    private Time breakTime;
    private Time longBreakTime;
    private boolean active;
    private int order;
    private String status;

    private StringProperty nameProperty = new SimpleStringProperty("");
    private BooleanProperty existsProperty = new SimpleBooleanProperty(false);


    public StringProperty nameProperty() {return nameProperty;}
    public BooleanProperty existsProperty() {return existsProperty;}

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getOfficeName() {
        return officeName;
    }

    public Time getTaskTime() {
        return taskTime;
    }

    public Time getBreakTime() {
        return breakTime;
    }

    public Time getLongBreakTime() {
        return longBreakTime;
    }

    public boolean isActive() {
        return active;
    }

    public int getOrder() {
        return order;
    }

    public Consultant(String email, String name, String officeName, Time taskTime, Time breakTime, Time longBreakTime, boolean active, int order, String status) {
        this.email = email;
        this.name = name;
        this.officeName = officeName;
        this.taskTime = taskTime;
        this.breakTime = breakTime;
        this.longBreakTime = longBreakTime;
        this.active = active;
        this.order = order;
        this.status = status;

        this.nameProperty.set(name);
    }

    public boolean exists(){
        return existsProperty.getValue();
    }

    public void setConsultant(Consultant consultant){
        this.email = consultant.email;
        this.name = consultant.name;
        this.officeName = consultant.officeName;
        this.taskTime = consultant.taskTime;
        this.breakTime = consultant.breakTime;
        this.longBreakTime = consultant.longBreakTime;
        this.active = consultant.active;
        this.order = consultant.order;
        this.status = consultant.status;

        this.existsProperty.setValue(true);
    }

}
