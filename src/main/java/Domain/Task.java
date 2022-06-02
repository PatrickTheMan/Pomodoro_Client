package Domain;

import java.sql.Time;

public class Task {

    private int id;
    private String email;
    private int projectId;
    private String name;
    private Time time;
    private boolean taskDone;
    private int order;

    public Task(int id, String email, int projectId, String name, Time time, boolean taskDone, int order) {
        this.id = id;
        this.email = email;
        this.projectId = projectId;
        this.name = name;
        this.time = time;
        this.taskDone = taskDone;
        this.order = order;
    }
}
