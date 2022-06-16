package Domain;

import Foundation.Singletons.ControllerSingleton;
import Domain.Singletons.ConsultantSingleton;
import Domain.Singletons.TimerSingleton;
import Foundation.Singletons.DBSingleton;
import Domain.Singletons.InformationContainerSingleton;
import UI.Structures.SceneStructureParts.SmallParts.NodePages;
import UI.Structures.SceneStructureParts.SmallParts.Projectline;
import UI.Structures.SceneStructureParts.SmallParts.Taskline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;

import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Patrick G. Schemel
 */
public class InformationContainer {

    //region [Variables]

    private NodePages activeNodePage;
    private ArrayList<Taskline> doTodayList;
    private NodePages activeOverviewNodePage;

    private WorkDay workDay;

    private ArrayList<Consultant> consultants;
    private ArrayList<Project> projects;
    private ArrayList<Task> tasks;

    //endregion

    //region [Properties]

    private final IntegerProperty AMOUNT_OF_ACTIVE_POMODOROS = new SimpleIntegerProperty();
    private final BooleanProperty UPDATE_NODEPAGES = new SimpleBooleanProperty(false);

    //endregion

    //region [Normal Getters & Setters]

    public BooleanProperty updateNodePagesProperty() {
        return UPDATE_NODEPAGES;
    }

    public NodePages getActiveNodePage() {return activeNodePage;}

    public void setActiveNodePage(NodePages activeNodePage) {this.activeNodePage = activeNodePage;}

    public NodePages getActiveOverviewNodePage() {
        return activeOverviewNodePage;
    }

    public void setActiveOverviewNodePage(NodePages activeOverviewNodePage) {
        this.activeOverviewNodePage = activeOverviewNodePage;
    }

    public void setDoTodayList(ArrayList<Taskline> doTodayList) {
        this.doTodayList = doTodayList;
    }

    public ArrayList<Consultant> getConsultants() {
        return consultants;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setConsultants(ArrayList<Consultant> consultants) {
        this.consultants = consultants;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getAmountOfActivePomodoros() {
        return AMOUNT_OF_ACTIVE_POMODOROS.get();
    }

    public IntegerProperty amountOfActivePomodorosProperty() {
        return AMOUNT_OF_ACTIVE_POMODOROS;
    }

    public void setAmountOfActivePomodoros(int amountOfActivePomodoros) {
        this.AMOUNT_OF_ACTIVE_POMODOROS.set(amountOfActivePomodoros);
    }

    public boolean isUpdateNodePages() {
        return UPDATE_NODEPAGES.get();
    }

    public void setWorkDay(WorkDay workDay) {
        this.workDay = workDay;
    }

    //endregion

    //region [Special Getters & Setters]

    /**
     * <Strong>Gets all the projects names</Strong>
     * @return an arraylist with all the names
     */
    public ArrayList<String> getProjectNames() {
        ArrayList<String> projectNames = new ArrayList<>();

        for (Project p:this.projects) {
            projectNames.add(p.getName());
        }

        return projectNames;
    }

    /**
     * <Strong>Gets all the tasks names</Strong>
     * @return an arraylist with all the names
     */
    public ArrayList<String> getTaskNames() {
        ArrayList<String> taskNames = new ArrayList<>();

        for (Task t:this.tasks) {
            taskNames.add(t.getName());
        }

        return taskNames;
    }

    /**
     * <Strong>Gets the dotodaylists tasklines as nodes</Strong>
     * @return an arraylist of nodes (converted tasklines)
     */
    public ArrayList<Node> getDoTodayList() {

        ArrayList<Node> doTodayListAsNodes = new ArrayList();

        doTodayListAsNodes.addAll(this.doTodayList);

        return doTodayListAsNodes;
    }

    //endregion

    //region [Constructor]

    public InformationContainer(){
        this.doTodayList = new ArrayList<>();
        this.consultants = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.tasks = new ArrayList<>();

        this.AMOUNT_OF_ACTIVE_POMODOROS.addListener((obs,old,newVal) -> {

            if (ConsultantSingleton.getInstance().exists()){

                if (old.intValue()>newVal.intValue()){
                    //System.out.println("smaller");

                    clearAndRemakePomodoros();

                } else {
                    //System.out.println("larger");

                    addPomodoroToDB(old.intValue(),newVal.intValue());

                }

            }

        });

        updateAll();
    }

    //endregion

    //region [Update]

    /**
     * <Strong>Updates consultants, projects and tasks by getting new data from the DB</Strong>
     */
    public void updateAll(){

        this.consultants.clear();
        this.projects.clear();
        this.tasks.clear();

        this.consultants.addAll(DBSingleton.getInstance().getConsultants());
        this.projects.addAll(DBSingleton.getInstance().getProjects());
        this.tasks.addAll(DBSingleton.getInstance().getTasks());

    }

    /**
     * <Strong>Updates consultants by getting new data from the DB</Strong>
     */
    public void updateConsultants(){
        this.consultants.clear();
        this.consultants.addAll(DBSingleton.getInstance().getConsultants());
    }

    /**
     * <Strong>Updates tasks by getting new data from the DB</Strong>
     */
    public void updateTasks(){
        this.tasks.clear();
        this.tasks.addAll(DBSingleton.getInstance().getTasks());
    }

    /**
     * <Strong>Updates the projects by getting new data from the DB</Strong>
     */
    public void updateProjects(){
        this.projects.clear();
        this.projects.addAll(DBSingleton.getInstance().getProjects());
    }

    //endregion

    //region [DoToday]

    /**
     * <Strong>Add a taskline to the dotoday arraylist</Strong>
     * @param taskline is the taskline that gets added
     */
    public void newTasklineInDoToday(Taskline taskline){
        this.doTodayList.add(taskline);

    }

    /**
     * <Strong>Removes a taskline from the dotoday arraylist</Strong>
     * @param taskline is the taskline that gets removed
     */
    public void removeTasklineInDoToday(Taskline taskline){
        this.doTodayList.remove(taskline);
    }

    /**
     * <Strong>Clears the dotoday arraylist and sets the amount of activepomodoros to 0</Strong>
     */
    public void clearDoTodayList(){

        for (int i = 0; i <= this.doTodayList.size()-1; i++) {
            // Remove this node from active nodepage
            InformationContainerSingleton.getInstance().getActiveNodePage().removeNode(this.doTodayList.get(i));

            this.doTodayList.remove(this.doTodayList.get(i));
        }
        this.AMOUNT_OF_ACTIVE_POMODOROS.setValue(0);

    }

    /**
     * <Strong>Go to the next task, update the information of the timer and the last tasks DB tasktimetaken</Strong>
     */
    public void nextTask(){

        // If there is a task in the doTodayList
        if (this.doTodayList.size()>0 && this.doTodayList.get(0).getTaskID()!=0){

            if (ConsultantSingleton.getInstance().exists()){
                Task currentTask = InformationContainerSingleton.getInstance().getTask(this.doTodayList.get(0).getTaskID());

                int hh = (currentTask.getTime().getHours() -
                        TimerSingleton.getInstance().getTaskOffsetTime().getHours())
                        + TimerSingleton.getInstance().getCurrentTimeSpent().getHours();
                int mm = (currentTask.getTime().getMinutes() -
                        TimerSingleton.getInstance().getTaskOffsetTime().getMinutes())
                        + TimerSingleton.getInstance().getCurrentTimeSpent().getMinutes();
                int ss = (currentTask.getTime().getSeconds() -
                        TimerSingleton.getInstance().getTaskOffsetTime().getSeconds())
                        + TimerSingleton.getInstance().getCurrentTimeSpent().getSeconds();

                // The Task is updated in the DB
                ControllerSingleton.getInstance().updateTaskDB(new Task(
                                currentTask.getId(),
                                currentTask.getEmail(),
                                (currentTask.getProjectId()==0 ? null : currentTask.getProjectId()),
                                currentTask.getName(),
                                Time.valueOf(hh+":"+mm+":"+ss),
                                currentTask.isTaskDone(),
                                currentTask.getOrder()
                        )
                );
            }

            // Change the pomodoroTimer or remove the task from doToday (does not have to be finished)
            if (Integer.parseInt(this.doTodayList.get(0).getCounter().getLabel().getText())==1){
                // Update the pomodoro counter on the taskline
                this.doTodayList.get(0).getCounter().getLabel().setText(""+
                        (Integer.parseInt(this.doTodayList.get(0).getCounter().getLabel().getText())-1)
                );
                // Update nodePages
                this.UPDATE_NODEPAGES.setValue(true);
                // Remove taskline for that task in dotoday
                this.doTodayList.remove(0);
            } else {
                // Update the pomodoro counter on the taskline
                this.doTodayList.get(0).getCounter().getLabel().setText(""+
                        (Integer.parseInt(this.doTodayList.get(0).getCounter().getLabel().getText())-1)
                );
                // Update nodePages
                this.UPDATE_NODEPAGES.setValue(true);
            }

            //Update the activePomodoros
            this.setAmountOfActivePomodoros(this.getAmountOfActivePomodoros()-1);

        }

    }

    /**
     * <Strong>Gets the tasks associated with the project chosen</Strong>
     * @param project is the chosen project
     */
    public void getTasksFromProjectToList(Project project){

        // Add the tasks to the view
        ArrayList<Node> overviewTaskContent = new ArrayList<>();

        Projectline backLine = new Projectline("");
        overviewTaskContent.add(backLine);

        for (Task t:InformationContainerSingleton.getInstance().getTasks()) {
            if (t.getProjectId()==project.getId()){
                overviewTaskContent.add(new Taskline(t));
            }
        }

        // Add tasklines for that project
        this.activeOverviewNodePage.setNodes(overviewTaskContent);

        // Update
        this.activeOverviewNodePage.updatePageContent(false);
        this.activeOverviewNodePage.updatePageButtons();

    }

    /**
     * <Strong>Get all the projects as projectlines</Strong>
     */
    public void getProjectsToList(){

        // Add the projectlines to the view
        ArrayList<Node> overviewProjectContent = new ArrayList<>();
        for (Project p:InformationContainerSingleton.getInstance().getProjects()) {
            overviewProjectContent.add(new Projectline(p));
        }

        // Set the content
        this.activeOverviewNodePage.setNodes(overviewProjectContent);

        // Update
        this.activeOverviewNodePage.updatePageContent(false);
        this.activeOverviewNodePage.updatePageButtons();

    }

    //endregion

    //region [Pomodoro&&WorkDay-DB]

    /**
     * <Strong>Clear and remake all DB pomodoros</Strong>
     */
    public void clearAndRemakePomodoros(){

        // Remove the pomodoros thats not in use
        DBSingleton.getInstance().clearPomodoros(this.workDay);

        addPomodoroToDB(0,this.AMOUNT_OF_ACTIVE_POMODOROS.getValue());

    }

    /**
     * <Strong>Add a pomodoro to the DB with the right values</Strong>
     * @param old is the old amount of pomodoros
     * @param newVal is the new amount of pomodoros
     */
    public void addPomodoroToDB(Integer old, Integer newVal){

        Timestamp newestWorkDay = null;
        if (DBSingleton.getInstance().getWorkDay(ConsultantSingleton.getInstance().getEmail())!=null){
            newestWorkDay = DBSingleton.getInstance().getWorkDay(ConsultantSingleton.getInstance().getEmail()).getStartDateTime();
        }
        Timestamp today = Timestamp.valueOf(LocalDateTime.now());
        Timestamp yesterday = Timestamp.valueOf(today.toLocalDateTime());
        yesterday.setHours(yesterday.getHours()-12);

        boolean remakeDB = false;

        // Update or create the workday
        if (this.workDay!=null){
            DBSingleton.getInstance().updateWorkDay(ConsultantSingleton.getInstance(),this.AMOUNT_OF_ACTIVE_POMODOROS.getValue(),this.workDay.getStartDateTime());
        } else {
            if (newestWorkDay!=null && BigInteger.valueOf(newestWorkDay.getTime()).compareTo(BigInteger.valueOf(yesterday.getTime()))==1){
                remakeDB = true;
            } else {
                DBSingleton.getInstance().updateWorkDay(ConsultantSingleton.getInstance(),this.AMOUNT_OF_ACTIVE_POMODOROS.getValue(), today);
            }
        }
        this.workDay = DBSingleton.getInstance().getWorkDay(ConsultantSingleton.getInstance().getEmail());

        if (remakeDB){
            clearAndRemakePomodoros();
        } else {
            // Add the pomodoros
            for (int i = old.intValue()+1; i < newVal.intValue()+1 ; i++) {
                if (i % 4 == 0){
                    DBSingleton.getInstance().addPomodoro(this.workDay,
                            ConsultantSingleton.getInstance(),
                            i);
                } else {
                    DBSingleton.getInstance().addPomodoro(this.workDay,
                            ConsultantSingleton.getInstance(),
                            i);
                }
            }
        }
    }

    //endregion

    //region [GetSpecific]

    /**
     * <Strong>Gets the project with the right project id</Strong>
     * @param projectId is the id searched after
     * @return the project searched for
     */
    public Project getProject(int projectId){
        for (Project p:this.projects) {
            if (p.getId()==projectId){
                return p;
            }
        }
        return null;
    }

    /**
     * <Strong>Gets the project with the right project name</Strong>
     * @param s is the name searched after
     * @return the project searched for
     */
    public Project getProject(String s){
        Project lastAddedProject=null;
        for (Project p:this.projects) {
            if (p.getName().equals(s)){
                lastAddedProject=p;
            }
        }
        return lastAddedProject;
    }

    /**
     * <Strong>Gets the task with the right task id</Strong>
     * @param taskId is the id searched after
     * @return the task searched for
     */
    public Task getTask(int taskId){
        for (Task t:this.tasks) {
            if (t.getId()==taskId){
                return t;
            }
        }
        return null;
    }

    /**
     * <Strong>Gets the task with the right task name</Strong>
     * @param taskName is the name searched after
     * @return the task searched for
     */
    public Task getTask(String taskName){
        Task lastAddedTask=null;
        for (Task t:this.tasks) {
            if (t.getName().equals(taskName)){
                lastAddedTask=t;
            }
        }
        return lastAddedTask;
    }

    //endregion

    //region [Convertions]

    /**
     * <Strong>Gets all the tasks names associated with the chosen project</Strong>
     * @param project is the project the tasks are associated with
     * @return the task names in an arraylist
     */
    public ArrayList<String> getTaskNamesFromProject(Project project){

        ArrayList<String> task = new ArrayList<>();

        for (Task t:this.tasks) {
            if (t.getProjectId()==project.getId()){
                task.add(t.getName());
            }
        }

        return task;
    }

    /**
     * <Strong>Gets all the now finished tasks names for a chosen project</Strong>
     * @param project is the project the tasks are associated with
     * @return the task names in an arraylist
     */
    public ArrayList<String> getAvailableTaskNamesFromProject(Project project){

        ArrayList<String> task = new ArrayList<>();

        if (project != null){
            // Normal operation, add all tasks from the given project to the arraylist
            for (Task t:this.tasks) {
                if (t.getProjectId()==project.getId() && !t.isTaskDone()){
                    task.add(t.getName());
                }
            }
        } else {
            // If the no projects are choosen, then the users own personal tasks will be shown
            for (Task t:this.tasks) {
                if (t.getEmail().equals(ConsultantSingleton.getInstance().getEmail()) && t.getProjectId()==0 && !t.isTaskDone()){
                    task.add(t.getName());
                }
            }
        }

        return task;
    }

    /**
     * <Strong>Gets the projects names</Strong>
     * @return an arraylist with the names
     */
    public ArrayList<String> getProjectsToNames(){

        ArrayList<String> names = new ArrayList<>();

        for (Project p:this.projects) {
            names.add(p.getName());
        }

        return names;
    }

    /**
     * <Strong>Gets the consultant with the given name</Strong>
     * @param s is the name searched after
     * @return the consultant with the right name
     */
    public Consultant getConsultant(String s){
        for (Consultant c:this.consultants) {
            if (c.getName().equals(s)){
                return c;
            }
        }
        return null;
    }

    //endregion

}
