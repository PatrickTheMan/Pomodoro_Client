package Domain.Singletons;

import Domain.Consultant;

import java.sql.Time;

public class ConsultantSingleton {

    private static Consultant instance;

    // Private so you can't "new" it
    private ConsultantSingleton(){}

    /**
     * Gets the instance, it is synchronized because in teori it can be used by multiple threads
     * @return the instance
     */
    public synchronized static Consultant getInstance(){
        // Create the instance if it hasn't been initiated, set the standard values
        if (instance==null){
            instance = new Consultant(
                    "",
                    "",
                    "",
                    Time.valueOf("00:25:00"),
                    Time.valueOf("00:05:00"),
                    Time.valueOf("00:30:00"),
                    true,
                    0,
                    ""
            );
        }
        // Return the instance
        return instance;
    }

}
