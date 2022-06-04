package Foundation;

import Domain.Consultant;
import Domain.Project;
import Domain.Task;
import Foundation.Singletons.DBConnectionSingleton;
import Foundation.Singletons.InformationContainerSingleton;

import java.sql.*;
import java.util.ArrayList;

public class DB {

    //region [Tasks]

    /**
     *
     * @return
     */
    public ArrayList<Task> getTasks(){

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            // Makes a prepared statement with the information and the stored procedure
            PreparedStatement ps = DBConnectionSingleton.getInstance().getConnection().prepareStatement("exec [dbo].[getTasks]");
            // Executes the stored procedure
            ResultSet rs = ps.executeQuery();

            // Adds the tasks
            while (rs.next()){

                tasks.add(new Task(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getTime(5),
                        rs.getBoolean(6),
                        rs.getInt(7)
                        )
                );
            }

            return tasks;

        }catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     *
     * @return
     */
    public void updateTask(Task task){

        try {
            // Makes a prepared statement with the information and the stored procedure
            PreparedStatement ps = DBConnectionSingleton.getInstance().getConnection().prepareStatement("exec [dbo].[updatingTask] "+
                    "@Fldid = "+task.getId()+", "+
                    "@FldConsultant_email = '"+task.getEmail()+"', "+
                    "@FldProject_id = "+task.getProjectId()+", "+
                    "@FldTask_name = '"+task.getName()+"', "+
                    "@FldTask_time_spent = '"+task.getTime()+"', "+
                    "@FldTask_done ="+task.isTaskDone()+", "+
                    "@FldOrder = "+task.getOrder()+
                    ";");
            // Executes the stored procedure
            ps.executeUpdate();

            // Update the information in the InformationContainer
            InformationContainerSingleton.getInstance().updateAll();

        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    //endregion

    //region [Projects]

    /**
     *
     * @return
     */
    public ArrayList<Project> getProjects(){

        ArrayList<Project> projects = new ArrayList<>();

        try {
            // Makes a prepared statement with the information and the stored procedure
            PreparedStatement ps = DBConnectionSingleton.getInstance().getConnection().prepareStatement("exec [dbo].[getProjects]");
            // Executes the stored procedure
            ResultSet rs = ps.executeQuery();

            // Adds the projects
            while (rs.next()){

                projects.add(new Project(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                        )
                );
            }

            return projects;

        }catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    //endregion

    //region [Consultants]

    /**
     *
     * @return
     */
    public ArrayList<Consultant> getConsultants(){

        ArrayList<Consultant> consultants = new ArrayList<>();

        try {
            // Makes a prepared statement with the information and the stored procedure
            PreparedStatement ps = DBConnectionSingleton.getInstance().getConnection().prepareStatement("exec [dbo].[Get_Consultants]");
            // Executes the stored procedure
            ResultSet rs = ps.executeQuery();

            // Adds the projects
            while (rs.next()){

                consultants.add(new Consultant(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(4),
                        rs.getTime(5),
                        rs.getTime(6),
                        rs.getTime(7),
                        rs.getBoolean(8),
                        rs.getInt(3),
                        rs.getString(9)
                        )
                );
            }

            return consultants;

        }catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    //







}
