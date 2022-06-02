package UI;

import Application.Controller;
import Application.Singleton.ControllerSingleton;
import Domain.Singletons.ConsultantSingleton;
import Domain.Singletons.TimerSingleton;
import UI.Buttons.CustomButton;

import UI.Enums.MyPos;
import UI.Enums.MyScaling;
import UI.Enums.MyShape;
import UI.Enums.SceneType;
import UI.Structures.MenuBar.CustomMenuBar;
import UI.Structures.SceneStructureParts.CustomWindow;
import UI.Structures.SceneStructureParts.SmallParts.*;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.ArrayList;

public class Scenehandler {

    private String sceneTitle;

    private Stage stage;
    private Scene scene;
    private StackPane root;
    private BorderPane mainPane;

    private Stage miniStage;
    private Scene miniScene;
    private NodeBarH miniNodeBarH;

    private ArrayList<Node> nodeArrayList = new ArrayList<>();


    public Scene getScene() {
        return scene;
    }

    public StackPane getRoot() {
        return root;
    }

    public BorderPane getMainPane() {
        return mainPane;
    }

    public Stage getStage(){
        return this.stage;
    }

    public Stage getMiniStage() {
        return miniStage;
    }

    public Scene getMiniScene() {
        return miniScene;
    }

    public NodeBarH getMiniNodeBarH() {
        return miniNodeBarH;
    }

    public String getSceneTitle() {return sceneTitle;}

    public ArrayList<Node> getNodeArrayList() {return nodeArrayList;}


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

        // Set the stages title
        this.stage.setTitle("Client Startup");

        // Set the stylesheet for the scene
        this.scene.getStylesheets().setAll(new File("src/main/resources/CSS/ClientStyleSheet.css").toURI().toString());

        // Set the stages limits in terms of sizing
        this.stage.setMinWidth(850);
        this.stage.setMinHeight(450);

        // Close miniStage if the main window is closed
        this.stage.setOnCloseRequest(e -> {
            if (this.miniStage!=null){
                this.miniStage.close();
            }
        });
    }

    /**
     *
     * @param sceneType
     */
    public void setStage(SceneType sceneType){

        // Set the stages scene to the right one
        switch (sceneType){
            case Home -> {

                // Set title of the stage
                this.sceneTitle="Home";
                ControllerSingleton.getInstance().setTimerTitle();

                // Set the main content
                this.mainPane.setCenter(getHomeScreen());

                // Set the stylesheet for the scene
                this.scene.getStylesheets().setAll(new File("src/main/resources/CSS/ClientStyleSheet.css").toURI().toString(),new File("src/main/resources/CSS/Scenes/ClientStyleSheet_Home.css").toURI().toString());

            }
            case Overview ->{

                // Set title of the stage
                this.sceneTitle="Overview";
                ControllerSingleton.getInstance().setTimerTitle();

                // Set the main content
                this.mainPane.setCenter(getOverviewScreen());

                // Set the stylesheet for the scene
                this.scene.getStylesheets().setAll(new File("src/main/resources/CSS/ClientStyleSheet.css").toURI().toString(),new File("src/main/resources/CSS/Scenes/ClientStyleSheet_Overview.css").toURI().toString());

            }
            case DoToday -> {

                // Set title of the stage
                this.sceneTitle="DoToday";
                ControllerSingleton.getInstance().setTimerTitle();

                // Set the main content
                this.mainPane.setCenter(getDoTodayScreen());

                // Set the stylesheet for the scene
                this.scene.getStylesheets().setAll(new File("src/main/resources/CSS/ClientStyleSheet.css").toURI().toString(),new File("src/main/resources/CSS/Scenes/ClientStyleSheet_DoToday.css").toURI().toString());

            }
        }

        // Set focus to none of the nodes
        this.root.requestFocus();
    }

    public void startMiniStage(){

        // The list with nodes
        ArrayList<Node> nodes = new ArrayList<>();

        // The list with nodes
        ArrayList<Node> nodesControls = new ArrayList<>();

        // Set the headline & bind the timerLabel to the timer
        Headline timerLabel = new Headline("");
        timerLabel.setPos(MyPos.CENTER);
        timerLabel.setShape(MyShape.ROUND);
        timerLabel.setScaling(MyScaling.BIG);
        timerLabel.setStyle("-fx-padding: 0");
        timerLabel.getLabel().setStyle("-fx-font-size: 20; -fx-padding: 0;");
        timerLabel.getLabel().textProperty().bind(TimerSingleton.getInstance().timeProperty());

        // Set the headline & bind the timerLabel to the timer
        Headline statusLabel = new Headline("");
        statusLabel.setPos(MyPos.CENTER);
        statusLabel.setShape(MyShape.ROUND);
        statusLabel.setScaling(MyScaling.BIG);
        statusLabel.setStyle("-fx-padding: 0");
        statusLabel.getLabel().setStyle("-fx-font-size: 10; -fx-padding: 0;");
        statusLabel.getLabel().textProperty().bind(TimerSingleton.getInstance().timeTypeProperty());

        // Make the miniStage moveable on the time label
        timerLabel.setOnMousePressed(e1 -> {
            timerLabel.setOnMouseDragged( e2 -> {
                this.miniStage.setX(e2.getScreenX() - e1.getSceneX());
                this.miniStage.setY(e2.getScreenY() - e1.getSceneY());
            });
        });

        // Make the miniStage moveable on the status label
        statusLabel.setOnMousePressed(e1 -> {
            statusLabel.setOnMouseDragged( e2 -> {
                this.miniStage.setX(e2.getScreenX() - e1.getSceneX());
                this.miniStage.setY(e2.getScreenY() - e1.getSceneY());
            });
        });

        // Add the buttons
        CustomButton buttonStop = new CustomButton().Controls().Stop();
        CustomButton buttonPausePlay = new CustomButton().Controls().PlayAndPause();
        CustomButton buttonSkip = new CustomButton().Controls().Skip();

        // Add seperator
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);

        // Style buttons to make them smaller
        buttonStop.setStyle("-fx-background-size: 15;");
        buttonPausePlay.setStyle("-fx-background-size: 25;");
        buttonSkip.setStyle("-fx-background-size: 20;");

        // Add the different buttons to the nodecontrol array
        nodesControls.add(buttonPausePlay);
        nodesControls.add(separator);
        nodesControls.add(buttonStop);
        nodesControls.add(buttonSkip);

        // Initiate a container for the buttons
        NodeBarH buttonContainer = new NodeBarH(nodesControls,MyPos.RIGHT);
        // Style the container
        buttonContainer.setStyle("-fx-spacing: 5; -fx-padding: 0; -fx-border-color: -fx-color2");

        // Add the different timer
        nodes.add(statusLabel);
        nodes.add(timerLabel);
        nodes.add(buttonContainer);

        // Initiate the root
        this.miniNodeBarH = new NodeBarH(nodes,MyPos.RIGHT);

        // Style the miniNodeBarH
        this.miniNodeBarH.setStyle("-fx-spacing: 15; -fx-padding: 0;");

        // Make the miniStage moveable on the background
        this.miniNodeBarH.setOnMousePressed(e1 -> {
            this.miniNodeBarH.setOnMouseDragged( e2 -> {
                this.miniStage.setX(e2.getScreenX() - e1.getSceneX());
                this.miniStage.setY(e2.getScreenY() - e1.getSceneY());
            });
        });

        // Create the scene with the root
        this.miniScene = new Scene(this.miniNodeBarH, 320, 50);

        // Remove the background
        this.miniScene.setFill(Color.TRANSPARENT);

        // Initiate the stage
        this.miniStage = new Stage();

        // Set the stage's scene
        this.miniStage.setScene(miniScene);

        // Make this scene use the custom css styling
        this.miniScene.getStylesheets().add("miniscene");
        this.miniStage.initStyle(StageStyle.TRANSPARENT);


        // Set the stylesheet for the scene
        this.miniScene.getStylesheets().setAll(new File("src/main/resources/CSS/ClientStyleSheet.css").toURI().toString());

        // Always on top
        this.miniStage.setAlwaysOnTop(true);

        // Show miniStage
        this.miniStage.show();

        // Set the start location of the miniStage
        this.miniStage.setX(Screen.getPrimary().getVisualBounds().getWidth()-this.miniStage.getWidth()-10);
        this.miniStage.setY(Screen.getPrimary().getVisualBounds().getHeight()-this.miniStage.getHeight()-10);
    }

    public void closeMiniStage(){
        // Close miniStage
        this.miniStage.close();
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

        // Give the screen a scroll function
        ScrollPane view = new ScrollPane();
        view.setFitToWidth(true);

        // Initiate the content container
        VBox container = new VBox();

        // Make this vbox use the custom css styling
        root.getStyleClass().add("screen-home");

        // Create the headline
        CustomWindow headline = new CustomWindow().Headline("Home");

        // Create the pomodoroTimerWindow
        CustomWindow pomodoroWindow = new CustomWindow().Pomodoro();

        // Create the setting window
        CustomWindow settingsWindow = new CustomWindow().Settings();
        settingsWindow.setMinMaxSize(200,200,600,400);
        settingsWindow.prefWidthProperty().bind(mainPane.widthProperty().divide(4));






        // Clear node arraylist if it has been used
        if (this.nodeArrayList.size()>0){
            this.nodeArrayList.clear();
        }

        //TESTING
        Taskline test1 = new Taskline();
        Taskline test2 = new Taskline();
        Taskline test3 = new Taskline();
        Taskline test4 = new Taskline();
        Taskline test5 = new Taskline();
        Taskline test6 = new Taskline();
        Taskline test7 = new Taskline();
        Taskline test8 = new Taskline();
        Taskline test9 = new Taskline();
        Taskline test10 = new Taskline();



        this.nodeArrayList.add(test1);
        this.nodeArrayList.add(test2);
        this.nodeArrayList.add(test3);
        this.nodeArrayList.add(test4);
        this.nodeArrayList.add(test5);
        this.nodeArrayList.add(test6);
        this.nodeArrayList.add(test7);
        this.nodeArrayList.add(test8);
        this.nodeArrayList.add(test9);
        this.nodeArrayList.add(test10);


        // Create the list window with the nodeArrayList as it's content
        CustomWindow listWindow = new CustomWindow().NodePage("User Tasks",nodeArrayList,1,230,15);
        listWindow.setMinMaxSize(100,200,1200,400);
        listWindow.prefWidthProperty().bind(mainPane.widthProperty().divide(4).multiply(3));







        // Add the container, so 2 windows can be next to each other
        HBox sideBySide = new HBox();
        sideBySide.setAlignment(Pos.CENTER);
        sideBySide.getChildren().addAll(settingsWindow,listWindow);

        // Set contentcontainers content
        container.getChildren().addAll(pomodoroWindow,sideBySide);

        // Set the content of the view
        view.setContent(container);

        // Set the content of the root
        root.getChildren().addAll(headline,view);

        // Bind the size of the view
        view.prefHeightProperty().bind(root.heightProperty().subtract(headline.getHeight()));

        return root;
    }

    /**
     *
     * @return
     */
    private VBox getOverviewScreen(){

        // Initiate the root for the screen
        VBox root = new VBox();

        // Give the screen a scroll function
        ScrollPane view = new ScrollPane();
        view.setFitToWidth(true);

        // Initiate the content container
        VBox container = new VBox();

        // Make this vbox use the custom css styling
        root.getStyleClass().add("screen-overview");

        // Create the headline
        CustomWindow headline = new CustomWindow().Headline("Overview");

        CustomWindow settingsWindow = new CustomWindow().Pomodoro();





        // Set contentcontainers content
        container.getChildren().addAll(settingsWindow);

        // Set the content of the view
        view.setContent(container);

        // Set the content of the root
        root.getChildren().addAll(headline,view);

        // Bind the size of the view
        view.prefHeightProperty().bind(root.heightProperty().subtract(headline.getHeight()));

        return root;
    }

    /**
     *
     * @return
     */
    private VBox getDoTodayScreen() {

        // Clear node arraylist if it has been used
        if (this.nodeArrayList.size()>0){
            this.nodeArrayList.clear();
        }

        // Initiate the root for the screen
        VBox root = new VBox();

        // Give the screen a scroll function
        ScrollPane view = new ScrollPane();
        view.setFitToWidth(true);

        // Initiate the content container
        VBox container = new VBox();

        // Make this vbox use the custom css styling
        root.getStyleClass().add("screen-dotoday");

        // Create the headline
        CustomWindow headline = new CustomWindow().Headline("Do Today");






        Taskline test1 = new Taskline();
        Taskline test2 = new Taskline();
        Taskline test3 = new Taskline();
        Taskline test4 = new Taskline();
        Taskline test5 = new Taskline();
        Taskline test6 = new Taskline();
        Taskline test7 = new Taskline();
        Taskline test8 = new Taskline();
        Taskline test9 = new Taskline();
        Taskline test10 = new Taskline();



        this.nodeArrayList.add(test1);
        this.nodeArrayList.add(test2);
        this.nodeArrayList.add(test3);
        this.nodeArrayList.add(test4);
        this.nodeArrayList.add(test5);
        this.nodeArrayList.add(test6);
        this.nodeArrayList.add(test7);
        this.nodeArrayList.add(test8);
        this.nodeArrayList.add(test9);
        this.nodeArrayList.add(test10);


        // Create the list window with the nodeArrayList as it's content
        CustomWindow listWindow = new CustomWindow().NodePage("User Tasks",nodeArrayList,1,400,15);




        // Set contentcontainers content
        container.getChildren().addAll(listWindow);

        // Set the content of the view
        view.setContent(container);

        // Set the content of the root
        root.getChildren().addAll(headline,view);

        // Bind the size of the view
        view.prefHeightProperty().bind(root.heightProperty().subtract(headline.getHeight()-(root.getChildren().size()*20)));

        return root;
    }

}
