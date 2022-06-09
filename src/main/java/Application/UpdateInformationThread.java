package Application;

import Domain.Consultant;
import Domain.Singletons.ConsultantSingleton;
import Domain.Task;
import Foundation.Singletons.InformationContainerSingleton;
import UI.Structures.SceneStructureParts.SmallParts.Taskline;
import javafx.scene.Node;

public class UpdateInformationThread implements Runnable {

    Thread updateInfoThread;

    @Override
    public void run() {

        // Print
        System.out.println("Thread Started");

        try {

            while (true){

                // Print
                System.out.println("...Updating");

                // Get the current time, so the used time can be calculated
                Long startTime = System.currentTimeMillis();

                // Update information
                InformationContainerSingleton.getInstance().updateAll();

                // Get the current time, so the used time can be calculated
                Long endTime = System.currentTimeMillis();

                // Print
                System.out.println("...Done ( "+(endTime-startTime)+"ms )");

                // Check if Consultant still exists
                boolean foundConsultant = false;
                if (ConsultantSingleton.getInstance().exists()){
                    for (Consultant c: InformationContainerSingleton.getInstance().getConsultants()) {

                        if (c.getEmail().equals(ConsultantSingleton.getInstance().getEmail())){
                            System.out.println("Consultant still exists");
                            foundConsultant = true;
                        }

                    }
                    if (!foundConsultant){
                        System.out.println("Consultant has been removed");
                        System.out.println("TERMINATE CLIENT");
                    }
                }

                // Check if a task in DoToday should be removed
                for (Node n:InformationContainerSingleton.getInstance().getDoTodayList()) {
                    boolean foundTask = false;

                    for (Task t:InformationContainerSingleton.getInstance().getTasks()) {

                        if (((Taskline) n).getTaskID() != -1 && t.getId() == ((Taskline) n).getTaskID()) {
                            System.out.println("Task exists");
                            foundTask=true;
                        }

                    }
                    if (!foundTask){
                        System.out.println("Task dont exist any more");
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
