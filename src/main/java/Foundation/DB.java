package Foundation;

import Domain.Consultant;
import Domain.Project;
import Domain.Singletons.ConsultantSingleton;
import Domain.Task;
import Domain.WorkDay;
import Foundation.Singletons.DBConnectionSingleton;
import Foundation.Singletons.InformationContainerSingleton;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;

public class DB {

    //region [PomodoroTimes&&WorkDay]

    public void addPomodoro(WorkDay workDay, Consultant consultant, int pomodoroNum) {

        int extraHours=0;
        int extraMinutes=0;
        int extraSeconds=0;

        for (int i = 1; i <= pomodoroNum; i++) {
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

        Timestamp endDateTime=Timestamp.valueOf(workDay.getStartDateTime().toLocalDateTime());
        endDateTime.setYear(workDay.getStartDateTime().getYear());
        endDateTime.setMonth(workDay.getStartDateTime().getMonth());
        endDateTime.setDate(workDay.getStartDateTime().getDate());
        endDateTime.setHours(workDay.getStartDateTime().getHours()+extraHours);
        endDateTime.setMinutes(workDay.getStartDateTime().getMinutes()+extraMinutes);
        endDateTime.setSeconds(workDay.getStartDateTime().getSeconds()+extraSeconds);

        Timestamp startDateTime=Timestamp.valueOf(workDay.getStartDateTime().toLocalDateTime());
        startDateTime.setYear(workDay.getStartDateTime().getYear());
        startDateTime.setMonth(workDay.getStartDateTime().getMonth());
        startDateTime.setDate(workDay.getStartDateTime().getDate());
        startDateTime.setHours(workDay.getStartDateTime().getHours()+extraHours-consultant.getTaskTime().getHours()-(pomodoroNum%4==0 ?
                consultant.getLongBreakTime().getHours() : consultant.getBreakTime().getHours()
                )
        );
        startDateTime.setMinutes(workDay.getStartDateTime().getMinutes()+extraMinutes-consultant.getTaskTime().getMinutes()-(pomodoroNum%4==0 ?
                consultant.getLongBreakTime().getMinutes() : consultant.getBreakTime().getMinutes()
                )
        );
        startDateTime.setSeconds(workDay.getStartDateTime().getSeconds()+extraSeconds-consultant.getTaskTime().getSeconds()-(pomodoroNum%4==0 ?
                consultant.getLongBreakTime().getSeconds() : consultant.getBreakTime().getSeconds()
                )
        );



        System.out.println(Date.from(Instant.now()));
        System.out.println(endDateTime);


        try {
            //

            // Makes a prepared statement with the information and the stored procedure
            PreparedStatement ps = DBConnectionSingleton.getInstance().getConnection().prepareStatement("exec [dbo].[addPomodoro]"+
                    "@FldWorkday = "+ workDay.getId()+","+
                    "@FldStart = '"+ startDateTime + "'," +
                    "@FldEnd = '"+ endDateTime + "'," +
                    "@FldWorkTime = '"+ consultant.getTaskTime() + "',"+
                    "@FldBreaKTime = '"+ (pomodoroNum%4==0 ?
                    consultant.getLongBreakTime() : consultant.getBreakTime()
                    ) + "';"
            );
            // Executes the stored procedure
            ps.executeUpdate();



        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }



    }

    public void clearPomodoros(WorkDay workDay){

        System.out.println("clear pomodoros");

        try {
            // Makes a prepared statement with the information and the stored procedure
            PreparedStatement ps = DBConnectionSingleton.getInstance().getConnection().prepareStatement("exec [dbo].[clearPomodoros]"+
                    "@workdayID = "+workDay.getId());
            // Executes the stored procedure
            ps.executeUpdate();

        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public void updateWorkDay(Consultant consultant, int amountOfPomodoros, Timestamp workDayStart) {

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

        Timestamp endDateTime=Timestamp.valueOf(workDayStart.toLocalDateTime());
        endDateTime.setYear(workDayStart.getYear());
        endDateTime.setMonth(workDayStart.getMonth());
        endDateTime.setDate(workDayStart.getDate());
        endDateTime.setHours(workDayStart.getHours()+extraHours);
        endDateTime.setMinutes(workDayStart.getMinutes()+extraMinutes);
        endDateTime.setSeconds(workDayStart.getSeconds()+extraSeconds);

        System.out.println(Date.from(Instant.now()));
        System.out.println(endDateTime);



        try {

            // Makes a prepared statement with the information and the stored procedure
            PreparedStatement ps = DBConnectionSingleton.getInstance().getConnection().prepareStatement("exec [dbo].[updateWorkDay] "+
                    "@FldConsultant = '"+ConsultantSingleton.getInstance().getEmail()+"',"+
                    "@FldStart = '"+ (
                            (workDayStart.getYear()+1900)+"-"+
                                    (workDayStart.getMonth()+1)+"-"+
                                    workDayStart.getDate()+" "+
                                    workDayStart.getHours()+":"+
                                    workDayStart.getMinutes()+":"+
                                    workDayStart.getSeconds()
                    )+"',"+
                    "@FldEnd = '"+(
                            (endDateTime.getYear()+1900)+"-"+
                                    (endDateTime.getMonth()+1)+"-"+
                                    endDateTime.getDate()+" "+
                                    endDateTime.getHours()+":"+
                                    endDateTime.getMinutes()+":"+
                                    endDateTime.getSeconds()
                    )+"'"
            );
            // Executes the stored procedure
            ps.executeUpdate();

        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }


        InformationContainerSingleton.getInstance().setWorkDay(
                getWorkDay(consultant.getEmail())
        );


    }

    public WorkDay getWorkDay(String email){

        WorkDay workDay = null;

        try {
            // Makes a prepared statement with the information and the stored procedure
            PreparedStatement ps = DBConnectionSingleton.getInstance().getConnection().prepareStatement("exec [dbo].[getWorkday] "+
                    "@consultantEmail = '"+email+"'");
            // Executes the stored procedure
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                workDay = new WorkDay(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getTimestamp(3),
                        rs.getTimestamp(4)
                );
            }

        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }


        return workDay;
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
