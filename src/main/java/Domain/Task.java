package Domain;

import java.sql.Time;

/**
 * @author Patrick G. Schemel
 */
public class Task {

    //region [Variables]

    private int id;
    private String email;
    private Integer projectId;
    private String name;
    private Time time;
    private boolean taskDone;
    private int order;

    //endregion

    //region [Normal Getters & Setters]

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public Time getTime() {
        return time;
    }

    public boolean isTaskDone() {
        return taskDone;
    }

    public int getOrder() {
        return order;
    }

    //endregion

    //region [Constructor]

    public Task(int id, String email, Integer projectId, String name, Time time, boolean taskDone, int order) {
        this.id = id;
        this.email = email;
        this.projectId = projectId;
        this.name = name;
        this.time = time;
        this.taskDone = taskDone;
        this.order = order;
    }

    //endregion

}
