package UI;

import UI.Buttons.CustomButton;
import UI.Enums.SceneType;
import UI.Structures.MenuBar.CustomMenuBar;
import UI.Structures.SceneStructureParts.CustomWindow;
import UI.Structures.SceneStructureParts.Windows.SettingsWindow;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Scenehandler {

    private Stage stage;
    private Scene scene;
    private StackPane root;
    private BorderPane mainPane;

    public Scenehandler(){

        // Initiate the root
        this.root = new StackPane();

        // Set the alignment
        this.root.setAlignment(Pos.TOP_LEFT);

        // Initiate the mainPane
        this.mainPane = new BorderPane();

        // Create the scene with the root
        this.scene = new Scene(this.root, 1500, 750);

        // Set the content of the main pane
        mainPane.setLeft(getMenuBar());

        // The menubar is made as a double for overlay purposes
        VBox menuBar = getMenuBar();

        // Add the content to the root
        this.root.getChildren().addAll(mainPane,menuBar);

        // Initiate the stage
        this.stage = new Stage();

        // Set the stage's scene
        this.stage.setScene(scene);

        // Set the stagestyle
        //this.stage.initStyle(StageStyle.TRANSPARENT);

        // Set the stages title
        this.stage.setTitle("Client Startup");

        // Set the stylesheet for the scene
        this.scene.getStylesheets().setAll(new File("src/main/resources/CSS/ClientStyleSheet.css").toURI().toString());

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

        // Set the stages scene to the right one
        switch (sceneType){
            case Home -> {
                this.stage.setTitle("Client Startup - Home");

                // Set the main content
                this.mainPane.setCenter(getHomeScreen());

                // Set the stylesheet for the scene
                this.scene.getStylesheets().setAll(new File("src/main/resources/CSS/ClientStyleSheet.css").toURI().toString(),new File("src/main/resources/CSS/Scenes/ClientStyleSheet_Home.css").toURI().toString());

            }
            case Overview ->{
                this.stage.setTitle("Client Startup - Overview");

                // Set the main content
                this.mainPane.setCenter(getOverviewScreen());

                // Set the stylesheet for the scene
                this.scene.getStylesheets().setAll(new File("src/main/resources/CSS/ClientStyleSheet.css").toURI().toString(),new File("src/main/resources/CSS/Scenes/ClientStyleSheet_Overview.css").toURI().toString());

            }
            case DoToday -> {
                this.stage.setTitle("Client Startup - DoToday");

                // Set the main content
                this.mainPane.setCenter(getDoTodayScreen());

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
    private VBox getHomeScreen(){

        // Initiate the root for the screen
        VBox root = new VBox();

        // Make this vbox use the custom css styling
        root.getStyleClass().add("screen-home");



        CustomWindow settingsWindow = new CustomWindow().settings(300,400);
        CustomWindow settingsWindow2 = new CustomWindow().settings();
        CustomWindow settingsWindow3 = new CustomWindow().settings();


        //
        HBox sideBySide = new HBox();
        sideBySide.getChildren().addAll(settingsWindow2,settingsWindow3);

        // Set center content of root
        root.getChildren().addAll(settingsWindow,sideBySide);

        return root;
    }

    /**
     *
     * @return
     */
    private VBox getOverviewScreen(){

        // Initiate the root for the screen
        VBox root = new VBox();

        // Make this vbox use the custom css styling
        root.getStyleClass().add("screen-overview");




        return root;
    }

    /**
     *
     * @return
     */
    private VBox getDoTodayScreen() {

        // Initiate the root for the screen
        VBox root = new VBox();

        // Make this vbox use the custom css styling
        root.getStyleClass().add("screen-dotoday");






        return root;
    }

}
