package Testing;

import Domain.Project;
import Domain.Singletons.ConsultantSingleton;
import Domain.Singletons.TimerSingleton;
import Domain.Task;
import Domain.Singletons.InformationContainerSingleton;
import Testing.Singletons.TestingSingleton;
import org.junit.*;

import java.sql.Time;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Patrick G. Schemel
 */
public class UnitTesting {

    @BeforeClass
    public static void startTesting(){
        System.out.println("Start testing ...");
    }

    @AfterClass
    public static void endTesting(){
        System.out.print("... Done testing");
    }


    @Test
    public void setConsutlantTest(){
        //Testing a EQ (Equivalense portition)
        System.out.println("Testing for if the consultant has been set, with the test consultant");

        TestingSingleton.getInstance().loadTestConsultant();

        String expected = "Jhon Nielsen";
        String actual = ConsultantSingleton.getInstance().getName();
        assertEquals(expected,actual);
    }

    @Test
    public void setAndGetTasks(){
        //Testing a EQ (Equivalense portition)
        System.out.println("Testing for the amount of tasks added, adding 5 to the information container to consultant Bo@Schnell.dk");

        InformationContainerSingleton.getInstance().setTasks(TestingSingleton.getInstance().getTestTasksForTestProjects());

        int expected = 5;
        int actual = 0;

        for (Task t:InformationContainerSingleton.getInstance().getTasks()) {
            if (t.getEmail().equals("Bo@Schnell.com")){
                actual++;
            }
        }
        assertEquals(expected,actual);
    }

    @Test
    public void setAndGetProjects(){
        //Testing a EQ (Equivalense portition)
        System.out.println("Testing if the project 3rd Project has been added");

        InformationContainerSingleton.getInstance().setProjects(TestingSingleton.getInstance().getTestProjects());

        int expected = 1;
        int actual = 0;

        for (Project p:InformationContainerSingleton.getInstance().getProjects()) {
            if (p.getName().equals("3rd Project")){
                actual++;
            }
        }

        assertEquals(expected,actual);
    }

    @Test
    public void skipButtonToNextTimerType(){
        //Testing a EQ (Equivalense portition)
        System.out.println("Testing if the skip button work, skip from Task to Break");

        TimerSingleton.getInstance().skipTimerTest();

        String expected = "Brea";
        String actual = TimerSingleton.getInstance().timeTypeProperty().getValue().substring(0,4);

        assertEquals(expected,actual);
    }

    @Test
    public void skipButtonTimeOffset(){
        //Testing a EQ (Equivalense portition)
        System.out.println("Testing if the skip button work, set the offset time. Set time to 24:30 out of 25:00 = 00:30");

        TimerSingleton.getInstance().setTime(Time.valueOf("00:24:30"));
        TimerSingleton.getInstance().setTaskOffsetTime();

        String expected = "00:00:30";
        String actual = TimerSingleton.getInstance().getTaskOffsetTime().toString();

        assertEquals(expected,actual);
    }

    @Test
    public void updateStandardWorkTime(){
        //Testing a EQ (Equivalense portition)
        System.out.println("Testing if the update tasktime works, setting the standard task time to 00:15:00");

        TimerSingleton.getInstance().setStandardWorkTime(Time.valueOf("00:15:00"));

        Time expected = Time.valueOf("00:15:00");
        Time actual = Time.valueOf(TimerSingleton.getInstance().getStandardWorkTime().toString());

        assertEquals(expected,actual);
    }



}
