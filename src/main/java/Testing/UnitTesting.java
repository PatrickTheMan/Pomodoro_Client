package Testing;

import Domain.Singletons.ConsultantSingleton;
import Testing.Singletons.TestingSingleton;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class UnitTesting {

    @BeforeClass
    public static void startTesting(){
        System.out.println("Start testing ...");
    }

    @AfterClass
    public static void endTesting(){
        System.out.print("... Done testing");
    }

    @Before
    public void beforeTest() throws Exception {
        System.out.println("... Next test");
    }

    @After
    public void afterTest() throws Exception{
        System.out.println("... No unexpected errors");
    }


    @Test
    public void Case1(){
        //Testing a EQ (Equivalense portition)
        System.out.println("Try the set consultant function with the testcase");

        TestingSingleton.getInstance().loadTestConsultant();

        String expected = "Jhon Nielsen";
        String actual = ConsultantSingleton.getInstance().getName();
        assertEquals(expected,actual);
    }

}
