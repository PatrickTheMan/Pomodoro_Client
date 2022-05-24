package UI;

import UI.Buttons.Menu.CustomMenuButton;
import UI.Enums.SceneType;
import UI.Structures.MenuBar.CustomMenuBar;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Scenehandler {

    private Stage stage = new Stage();
    private Scene scene = new Scene();
    private BorderPane root = new BorderPane();

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

        // Create the scene with the root
        this.scene = new Scene(root, 1500, 750);

        //
        stage.setScene(this.scene);

        // Set the stages scene to the right one
        switch (sceneType){
            case Home -> {
                stage.setTitle("Client Startup - Home");

                // Set the stylesheet for the scene
                scene.getStylesheets().add(new File("src/main/resources/CSS/Scenes/ClientStyleSheet_Home.css").toURI().toString());

            }
            case Overview ->{
                stage.setTitle("Client Startup - Overview");

                // Set the stylesheet for the scene
                scene.getStylesheets().add(new File("src/main/resources/CSS/Scenes/ClientStyleSheet_Overview.css").toURI().toString());

            }
            case DoToday -> {
                stage.setTitle("Client Startup - DoToday");

                // Set the stylesheet for the scene
                this.scene.getStylesheets().add(new File("src/main/resources/CSS/Scenes/ClientStyleSheet_DoToday.css").toURI().toString());

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
    private Node getHomeNode(){

        HBox root = new HBox();

        // Add the menu bar with 3 buttons
        ArrayList<CustomMenuButton> customMenuButtons = new ArrayList<>();
        customMenuButtons.add(new CustomMenuButton().CustomButton_Home());
        customMenuButtons.add(new CustomMenuButton().CustomButton_Overview());
        customMenuButtons.add(new CustomMenuButton().CustomButton_DoToday());

        CustomMenuBar customMenuBar = new CustomMenuBar(customMenuButtons);

        root.getChildren().addAll(customMenuBar);

        // Add content
        VBox vBox = new VBox();

        root.getChildren().add(vBox);


    }

    /**
     *
     * @return
     */
    private Node setOverviewNode(){

        HBox root = new HBox();

        // Add the menu bar with 3 buttons
        ArrayList<CustomMenuButton> customMenuButtons = new ArrayList<>();
        customMenuButtons.add(new CustomMenuButton().CustomButton_Home());
        customMenuButtons.add(new CustomMenuButton().CustomButton_Overview());
        customMenuButtons.add(new CustomMenuButton().CustomButton_DoToday());

        CustomMenuBar customMenuBar = new CustomMenuBar(customMenuButtons);

        root.getChildren().addAll(customMenuBar);

        // Add content
        VBox vBox = new VBox();

        root.getChildren().add(vBox);


        // Create the scene with the root
        Scene scene = new Scene(root, 1500, 750);



        // Return the scene
        return scene;
    }

    /**
     *
     * @return
     */
    private Node getDoTodayNode() {








        // Return the scene
        return scene;
    }

}
