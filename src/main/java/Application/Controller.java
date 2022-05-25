package Application;

import Domain.Consultant;
import Domain.Singletons.ConsultantSingleton;
import UI.Enums.SceneType;
import UI.Singletons.ScenehandlerSingleton;
import UI.Structures.SceneStructureParts.Windows.SettingsWindow;

public class Controller {

    /**
     *
     */
    public void setSceneHome(){
        ScenehandlerSingleton.getInstance().setStage(SceneType.Home);
    }

    /**
     *
     */
    public void setSceneOverview(){
        ScenehandlerSingleton.getInstance().setStage(SceneType.Overview);
    }

    /**
     *
     */
    public void setSceneDoToday(){
        ScenehandlerSingleton.getInstance().setStage(SceneType.DoToday);
    }

    /**
     *
     * @param consultant
     */
    public void setConsultant(Consultant consultant){

        // DO IT NOW

        // Change the stage title
        ScenehandlerSingleton.getInstance().getStage().setTitle(
                ScenehandlerSingleton.getInstance().getStage().getTitle().substring(0,16)+" "
                +consultant.getFullName()+" - "
                +ScenehandlerSingleton.getInstance().getStage().getTitle().substring(ConsultantSingleton.getInstance().getFullName().length()+20));

        // Set the new consultant
        ConsultantSingleton.getInstance().setConsultant(consultant);

    }

    /**
     *
     * @param consultant
     * @param settingsWindow
     */
    public void updateConsultantValues(Consultant consultant, SettingsWindow settingsWindow){
        // Update fields
        settingsWindow.setTaskTimeFieldText(""+consultant.getTaskTime());
        settingsWindow.setBreakTimeFieldText(""+consultant.getBreakTime());
        settingsWindow.setLongbreakTimeFieldText(""+consultant.getLongBreakTime());
    }

}
