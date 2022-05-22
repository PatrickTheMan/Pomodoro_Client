package UI;

import UI.Buttons.Menu.CustomButton_DoToday;
import UI.Buttons.Menu.CustomButton_Home;
import UI.Buttons.Menu.CustomButton_Overview;
import UI.Buttons.Menu.CustomMenuButton;
import UI.Enums.SceneType;
import UI.Structures.MenuBar.CustomMenuBar;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Scenehandler {

    private Stage stage = new Stage();



    public Scenehandler(){}

    /**
     *
     * @return
     */
    public Stage getStage(){
        return stage;
    }

    /**
     *
     * @param sceneType
     */
    public void setStage(SceneType sceneType){
        // Set the stages scene to the right one
        switch (sceneType){
            case Home -> {
                stage.setTitle("Client Startup - Home");
                stage.setScene(getHomeScene());
            }
            case Overview ->{
                stage.setTitle("Client Startup - Overview");
                stage.setScene(getOverview());
            }
            case DoToday -> {
                stage.setTitle("Client Startup - DoToday");
                stage.setScene(getDoTodayScene());
            }
        }
    }

    /**
     *
     * @param sceneType
     * @return
     */
    public Stage setAndGetStage(SceneType sceneType){
        setStage(sceneType);
        return getStage();
    }

    /**
     *
     * @return
     */
    private Scene getHomeScene(){

        HBox root = new HBox();

        // Add the menu bar with 3 buttons
        ArrayList<CustomMenuButton> customMenuButtons = new ArrayList<>();
        customMenuButtons.add(new CustomButton_Home());
        customMenuButtons.add(new CustomButton_Overview());
        customMenuButtons.add(new CustomButton_DoToday());

        CustomMenuBar customMenuBar = new CustomMenuBar(customMenuButtons);

        root.getChildren().addAll(customMenuBar);

        // Add content
        VBox vBox = new VBox();

        root.getChildren().add(vBox);


        // Create the scene with the root
        Scene scene = new Scene(root, 1500, 750);

        // Set the stylesheet for the scene
        scene.getStylesheets().add(new File("src/main/resources/ClientStyleSheet.css").toURI().toString());

        // Return the scene
        return scene;
    }

    /**
     *
     * @return
     */
    private Scene getOverview(){

        HBox root = new HBox();

        // Add the menu bar with 3 buttons
        ArrayList<CustomMenuButton> customMenuButtons = new ArrayList<>();
        customMenuButtons.add(new CustomButton_Home());
        customMenuButtons.add(new CustomButton_Overview());
        customMenuButtons.add(new CustomButton_DoToday());

        CustomMenuBar customMenuBar = new CustomMenuBar(customMenuButtons);

        root.getChildren().addAll(customMenuBar);

        // Add content
        VBox vBox = new VBox();

        root.getChildren().add(vBox);


        // Create the scene with the root
        Scene scene = new Scene(root, 1500, 750);

        // Set the stylesheet for the scene
        scene.getStylesheets().add(new File("src/main/resources/ClientStyleSheet.css").toURI().toString());

        // Return the scene
        return scene;
    }

    /**
     *
     * @return
     */
    private Scene getDoTodayScene() {

        HBox root = new HBox();

        // Add the menu bar with 3 buttons
        ArrayList<CustomMenuButton> customMenuButtons = new ArrayList<>();
        customMenuButtons.add(new CustomButton_Home());
        customMenuButtons.add(new CustomButton_Overview());
        customMenuButtons.add(new CustomButton_DoToday());

        CustomMenuBar customMenuBar = new CustomMenuBar(customMenuButtons);

        root.getChildren().addAll(customMenuBar);

        // Add content
        VBox vBox = new VBox();

        root.getChildren().add(vBox);


        // Create the scene with the root
        Scene scene = new Scene(root, 1500, 750);

        // Set the stylesheet for the scene
        scene.getStylesheets().add(new File("src/main/resources/ClientStyleSheet.css").toURI().toString());

        // Return the scene
        return scene;
    }

}
