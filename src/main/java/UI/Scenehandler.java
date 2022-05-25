package UI;

import Application.Singleton.ControllerSingleton;
import Domain.Consultant;
import Domain.Singletons.ConsultantSingleton;
import UI.Buttons.CustomButton;

import UI.Enums.SceneType;
import UI.Structures.MenuBar.CustomMenuBar;
import UI.Structures.SceneStructureParts.CustomWindow;
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

    public Scene getScene() {
        return scene;
    }

    public StackPane getRoot() {
        return root;
    }

    public BorderPane getMainPane() {
        return mainPane;
    }



    public Scenehandler(){

        // Initiate the root
        this.root = new StackPane();

        // Set the alignment
        this.root.setAlignment(Pos.TOP_LEFT);

        // Initiate the mainPane
        this.mainPane = new BorderPane();

        // Create the scene with the root
        this.scene = new Scene(this.root, 1600, 800);

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
                if (!ConsultantSingleton.getInstance().getFirstName().equals("")){
                    this.stage.setTitle("Client Startup - "+ConsultantSingleton.getInstance().getFullName()+" - Home");
                } else {
                    this.stage.setTitle("Client Startup - Home");
                }

                // Set the main content
                this.mainPane.setCenter(getHomeScreen());

                // Set the stylesheet for the scene
                this.scene.getStylesheets().setAll(new File("src/main/resources/CSS/ClientStyleSheet.css").toURI().toString(),new File("src/main/resources/CSS/Scenes/ClientStyleSheet_Home.css").toURI().toString());

            }
            case Overview ->{
                if (!ConsultantSingleton.getInstance().getFirstName().equals("")){
                    this.stage.setTitle("Client Startup - "+ConsultantSingleton.getInstance().getFullName()+" - Overview");
                } else {
                    this.stage.setTitle("Client Startup - Overview");
                }

                // Set the main content
                this.mainPane.setCenter(getOverviewScreen());

                // Set the stylesheet for the scene
                this.scene.getStylesheets().setAll(new File("src/main/resources/CSS/ClientStyleSheet.css").toURI().toString(),new File("src/main/resources/CSS/Scenes/ClientStyleSheet_Overview.css").toURI().toString());

            }
            case DoToday -> {
                if (!ConsultantSingleton.getInstance().getFirstName().equals("")){
                    this.stage.setTitle("Client Startup - "+ConsultantSingleton.getInstance().getFullName()+" - DoToday");
                } else {
                    this.stage.setTitle("Client Startup - DoToday");
                }

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
        customButtons.add(new CustomButton().Menu().Home());
        customButtons.add(new CustomButton().Menu().Overview());
        customButtons.add(new CustomButton().Menu().DoToday());

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

        CustomWindow headline = new CustomWindow().Headline("Home");

        CustomWindow settingsWindow = new CustomWindow().Settings(300,375);
        CustomWindow settingsWindow2 = new CustomWindow().Settings();
        CustomWindow settingsWindow3 = new CustomWindow().Settings();


        //
        HBox sideBySide = new HBox();
        sideBySide.getChildren().addAll(settingsWindow2,settingsWindow3);


        // Set center content of root
        root.getChildren().addAll(headline,settingsWindow,sideBySide);

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


        CustomWindow headline = new CustomWindow().Headline("Overview");





        root.getChildren().addAll(headline);

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

        // Add headline
        CustomWindow headline = new CustomWindow().Headline("Do Today");

        // Add the Nodebar underneath
        //CustomWindow barH = new CustomWindow().ButtonBarH();





        root.getChildren().addAll(headline);

        return root;
    }

}
