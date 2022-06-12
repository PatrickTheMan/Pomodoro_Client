package Testing;

import Domain.Consultant;
import Domain.Project;
import Domain.Singletons.ConsultantSingleton;
import Domain.Task;

import java.sql.Time;
import java.util.ArrayList;

/**
 * @author Patrick G. Schemel
 */
public class Testing {

    //region [Consultant]

    /**
     * <Strong>Loads a test consultant to the consultantsingleton</Strong>
     */
    public void loadTestConsultant(){

        ConsultantSingleton.getInstance().setConsultant(new Consultant(
                "Jhon@Nielsen.com",
                "Jhon Nielsen",
                "Main Office",
                Time.valueOf("00:30:00"),
                Time.valueOf("00:05:00"),
                Time.valueOf("00:40:00"),
                true,
                1,
                ""
                )
        );

    }

    /**
     * <Strong>Gets the test consultants</Strong>
     * @return an arraylist with test consultants
     */
    public ArrayList<Consultant> getTestConsultants(){

        ArrayList<Consultant> consultants = new ArrayList<>();

        consultants.add(new Consultant(
                "Lea@Nielsen.com",
                "Lea Nielsen",
                "Main Office",
                Time.valueOf("00:30:00"),
                Time.valueOf("00:10:00"),
                Time.valueOf("00:20:00"),
                true,
                3,
                ""
                )
        );
        consultants.add(new Consultant(
                "Jeff@Jhonson.com",
                "Jeff Jhonson",
                "Main Office",
                Time.valueOf("00:25:00"),
                Time.valueOf("00:10:00"),
                Time.valueOf("00:15:00"),
                true,
                3,
                ""
                )
        );
        consultants.add(new Consultant(
                "Tom@Christensen.com",
                "Tom Christensen",
                "Main Office",
                Time.valueOf("00:60:00"),
                Time.valueOf("00:20:00"),
                Time.valueOf("00:40:00"),
                true,
                2,
                ""
                )
        );
        consultants.add(new Consultant(
                "Liam@Arnolsen.com",
                "Liam Arnolsen",
                "Main Office",
                Time.valueOf("00:15:00"),
                Time.valueOf("00:03:00"),
                Time.valueOf("00:15:00"),
                true,
                4,
                ""
                )
        );
        consultants.add(new Consultant(
                "Bo@Schnell.com",
                "Bo Schnell",
                "Main Office",
                Time.valueOf("00:25:00"),
                Time.valueOf("00:05:00"),
                Time.valueOf("00:30:00"),
                true,
                4,
                ""
                )
        );

        return consultants;
    }

    //endregion

    //region [Projects]

    /**
     * <Strong>Gets the test projects</Strong>
     * @return an arraylist of test projects
     */
    public ArrayList<Project> getTestProjects(){

        ArrayList<Project> projects = new ArrayList<>();

        projects.add(new Project(12,"3rd Project",3));
        projects.add(new Project(4,"1st Project",1));
        projects.add(new Project(23,"2nd Project",2));
        projects.add(new Project(5,"5th Project",5));
        projects.add(new Project(3,"4th Project",4));

        return projects;
    }

    //endregion

    //region [Task]

    /**
     * <Strong>Gets the tasks for the test projects</Strong>
     * @return an arraylist of test tasks
     */
    public ArrayList<Task> getTestTasksForTestProjects(){

        ArrayList<Task> tasks = new ArrayList<>();

        tasks.add(new Task(1,"Bo@Schnell.com",3,"Do laundry",Time.valueOf("00:00:00"),false,1));
        tasks.add(new Task(2,"Bo@Schnell.com",3,"Do laundry2",Time.valueOf("00:00:00"),false,2));
        tasks.add(new Task(3,"Bo@Schnell.com",3,"Do laundry3",Time.valueOf("00:00:00"),false,3));
        tasks.add(new Task(4,"Bo@Schnell.com",3,"Do laundry4",Time.valueOf("00:00:00"),false,4));
        tasks.add(new Task(5,"Bo@Schnell.com",5,"Do laundry5",Time.valueOf("00:00:00"),false,5));

        return tasks;
    }

    //endregion

}
