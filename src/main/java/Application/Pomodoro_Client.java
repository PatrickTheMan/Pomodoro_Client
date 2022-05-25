package Application;

import UI.Singletons.ScenehandlerSingleton;
import javafx.application.Application;
import javafx.stage.Stage;

public class Pomodoro_Client extends Application {
    @Override
    public void start(Stage stage) {

        // Set the scene the scenehandler should work with
        ScenehandlerSingleton.getInstance();

        // Get and set the stage
        stage=ScenehandlerSingleton.getInstance().getStage();

        // Show the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}