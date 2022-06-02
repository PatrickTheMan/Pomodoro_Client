package Testing;

import Domain.Consultant;
import Domain.Project;
import Domain.Singletons.ConsultantSingleton;
import Domain.Task;

import java.sql.Time;
import java.util.ArrayList;

public class Testing {

    public void loadTestConsultant(){

        ConsultantSingleton.getInstance().setConsultant(new Consultant(
                        "Jhon@Nielsen.com",
                        "Jhon Nielsen",
                        "Main Office",
                        Time.valueOf("00:30:00"),
                        Time.valueOf("00:05:00"),
                        Time.valueOf("00:40:00"),
                        true,
                        1
                )
        );

    }

    /**
     *
     * @return
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
                        3
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
                        3
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
                        2
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
                        4
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
                        4
                )
        );

        return consultants;
    }

    /**
     *
     * @return
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

    /**
     *
     * @return
     */
    public ArrayList<Task> getTestTasksForTestProjects(){

        ArrayList<Task> projects = new ArrayList<>();




        return projects;
    }

}
