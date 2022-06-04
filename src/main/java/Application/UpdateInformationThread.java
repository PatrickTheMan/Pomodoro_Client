package Application;

import Foundation.Singletons.InformationContainerSingleton;

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

                // Sleep in 5 min
                Thread.sleep(300000);
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
