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
    private Time workTime;
    private Time breakTime;
    private Time longBreakTime;
    private boolean active;
    private int order;
    private String status;

    //endregion

    //region [Properties]

    private final StringProperty NAME = new SimpleStringProperty("");
    private final BooleanProperty EXISTS = new SimpleBooleanProperty(false);

    public StringProperty nameProperty() {return NAME;}
    public BooleanProperty existsProperty() {return EXISTS;}

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

    public Time getWorkTime() {
        return workTime;
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

    public void setWorkTime(Time workTime) {
        this.workTime = workTime;
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
        return NAME.get();
    }

    public StringProperty namePropertyProperty() {
        return NAME;
    }

    public void setNameProperty(String nameProperty) {
        this.NAME.set(nameProperty);
    }

    public boolean isExistsProperty() {
        return EXISTS.get();
    }

    public BooleanProperty existsPropertyProperty() {
        return EXISTS;
    }

    public void setExistsProperty(boolean existsProperty) {
        this.EXISTS.set(existsProperty);
    }

    //endregion

    //region [Constructor]

    public Consultant(String email, String name, String officeName, Time workTime, Time breakTime, Time longBreakTime, boolean active, int order, String status) {
        this.email = email;
        this.name = name;
        this.officeName = officeName;
        this.workTime = workTime;
        this.breakTime = breakTime;
        this.longBreakTime = longBreakTime;
        this.active = active;
        this.order = order;
        this.status = status;

        this.NAME.set(name);
    }

    //endregion

    //region [Methods]

    public boolean exists(){
        return EXISTS.getValue();
    }

    public void setConsultant(Consultant consultant){
        this.email = consultant.email;
        this.name = consultant.name;
        this.officeName = consultant.officeName;
        this.workTime = consultant.workTime;
        this.breakTime = consultant.breakTime;
        this.longBreakTime = consultant.longBreakTime;
        this.active = consultant.active;
        this.order = consultant.order;
        this.status = consultant.status;

        if (!this.existsProperty().getValue()){
            this.EXISTS.setValue(true);
        }
    }

    public void setConsultant(Consultant consultant,Time workTime, Time breakTime, Time longBreakTime){
        this.email = consultant.email;
        this.name = consultant.name;
        this.officeName = consultant.officeName;
        this.workTime =workTime;
        this.breakTime = breakTime;
        this.longBreakTime = longBreakTime;
        this.active = consultant.active;
        this.order = consultant.order;
        this.status = consultant.status;

        if (!this.existsProperty().getValue()){
            this.EXISTS.setValue(true);
        }
    }

    //endregion

}
