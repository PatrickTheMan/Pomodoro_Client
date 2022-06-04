package Foundation;

import Domain.Consultant;
import Domain.Project;
import Domain.Singletons.ConsultantSingleton;
import Domain.Task;
import Foundation.Singletons.DBSingleton;
import UI.Structures.SceneStructureParts.SmallParts.Taskline;
import javafx.scene.Node;

import java.util.ArrayList;

public class InformationContainer {

    private ArrayList<Node> doTodayList;

    private ArrayList<Consultant> consultants;
    private ArrayList<Project> projects;
    private ArrayList<Task> tasks;


    public void setDoTodayList(ArrayList<Node> doTodayList) {
        this.doTodayList = doTodayList;
    }

    public ArrayList<Node> getDoTodayList() {
        return doTodayList;
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

        System.out.println("update-method");

        this.consultants.clear();
        this.projects.clear();
        this.tasks.clear();

        this.consultants.addAll(DBSingleton.getInstance().getConsultants());
        this.projects.addAll(DBSingleton.getInstance().getProjects());
        this.tasks.addAll(DBSingleton.getInstance().getTasks());
    }



    //region [DoToday]

    public void newTasklineInDoToday(Taskline taskline){
        this.doTodayList.add(taskline);

    }

    public void removeTasklineInDoToday(Taskline taskline){
        this.doTodayList.remove(taskline);
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
    public ArrayList<String> taskNamesFromProject(Project project){

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
    public ArrayList<String> availableTaskNamesFromProject(Project project){

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
    public ArrayList<String> projectsToNames(ArrayList<Project> projects){

        ArrayList<String> names = new ArrayList<>();

        for (Project p:projects) {
            names.add(p.getName());
        }

        return names;
    }

    //endregion

}
