package Domain;

import java.sql.Timestamp;

/**
 * @author Patrick G. Schemel
 */
public class WorkDay {

    //region [Variables]

    private int id;
    private String email;
    private Timestamp startDateTime;
    private Timestamp endDateTime;

    //endregion

    //region [Normal Getters & Setters]

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Timestamp getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }

    //endregion

    //region [Constructor]

    public WorkDay(int id, String email, Timestamp startDateTime, Timestamp endDateTime){
        this.id=id;
        this.email=email;
        this.startDateTime=startDateTime;
        this.endDateTime=endDateTime;
    }

    //endregion

}
