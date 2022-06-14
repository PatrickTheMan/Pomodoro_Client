package Application;

import UI.Enums.SceneType;
import Foundation.Singletons.ScenehandlerSingleton;
import Foundation.UpdateInformationThread;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Patrick G. Schemel
 */
public class Pomodoro_Client extends Application {

    @Override
    public void start(Stage stage) {

        // Start an update thread, which updates the clients' information every 5 min
        new UpdateInformationThread().start();

        // Set the scene the scenehandler should work with
        ScenehandlerSingleton.getInstance();

        // Get and set the stage
        stage=ScenehandlerSingleton.getInstance().getStage();

        // Show the stage
        stage.show();

        // Set the scene
        ScenehandlerSingleton.getInstance().setStage(SceneType.HOME);
    }

    public static void main(String[] args) {
        launch();
    }
}