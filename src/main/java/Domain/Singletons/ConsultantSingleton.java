package Domain.Singletons;

import Domain.Consultant;

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
            instance = new Consultant("","",25,5,30);
        }
        // Return the instance
        return instance;
    }

}
