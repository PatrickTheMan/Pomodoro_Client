package Foundation;

import Domain.Consultant;
import Domain.Singletons.ConsultantSingleton;
import Domain.Task;
import Domain.Singletons.InformationContainerSingleton;
import UI.Structures.SceneStructureParts.SmallParts.Taskline;
import javafx.scene.Node;

/**
 * @author Patrick G. Schemel
 */
public class UpdateInformationThread implements Runnable {

    private Thread updateInfoThread;

    @Override
    public void run() {

        try {

            while (true){

                // Get the current time, so the used time can be calculated
                Long startTime = System.currentTimeMillis();

                // Update information
                InformationContainerSingleton.getInstance().updateAll();

                // Get the current time, so the used time can be calculated
                Long endTime = System.currentTimeMillis();

                // Print
                System.out.println("...Updating is Done ( "+(endTime-startTime)+"ms )");

                // Check if Consultant still exists
                boolean foundConsultant = false;
                if (ConsultantSingleton.getInstance().exists()){
                    for (Consultant c: InformationContainerSingleton.getInstance().getConsultants()) {

                        if (c.getEmail().equals(ConsultantSingleton.getInstance().getEmail())){
                            foundConsultant = true;
                        }

                    }
                    if (!foundConsultant){
                        /*
                        If you would like to close the client,
                        when a counsultant has been removed while a client is running with said consultant,
                        then you could put the code here
                         */
                    }
                }

                // Check if a task in DoToday should be removed
                for (Node n:InformationContainerSingleton.getInstance().getDoTodayList()) {
                    boolean foundTask = false;

                    for (Task t:InformationContainerSingleton.getInstance().getTasks()) {

                        if (((Taskline) n).getTaskID() != -1 && t.getId() == ((Taskline) n).getTaskID()) {
                            foundTask=true;
                        }

                    }
                    if (!foundTask){
                        /*
                        If you would like to remove tasks that don't exists anymore,
                        then you could had put the code here
                         */
                    }
                }

                // Sleep in 2 min and 30 sec
                Thread.sleep(150000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print
        System.out.println("Thread Ended");

    }

    public void start(){

        if (this.updateInfoThread==null){
            this.updateInfoThread = new Thread(this);
            this.updateInfoThread.start();
        }
    }

}
