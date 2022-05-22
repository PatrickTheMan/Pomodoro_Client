package Application;

import UI.Enums.SceneType;
import UI.Singletons.ScenehandlerSingleton;

public class Controller {

    public void setSceneHome(){
        ScenehandlerSingleton.getInstance().setStage(SceneType.Home);
    }

    public void setSceneOverview(){
        ScenehandlerSingleton.getInstance().setStage(SceneType.Overview);
    }

    public void setSceneDoToday(){
        ScenehandlerSingleton.getInstance().setStage(SceneType.DoToday);
    }

}
