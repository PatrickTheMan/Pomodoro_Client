package UI.Structures.SceneStructureParts.SmallParts;

import Domain.Project;
import UI.Buttons.CustomButton;
import UI.Enums.MyPos;
import UI.Enums.MyShape;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class Taskline extends HBox {

    private boolean editing = true;

    private String projectName;
    private String taskName;
    private int amountPomodoros;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getAmountPomodoros() {
        return amountPomodoros;
    }

    public void setAmountPomodoros(int amountPomodoros) {
        this.amountPomodoros = amountPomodoros;
    }

    /**
     *
     */
    public Taskline(){
        // Normal setup
        NormalSetup();
    }

    /**
     *
     */
    public void NormalSetup(){

        // Make this object use the custom css styling
        this.getStyleClass().add("taskline");

        // Set alignment
        this.setAlignment(Pos.CENTER);

        // Get all projects db
        ArrayList<Project> projects = new ArrayList<>();
        ArrayList<String> projectNames = new ArrayList<>();

        for (Project p:projects) {
            projectNames.add(p.getName());
        }

        //
        ChoiceComboBox projectChoice = new ChoiceComboBox("Project:");
        projectChoice.setContent(projectNames);
        projectChoice.setScaling(true);
        projectChoice.setPos(MyPos.CENTER);
        projectChoice.removeBorder();
        projectChoice.maxWidthProperty().bind(this.widthProperty().divide(10).multiply(2));

        //
        ChoiceComboBox taskChoice = new ChoiceComboBox("Task:   ");
        taskChoice.setScaling(true);
        taskChoice.setPos(MyPos.CENTER);
        taskChoice.setWriteable(true);
        taskChoice.removeBorder();
        taskChoice.maxWidthProperty().bind(this.widthProperty().divide(10).multiply(4));



        //
        ArrayList<Node> buttonsAndCounter = new ArrayList<>();

        CustomButton buttonAdd = new CustomButton().Other().Add().setToAddPomodoro(this);
        buttonsAndCounter.add(buttonAdd);

        Headline counter = new Headline("0");
        counter.getLabel().setStyle("-fx-font-size: 10;");
        counter.setStyle("-fx-padding: 0;");
        counter.setAlignment(Pos.CENTER);
        counter.removeBorder();
        buttonsAndCounter.add(counter);

        CustomButton buttonMinus = new CustomButton().Other().Minus();
        buttonsAndCounter.add(buttonMinus);

        //
        NodeBarH buttonsCounter = new NodeBarH(buttonsAndCounter);
        buttonsCounter.setAlignment(Pos.CENTER);
        buttonsCounter.removeBorder();
        buttonsCounter.prefWidthProperty().bind(this.widthProperty().divide(10).multiply(3));




        //
        ArrayList<Node> buttonsRemoveDone = new ArrayList<>();

        CustomButton buttonDelete = new CustomButton().Other().Minus();
        buttonsRemoveDone.add(buttonDelete);

        CustomButton buttonDone = new CustomButton().Other().Save();
        buttonsRemoveDone.add(buttonDone);

        //
        NodeBarH buttonsRemoveDoneContainer = new NodeBarH(buttonsRemoveDone);
        buttonsRemoveDoneContainer.setAlignment(Pos.CENTER);
        buttonsRemoveDoneContainer.removeBorder();
        buttonsRemoveDoneContainer.prefWidthProperty().bind(this.widthProperty().divide(10).multiply(1));




        this.getChildren().addAll(projectChoice,taskChoice,buttonsCounter,buttonsRemoveDoneContainer);

    }

}
