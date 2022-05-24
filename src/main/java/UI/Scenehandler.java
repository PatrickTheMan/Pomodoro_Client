package UI;

import UI.Buttons.CustomButton;
import UI.Enums.SceneType;
import UI.Structures.MenuBar.CustomMenuBar;
import UI.Structures.SceneStructureParts.SettingsWindow;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Scenehandler {

    private Stage stage;
    private Scene scene;
    private BorderPane root;

    public Scenehandler(){

        // Initiate the root
        this.root = new BorderPane();

        // Add the content to the root
        this.root.setLeft(getMenuBar());

        // Create the scene with the root
        this.scene = new Scene(root, 1500, 750);

        // Initiate the stage
        this.stage = new Stage();

        // Set the stage's scene
        this.stage.setScene(scene);
    }

    /**
     *
     * @return
     */
    public Stage getStage(){
        return this.stage;
    }

    /**
     *
     * @param sceneType
     */
    public void setStage(SceneType sceneType){

        // Set the scene
        stage.setScene(this.scene);

        // Set the stages scene to the right one
        switch (sceneType){
            case Home -> {
                this.stage.setTitle("Client Startup - Home");

                // Set the main content
                this.root.setCenter(getHomeScreen());

                // Set the stylesheet for the scene
                this.scene.getStylesheets().setAll(new File("src/main/resources/CSS/ClientStyleSheet.css").toURI().toString(),new File("src/main/resources/CSS/Scenes/ClientStyleSheet_Home.css").toURI().toString());

            }
            case Overview ->{
                this.stage.setTitle("Client Startup - Overview");

                // Set the main content
                this.root.setCenter(getOverviewScreen());

                // Set the stylesheet for the scene
                this.scene.getStylesheets().setAll(new File("src/main/resources/CSS/ClientStyleSheet.css").toURI().toString(),new File("src/main/resources/CSS/Scenes/ClientStyleSheet_Overview.css").toURI().toString());

            }
            case DoToday -> {
                this.stage.setTitle("Client Startup - DoToday");

                // Set the main content
                this.root.setCenter(getDoTodayScreen());

                // Set the stylesheet for the scene
                this.scene.getStylesheets().setAll(new File("src/main/resources/CSS/ClientStyleSheet.css").toURI().toString(),new File("src/main/resources/CSS/Scenes/ClientStyleSheet_DoToday.css").toURI().toString());

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
    private VBox getMenuBar(){

        // Add the 3 buttons to the arraylist containing the different buttons
        ArrayList<CustomButton> customButtons = new ArrayList<>();
        customButtons.add(new CustomButton().menu().home());
        customButtons.add(new CustomButton().menu().overview());
        customButtons.add(new CustomButton().menu().doToday());

        // Create the buttonpart of the menubar with the given arraylist of buttons
        CustomMenuBar customMenuBar = new CustomMenuBar(customButtons);

        // Return the menubar
        return customMenuBar;
    }

    /**
     *
     * @return
     */
    private Parent getHomeScreen(){

        // Initiate the root for the screen
        BorderPane root = new BorderPane();


        SettingsWindow settingsWindow = new SettingsWindow();



        // Set center content of root
        root.setCenter(settingsWindow);

        // Move the menubar to the front, because of hover function
        this.root.getLeft().getParent().toFront();

        return root;
    }

    /**
     *
     * @return
     */
    private Parent getOverviewScreen(){

        // Initiate the root for the screen
        BorderPane root = new BorderPane();



        // Move the menubar to the front, because of hover function
        this.root.getLeft().getParent().toFront();

        return root;
    }

    /**
     *
     * @return
     */
    private Parent getDoTodayScreen() {

        // Initiate the root for the screen
        BorderPane root = new BorderPane();





        // Move the menubar to the front, because of hover function
        this.root.getLeft().getParent().toFront();

        return root;
    }

}
