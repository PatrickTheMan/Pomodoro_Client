package Foundation;

import Domain.Consultant;
import Domain.Project;
import Domain.Singletons.ConsultantSingleton;
import Domain.Singletons.TimerSingleton;
import Domain.Task;
import Foundation.Singletons.DBConnectionSingleton;
import Foundation.Singletons.InformationContainerSingleton;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class DB {

    //region [PomodoroTimes&&WorkDay]

    public void addPomodoro(Time taskTime, Time breakTime){

        //TODO

        System.out.println("add pomodoro");
        System.out.println("TaskTime: "+taskTime+" / BreakTime: "+breakTime);

        /*
        try {
            //
            Date endDateTime = Date.from(Instant.parse(Date.from(Instant.now()).getYear()+"/"+
                    Date.from(Instant.now()).getMonth()+"/"+
                    Date.from(Instant.now()).getDay()+"/"+
                    Date.from(Instant.now()).getHours()+":"+
                    Date.from(Instant.now()).getMinutes()+":"+
                    Date.from(Instant.now()).getSeconds()
                    )
            );

            // Makes a prepared statement with the information and the stored procedure
            PreparedStatement ps = DBConnectionSingleton.getInstance().getConnection().prepareStatement("exec [dbo].[]"+
                    "@FldStart = '"+ Date.from(Instant.now()) + "'," +
                    "@FldEnd = '"+ endDateTime + "'," +
                    "@FldWork_time = '"+ taskTime + "',"+
                    "@FldBreak_time = '"+ breakTime + "';"
            );
            // Executes the stored procedure
            ps.executeUpdate();

        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }

         */

    }

    public void removePomodoro(String email){

        //TODO

        System.out.println("remove pomodoro");

        /*
        try {
            // Makes a prepared statement with the information and the stored procedure
            PreparedStatement ps = DBConnectionSingleton.getInstance().getConnection().prepareStatement("exec [dbo].[]"+
                    "@FldConsultant = "+email);
            // Executes the stored procedure
            ps.executeUpdate();

        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }

         */

    }

    public void clearPomodoros(String email){

        //TODO

        System.out.println("clear pomodoros");

        /*
        try {
            // Makes a prepared statement with the information and the stored procedure
            PreparedStatement ps = DBConnectionSingleton.getInstance().getConnection().prepareStatement("exec [dbo].[]"+
                    "@FldConsultant = "+email);
            // Executes the stored procedure
            ps.executeUpdate();

        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }

         */

    }

    public void updateWorkDay(Consultant consultant, int amountOfPomodoros, Date startTime) throws ParseException {

        //TODO

        System.out.println("update work day");

        int extraHours=0;
        int extraMinutes=0;
        int extraSeconds=0;

        for (int i = 1; i <= amountOfPomodoros; i++) {
            if (i==4){
                extraHours += (consultant.getTaskTime().getHours()+consultant.getLongBreakTime().getHours());
                extraMinutes += (consultant.getTaskTime().getMinutes()+consultant.getLongBreakTime().getMinutes());
                extraSeconds += (consultant.getTaskTime().getSeconds()+consultant.getLongBreakTime().getSeconds());
            } else {
                extraHours += (consultant.getTaskTime().getHours()+consultant.getBreakTime().getHours());
                extraMinutes += (consultant.getTaskTime().getMinutes()+consultant.getBreakTime().getMinutes());
                extraSeconds += (consultant.getTaskTime().getSeconds()+consultant.getBreakTime().getSeconds());
            }
        }

        System.out.println("hh"+extraHours);
        System.out.println("mm"+extraMinutes);
        System.out.println("ss"+extraSeconds);

        Date endDateTime;

        if (startTime==null){
            endDateTime= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(
                    (Date.from(Instant.now()).getYear()+1900)+"/"+
                            (Date.from(Instant.now()).getMonth()+1)+"/"+
                            Date.from(Instant.now()).getDate()+" "+
                            (Date.from(Instant.now()).getHours()+extraHours)+":"+
                            (Date.from(Instant.now()).getMinutes()+extraMinutes)+":"+
                            (Date.from(Instant.now()).getSeconds()+extraSeconds)
            );
        } else {
            endDateTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(
                    (startTime.getYear()+1900)+"/"+
                            (startTime.getMonth()+1)+"/"+
                            startTime.getDate()+" "+
                            (startTime.getHours()+extraHours)+":"+
                            (startTime.getMinutes()+extraMinutes)+":"+
                            (startTime.getSeconds()+extraSeconds)
            );
            InformationContainerSingleton.getInstance().setWorkDayStart(Date.from(Instant.now()));
        }


        System.out.println(Date.from(Instant.now()));
        System.out.println(endDateTime);

        /*
        try {
            // Makes a prepared statement with the information and the stored procedure
            PreparedStatement ps = DBConnectionSingleton.getInstance().getConnection().prepareStatement("exec [dbo].[]"+
                    "@email = "+ConsultantSingleton.getInstance().getEmail());
            // Executes the stored procedure
            ps.executeUpdate();

        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }

         */

    }

    public void removeWorkDay(String email){

        //TODO

        System.out.println("remove workday");

        /*
        try {
            // Makes a prepared statement with the information and the stored procedure
            PreparedStatement ps = DBConnectionSingleton.getInstance().getConnection().prepareStatement("exec [dbo].[]"+
                    "@FldConsultant = "+email);
            // Executes the stored procedure
            ps.executeUpdate();

        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }

         */

    }

    //endregion

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
     * @param task
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

    /**
     *
     * @param consultant
     */
    public void updateConsultant(Consultant consultant){

        try {
            // Makes a prepared statement with the information and the stored procedure
            PreparedStatement ps = DBConnectionSingleton.getInstance().getConnection().prepareStatement("exec [dbo].[updatingConsultant] "+
                    "@FldOrder = "+consultant.getOrder()+", "+
                    "@FldConsultant_name = '"+consultant.getName()+"', "+
                    "@FldConsultant_email = '"+consultant.getEmail()+"', "+
                    "@FldOffice_name = '"+consultant.getOfficeName()+"', "+
                    "@FldPreferred_pomodoro_work_time = '"+consultant.getTaskTime()+"', "+
                    "@FldPreferred_pomodoro_break_time = '"+consultant.getBreakTime()+"', "+
                    "@FldPreferred_pomodoro_long_break_time = '"+consultant.getLongBreakTime()+"',"+
                    "@FldActive = "+consultant.isActive()+
                    ";");
            // Executes the stored procedure
            ps.executeUpdate();

        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }


    //







}
