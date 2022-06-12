package Domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Time;

/**
 * @author Patrick G. Schemel
 */
public class Consultant {

    //region [Variables]

    private String email;
    private String name;
    private String officeName;
    private Time taskTime;
    private Time breakTime;
    private Time longBreakTime;
    private boolean active;
    private int order;
    private String status;

    //endregion

    //region [Properties]

    private StringProperty nameProperty = new SimpleStringProperty("");
    private BooleanProperty existsProperty = new SimpleBooleanProperty(false);

    public StringProperty nameProperty() {return nameProperty;}
    public BooleanProperty existsProperty() {return existsProperty;}

    //endregion

    //region [Normal Getters & Setters]

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public void setTaskTime(Time taskTime) {
        this.taskTime = taskTime;
    }

    public void setBreakTime(Time breakTime) {
        this.breakTime = breakTime;
    }

    public void setLongBreakTime(Time longBreakTime) {
        this.longBreakTime = longBreakTime;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    //endregion

    //region [Properties]

    public String getNameProperty() {
        return nameProperty.get();
    }

    public StringProperty namePropertyProperty() {
        return nameProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty.set(nameProperty);
    }

    public boolean isExistsProperty() {
        return existsProperty.get();
    }

    public BooleanProperty existsPropertyProperty() {
        return existsProperty;
    }

    public void setExistsProperty(boolean existsProperty) {
        this.existsProperty.set(existsProperty);
    }

    //endregion

    //region [Constructor]

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

    //endregion

    //region [Methods]

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

        if (!this.existsProperty().getValue()){
            this.existsProperty.setValue(true);
        }
    }

    public void setConsultant(Consultant consultant,Time taskTime, Time breakTime, Time longBreakTime){
        this.email = consultant.email;
        this.name = consultant.name;
        this.officeName = consultant.officeName;
        this.taskTime =taskTime;
        this.breakTime = breakTime;
        this.longBreakTime = longBreakTime;
        this.active = consultant.active;
        this.order = consultant.order;
        this.status = consultant.status;

        if (!this.existsProperty().getValue()){
            this.existsProperty.setValue(true);
        }
    }

    //endregion

}
