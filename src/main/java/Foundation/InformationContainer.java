package Foundation;

import Application.Singleton.ControllerSingleton;
import Domain.Consultant;
import Domain.Project;
import Domain.Singletons.ConsultantSingleton;
import Domain.Singletons.TimerSingleton;
import Domain.Task;
import Foundation.Singletons.DBSingleton;
import Foundation.Singletons.InformationContainerSingleton;
import UI.Structures.SceneStructureParts.SmallParts.NodePages;
import UI.Structures.SceneStructureParts.SmallParts.Projectline;
import UI.Structures.SceneStructureParts.SmallParts.Taskline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;

import java.sql.Time;
import java.util.ArrayList;

public class InformationContainer {

    private NodePages activeNodePage;
    private ArrayList<Taskline> doTodayList;
    private NodePages activeOverviewNodePage;

    private ArrayList<Consultant> consultants;
    private ArrayList<Project> projects;
    private ArrayList<Task> tasks;

    private BooleanProperty updateNodePages = new SimpleBooleanProperty(false);



    public BooleanProperty updateNodePagesProperty() {
        return updateNodePages;
    }

    public NodePages getActiveNodePage() {return activeNodePage;}

    public void setActiveNodePage(NodePages activeNodePage) {this.activeNodePage = activeNodePage;}

    public NodePages getActiveOverviewNodePage() {
        return activeOverviewNodePage;
    }

    public void setActiveOverviewNodePage(NodePages activeOverviewNodePage) {
        this.activeOverviewNodePage = activeOverviewNodePage;
    }


    public ArrayList<Node> getDoTodayList() {

        ArrayList<Node> doTodayListAsNodes = new ArrayList();

        doTodayListAsNodes.addAll(this.doTodayList);

        return doTodayListAsNodes;
    }


    public ArrayList<Consultant> getConsultants() {
        return consultants;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public ArrayList<String> getProjectNames() {
        ArrayList<String> projectNames = new ArrayList<>();

        for (Project p:this.projects) {
            projectNames.add(p.getName());
        }

        return projectNames;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<String> getTaskNames() {
        ArrayList<String> taskNames = new ArrayList<>();

        for (Task t:this.tasks) {
            taskNames.add(t.getName());
        }

        return taskNames;
    }

    public InformationContainer(){
        this.doTodayList = new ArrayList<>();
        this.consultants = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.tasks = new ArrayList<>();

        updateAll();
    }


    public void updateAll(){

        System.out.println("update-all-method");

        this.consultants.clear();
        this.projects.clear();
        this.tasks.clear();

        this.consultants.addAll(DBSingleton.getInstance().getConsultants());
        this.projects.addAll(DBSingleton.getInstance().getProjects());
        this.tasks.addAll(DBSingleton.getInstance().getTasks());
    }

    public void updateConsultants(){
        System.out.println("update-consultants-method");
        this.consultants.clear();
        this.consultants.addAll(DBSingleton.getInstance().getConsultants());
    }

    public void updateTasks(){
        System.out.println("update-tasks-method");
        this.tasks.clear();
        this.tasks.addAll(DBSingleton.getInstance().getTasks());
    }

    public void updateProjects(){
        System.out.println("update-projects-method");
        this.projects.clear();
        this.projects.addAll(DBSingleton.getInstance().getProjects());
    }



    //region [DoToday]

    public void newTasklineInDoToday(Taskline taskline){
        this.doTodayList.add(taskline);

    }

    public void removeTasklineInDoToday(Taskline taskline){
        this.doTodayList.remove(taskline);
    }

    public void nextTask(){

        // If there is a task in the doTodayList
        if (this.doTodayList.size()>0 && this.doTodayList.get(0).getTaskID()!=0){

            if (ConsultantSingleton.getInstance().exists()){
                Task currentTask = InformationContainerSingleton.getInstance().getTask(this.doTodayList.get(0).getTaskID());

                // The Task is updated in the DB
                ControllerSingleton.getInstance().updateTaskDB(new Task(
                                currentTask.getId(),
                                currentTask.getEmail(),
                                (currentTask.getProjectId()==0 ? null : currentTask.getProjectId()),
                                currentTask.getName(),
                                Time.valueOf(
                                        (currentTask.getTime().getHours() + TimerSingleton.getInstance().getCurrentTimeSpent().getHours()) + ":" +
                                                (currentTask.getTime().getMinutes() + TimerSingleton.getInstance().getCurrentTimeSpent().getMinutes()) + ":" +
                                                (currentTask.getTime().getSeconds() + TimerSingleton.getInstance().getCurrentTimeSpent().getSeconds())
                                ),
                                currentTask.isTaskDone(),
                                0
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
                this.updateNodePages.setValue(true);
                // Remove taskline for that task in dotoday
                this.doTodayList.remove(0);
            } else {
                // Update the pomodoro counter on the taskline
                this.doTodayList.get(0).getCounter().getLabel().setText(""+
                        (Integer.parseInt(this.doTodayList.get(0).getCounter().getLabel().getText())-1)
                );
                // Update nodePages
                this.updateNodePages.setValue(true);
            }

        }

    }

    public void getTasksFromProjectToList(Project project){

        // Add the tasks to the view
        ArrayList<Node> overviewTaskContent = new ArrayList<>();

        Projectline backLine = new Projectline("Back");
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

    //region [GetSpecific]

    public Project getProject(int projectId){
        for (Project p:this.projects) {
            if (p.getId()==projectId){
                return p;
            }
        }
        return null;
    }

    public Project getProject(String s){
        Project lastAddedProject=null;
        for (Project p:this.projects) {
            if (p.getName().equals(s)){
                lastAddedProject=p;
            }
        }
        return lastAddedProject;
    }

    public Task getTask(int taskId){
        for (Task t:this.tasks) {
            if (t.getId()==taskId){
                return t;
            }
        }
        return null;
    }

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
     *
     * @return
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
     *
     * @return
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
     *
     * @return
     */
    public ArrayList<String> getProjectsToNames(ArrayList<Project> projects){

        ArrayList<String> names = new ArrayList<>();

        for (Project p:projects) {
            names.add(p.getName());
        }

        return names;
    }

    /**
     *
     * @param s
     * @return
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
