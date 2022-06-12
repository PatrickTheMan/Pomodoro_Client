package Testing;

import Domain.Project;
import Domain.Singletons.ConsultantSingleton;
import Domain.Singletons.TimerSingleton;
import Domain.Task;
import Foundation.Singletons.InformationContainerSingleton;
import Testing.Singletons.TestingSingleton;
import UI.Structures.SceneStructureParts.SmallParts.Taskline;
import org.junit.*;

import java.sql.Time;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Patrick G. Schemel
 */
public class UnitTesting {

    @BeforeClass
    public static void StartTesting(){
        System.out.println("Start testing ...");
    }

    @AfterClass
    public static void EndTesting(){
        System.out.print("... Done testing");
    }

    @Before
    public void BeforeTest() throws Exception {
        System.out.println("... Next test");
    }

    @After
    public void AfterTest() throws Exception{
        System.out.println("... No unexpected errors");
    }




    @Test
    public void SetConsutlantTest(){
        //Testing a EQ (Equivalense portition)
        System.out.println("Testing for if the consultant has been set, with the test consultant");

        TestingSingleton.getInstance().loadTestConsultant();

        String expected = "Jhon Nielsen";
        String actual = ConsultantSingleton.getInstance().getName();
        assertEquals(expected,actual);
    }

    @Test
    public void SetAndGetTasks(){
        //Testing a EQ (Equivalense portition)
        System.out.println("Testing for the amount of tasks added, adding 5 to the information container to consultant Bo@Schnell.dk");

        InformationContainerSingleton.getInstance().setTasks(TestingSingleton.getInstance().getTestTasksForTestProjects());

        double expected = 5;
        double actual = 0;

        for (Task t:InformationContainerSingleton.getInstance().getTasks()) {
            if (t.getEmail().equals("Bo@Schnell.dk")){
                actual++;
            }
        }
        assertEquals(expected,actual);
    }

    @Test
    public void SetAndGetProjects(){
        //Testing a EQ (Equivalense portition)
        System.out.println("Testing if the project 3rd Project has been added");

        InformationContainerSingleton.getInstance().setProjects(TestingSingleton.getInstance().getTestProjects());

        double expected = 1;
        double actual = 0;

        for (Project p:InformationContainerSingleton.getInstance().getProjects()) {
            if (p.getName().equals("3rd Project")){
                actual++;
            }
        }

        assertEquals(expected,actual);
    }

    @Test
    public void SkipButtonMethode(){
        //Testing a EQ (Equivalense portition)
        System.out.println("Testing if the skip button work, skip from Task to Break");

        InformationContainerSingleton.getInstance().setProjects(TestingSingleton.getInstance().getTestProjects());

        String expected = "Break";
        String actual = TimerSingleton.getInstance().timeTypeProperty().toString().substring(0,4);

        assertEquals(expected,actual);
    }

    @Test
    public void AddTaskLine(){
        //Testing a EQ (Equivalense portition)
        System.out.println("Testing if the add taskline to the todolist work, adding 1 taskline");

        InformationContainerSingleton.getInstance().getDoTodayList().add(new Taskline());

        double expected = 1;
        double actual = InformationContainerSingleton.getInstance().getDoTodayList().size();

        assertEquals(expected,actual);
    }

    @Test
    public void UpdateStandardTaskTime(){
        //Testing a EQ (Equivalense portition)
        System.out.println("Testing if the update tasktime works, setting the standard task time to 00:15:00");

        TimerSingleton.getInstance().setStandardTaskTime(Time.valueOf("00:15:00"));

        Time expected = Time.valueOf("00:15:00");
        Time actual = Time.valueOf(TimerSingleton.getInstance().getStandardTaskTime().toString());

        assertEquals(expected,actual);
    }



}
