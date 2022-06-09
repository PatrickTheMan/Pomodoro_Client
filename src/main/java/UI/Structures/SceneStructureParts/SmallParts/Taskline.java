package UI.Structures.SceneStructureParts.SmallParts;

import Application.Singleton.ControllerSingleton;
import Domain.Singletons.ConsultantSingleton;
import Domain.Singletons.TimerSingleton;
import Domain.Task;
import Foundation.Singletons.DBSingleton;
import Foundation.Singletons.InformationContainerSingleton;
import UI.Buttons.CustomButton;
import UI.Enums.MyPos;
import UI.Enums.MyScaling;
import UI.Enums.MyShape;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.sql.Time;
import java.util.ArrayList;

public class Taskline extends HBox {

    private Integer projectId= null;
    private int taskId= -1;
    private boolean taskDone = false;
    private Time taskTimeSpent = Time.valueOf("00:00:00");

    private boolean editing;
    private boolean small;
    private boolean showOnly;

    private ChoiceComboBox projectChoice;
    private ChoiceComboBox taskChoice;
    private Headline counter;
    private NodeBarH buttonsCounterBar;
    private NodeBarH buttonsRemoveDoneContainerBar;

    private ChoiceComboBox projectChoiceShow;
    private ChoiceComboBox taskChoiceShow;
    private Headline pomodorosHeadline;
    private NodeBarH pomodoroHeadlineBar;
    private NodeBarH buttonsEditFinishBar;

    private BooleanProperty diabled = new SimpleBooleanProperty();


    public Headline getCounter() {
        return counter;
    }

    public void setCounter(Headline counter) {
        this.counter = counter;
    }

    public int getTaskID() {
        return taskId;
    }

    public void setTaskID(int taskID) {
        this.taskId = taskID;
    }

    public ChoiceComboBox getTaskChoice() {
        return taskChoice;
    }

    public void setTaskChoice(ChoiceComboBox taskChoice) {
        this.taskChoice = taskChoice;
    }

    /**
     *
     */
    public Taskline(){

        // Normal setup
        normalSetup();

        // Set mode
        this.editing = true;
        setEditSetup();

    }

    /**
     *
     * @param task
     */
    public Taskline(Task task){

        //
        this.showOnly=true;

        // Normal setup
        normalSetup();

        // Set initial values
        this.taskId = task.getId();
        this.projectId = task.getProjectId();
        this.taskTimeSpent = task.getTime();
        this.taskChoice.getChoicebox().setValue(task.getName());
        this.projectChoice.getChoicebox().setValue(InformationContainerSingleton.getInstance().getProject(task.getProjectId()));

        // Set values
        this.projectChoiceShow.getChoicebox().setValue(InformationContainerSingleton.getInstance().getProject(task.getProjectId()).getName());
        this.taskChoiceShow.getChoicebox().setValue(InformationContainerSingleton.getInstance().getTask(task.getId()).getName());

        // Set mode
        this.editing = false;

        setShowOnlySetup();

    }

    /**
     *
     */
    private void normalSetup(){

        // Make this object use the custom css styling
        this.getStyleClass().add("taskline");

        // Set alignment
        this.setAlignment(Pos.CENTER);

        // Edit setup
        editSetup();

        // Show setup
        showSetup();

        // Some width property functionality
        this.widthProperty().addListener((obs,old,newVal) -> {
            if (!this.showOnly){
                if (newVal.intValue()<800){
                    if (!this.editing){

                        if (this.projectChoice.getChoicebox().getValue().equals("Personal")){
                            this.getChildren().setAll(this.taskChoiceShow);
                            this.taskChoiceShow.maxWidthProperty().bind(this.widthProperty());
                        } else {
                            this.getChildren().setAll(this.projectChoiceShow,this.taskChoiceShow);
                            this.projectChoiceShow.maxWidthProperty().bind(this.widthProperty().divide(10).multiply(4));
                            this.taskChoiceShow.maxWidthProperty().bind(this.widthProperty().divide(10).multiply(6));
                        }

                        this.small=true;
                    }
                } else if (this.small && !this.editing){

                    if (this.projectChoice.getChoicebox().getValue().equals("Personal")){
                        this.taskChoiceShow.maxWidthProperty().bind(this.widthProperty().divide(10).multiply(8));
                        this.getChildren().setAll(this.taskChoiceShow,this.pomodoroHeadlineBar,this.buttonsEditFinishBar);
                    } else {
                        this.taskChoiceShow.maxWidthProperty().bind(this.widthProperty().divide(10).multiply(5));
                        this.projectChoiceShow.maxWidthProperty().bind(this.widthProperty().divide(10).multiply(3));
                        this.getChildren().setAll(this.projectChoiceShow,this.taskChoiceShow,this.pomodoroHeadlineBar,this.buttonsEditFinishBar);
                    }


                    this.small=false;

                }
            }
        });

    }

    /**
     *
     */
    private void editSetup(){

        //
        this.projectChoice = new ChoiceComboBox("Project:");
        this.projectChoice.setScaling(true);
        this.projectChoice.setPos(MyPos.CENTER);
        this.projectChoice.removeBorder();
        this.projectChoice.maxWidthProperty().bind(this.widthProperty().divide(10).multiply(2));
        // Fill in the information from DB
        this.projectChoice.setContent(InformationContainerSingleton.getInstance().getProjectsToNames(DBSingleton.getInstance().getProjects()));
        this.projectChoice.getChoicebox().setOnAction(e -> {
            // Set the project id for the taskbar
            if (InformationContainerSingleton.getInstance().getProject(this.projectChoice.getChoicebox().getValue().toString())!=null){
                this.projectId=InformationContainerSingleton.getInstance().getProject(this.projectChoice.getChoicebox().getValue().toString()).getId();
            } else {
                this.projectId=null;
            }
            // Resets the task if the project gets changed
            if (this.taskChoice.getChoicebox().getValue() != null && !this.taskChoice.getChoicebox().getValue().equals("")){
                this.taskChoice.getChoicebox().setValue("");
            }
        });
        this.projectChoice.getChoicebox().getItems().add("Personal");
        this.projectChoice.getChoicebox().setValue("Personal");


        //
        this.taskChoice = new ChoiceComboBox("Task:   ");
        this.taskChoice.setScaling(true);
        this.taskChoice.setPos(MyPos.CENTER);
        this.taskChoice.setWriteable(true);
        this.taskChoice.removeBorder();
        this.taskChoice.maxWidthProperty().bind(this.widthProperty().divide(10).multiply(4));
        this.taskChoice.getChoicebox().setOnMouseClicked(e -> {

            // Resets the taskId if it gets changed
            if (this.taskId!=-1){
                this.taskId=-1;
            }

            // Fill up the choose task combobox
            if (!this.projectChoice.getChoicebox().getValue().equals("Personal")){
                this.taskChoice.setContent(InformationContainerSingleton.getInstance().getAvailableTaskNamesFromProject(
                                InformationContainerSingleton.getInstance().getProject(
                                        this.projectChoice.getChoicebox().getValue().toString()
                                )
                        )
                );
            } else {
                // If the user is logged in then it loads the users task when hovering none
                if (ConsultantSingleton.getInstance().exists()){
                    this.taskChoice.setContent(InformationContainerSingleton.getInstance().getAvailableTaskNamesFromProject(
                                    InformationContainerSingleton.getInstance().getProject(
                                            null
                                    )
                            )
                    );
                } else {
                    taskChoice.getChoicebox().getItems().clear();
                }
            }
        });

        //
        ArrayList<Node> buttonsAndCounter = new ArrayList<>();

        CustomButton buttonMinus = new CustomButton().Other().Minus();
        buttonMinus.setOnAction(e -> {
            // Remove focus
            this.setFocused(false);

            // The target
            ControllerSingleton.getInstance().subtractToCounter(this.counter);
        });
        buttonsAndCounter.add(buttonMinus);

        this.counter = new Headline("1");
        this.counter.getLabel().setStyle("-fx-font-size: 25;");
        this.counter.noStyleClass();
        this.counter.setAlignment(Pos.CENTER);
        this.counter.removeBorder();
        buttonsAndCounter.add(this.counter);

        CustomButton buttonAdd = new CustomButton().Other().Add();
        buttonAdd.setOnAction(e -> {
            // Remove focus
            this.setFocused(false);

            // The target
            ControllerSingleton.getInstance().addToCounter(this.counter);
        });
        buttonsAndCounter.add(buttonAdd);

        //
        this.buttonsCounterBar = new NodeBarH(buttonsAndCounter);
        this.buttonsCounterBar.setAlignment(Pos.CENTER);
        this.buttonsCounterBar.removeBorder();
        this.buttonsCounterBar.prefWidthProperty().bind(this.widthProperty().divide(10).multiply(3));


        //
        ArrayList<Node> buttonsRemoveDone = new ArrayList<>();

        CustomButton buttonDelete = new CustomButton().Other().Delete();
        buttonDelete.setOnAction(e -> {

            // Remove this node from active nodepage
            InformationContainerSingleton.getInstance().getActiveNodePage().removeNode(this);

            // Remove this from the information container
            ControllerSingleton.getInstance().removeTasklineInDoToday(this);

        });
        buttonsRemoveDone.add(buttonDelete);

        CustomButton buttonSaveEdit = new CustomButton().Other().Accept();
        buttonSaveEdit.setOnAction(e -> {
            if (this.taskChoice.getChoicebox().getValue() != null
                    && !this.taskChoice.getChoicebox().getValue().equals("")
                    && !counter.getLabel().getText().equals("0")){

                // Does the task already exist?
                if (InformationContainerSingleton.getInstance().getTask(this.taskChoice.getChoicebox().getValue().toString())!=null
                        && ConsultantSingleton.getInstance().exists()){
                    // It does exist

                    System.out.println("Exists just assigning - Exists");

                    // Set the id of the task
                    this.taskId=InformationContainerSingleton.getInstance().getTask(
                            this.taskChoice.getChoicebox().getValue().toString()
                    ).getId();
                } else {
                    // It does not exist

                    if (ConsultantSingleton.getInstance().exists()){
                        // User does exists

                        // If no project is selected set projectId to null
                        if (this.projectChoice.getChoicebox().getValue()!=null && !this.projectChoice.getChoicebox().getValue().equals("")){
                            this.projectId=null;
                        }

                        // Reset the taskId if it has been changed
                        this.taskId=-1;

                        // The Task is saved to DB
                        ControllerSingleton.getInstance().updateTaskDB(new Task(
                                        this.taskId,
                                        ConsultantSingleton.getInstance().getEmail(),
                                        this.projectId,
                                        this.taskChoice.getChoicebox().getValue().toString(),
                                        this.taskTimeSpent,
                                        this.taskDone,
                                        0
                                )
                        );

                        // Set the id of the task
                        this.taskId=InformationContainerSingleton.getInstance().getTask(
                                this.taskChoice.getChoicebox().getValue().toString()
                        ).getId();

                    } else {


                        System.out.println("Tryed to Save / Update user don't exist - Saved Temp");
                    }

                }

                System.out.println("Project: "+this.projectId+" / Task: "+this.taskId);

                // Change activePomodoroAmount
                ControllerSingleton.getInstance().addActivePomodoro(Integer.parseInt(this.counter.getLabel().getText()));

                // Set new title to the timertype
                TimerSingleton.getInstance().setTimerTypeTaskWithTaskName();

                // Change to show setup
                changeTasklineSetup();
                this.editing = false;

            } else {
                // Error no task
                if (this.taskChoice.getChoicebox().getValue()==null || this.taskChoice.getChoicebox().getValue().equals("")) {
                    if (!this.taskChoice.getStyleClass().contains("choice-combobox-error")){
                        this.taskChoice.getStyleClass().add("choice-combobox-error");
                    }
                } else this.taskChoice.getStyleClass().remove("choice-combobox-error");
            }
        });
        buttonsRemoveDone.add(buttonSaveEdit);

        //
        this.buttonsRemoveDoneContainerBar = new NodeBarH(buttonsRemoveDone);
        this.buttonsRemoveDoneContainerBar.setAlignment(Pos.CENTER);
        this.buttonsRemoveDoneContainerBar.removeBorder();
        this.buttonsRemoveDoneContainerBar.prefWidthProperty().bind(this.widthProperty().divide(10).multiply(1));


        // Bind the disable function to the timer
        this.diabled.bind(TimerSingleton.getInstance().timeRunningProperty());
        // The different things that gets disabled, when the timer is running
        this.taskChoice.disableProperty().bind(this.diabled);
        this.projectChoice.disableProperty().bind(this.diabled);
        this.buttonsCounterBar.disableProperty().bind(this.diabled);
        this.buttonsRemoveDoneContainerBar.disableProperty().bind(this.diabled);

    }

    private void showSetup(){

        //
        this.projectChoiceShow = new ChoiceComboBox("Project:");
        this.projectChoiceShow.setScaling(true);
        this.projectChoiceShow.setPos(MyPos.CENTER);
        this.projectChoiceShow.removeBorder();
        this.projectChoiceShow.setDisable(true);
        this.projectChoiceShow.maxWidthProperty().bind(this.widthProperty().divide(10).multiply(3));

        //
        this.taskChoiceShow = new ChoiceComboBox("Task:   ");
        this.taskChoiceShow.setScaling(true);
        this.taskChoiceShow.setPos(MyPos.CENTER);
        this.taskChoiceShow.setWriteable(true);
        this.taskChoiceShow.removeBorder();
        this.taskChoiceShow.setDisable(true);

        this.pomodorosHeadline = new Headline("");
        this.pomodorosHeadline.getLabel().setStyle("-fx-font-size: 25");
        this.pomodorosHeadline.noStyleClass();
        this.pomodorosHeadline.setShape(MyShape.ROUND);
        this.pomodorosHeadline.setScaling(MyScaling.SMALL);
        this.pomodorosHeadline.getLabel().textProperty().bind(this.counter.getLabel().textProperty());

        this.pomodoroHeadlineBar = new NodeBarH(pomodorosHeadline);
        this.pomodoroHeadlineBar.setShape(MyShape.ROUND);
        this.pomodoroHeadlineBar.setAlignment(Pos.CENTER);
        this.pomodoroHeadlineBar.removeBorder();
        this.pomodoroHeadlineBar.prefWidthProperty().bind(this.widthProperty().divide(10).multiply(1));


        //
        ArrayList<Node> arrayListButtonsEditFinish = new ArrayList<>();

        CustomButton buttonFinish = new CustomButton().Other().Accept();
        buttonFinish.setOnAction(e -> {

            // Set done
            this.taskDone=true;

            if (ConsultantSingleton.getInstance().exists()){

                int hh = InformationContainerSingleton.getInstance().getTask(this.taskId).getTime().getHours()
                        + TimerSingleton.getInstance().getCurrentTimeSpent().getHours() -
                        TimerSingleton.getInstance().getTaskOffsetTime().getHours();
                int mm = InformationContainerSingleton.getInstance().getTask(this.taskId).getTime().getMinutes()
                        + TimerSingleton.getInstance().getCurrentTimeSpent().getMinutes() -
                        TimerSingleton.getInstance().getTaskOffsetTime().getMinutes();
                int ss = InformationContainerSingleton.getInstance().getTask(this.taskId).getTime().getSeconds()
                        + TimerSingleton.getInstance().getCurrentTimeSpent().getSeconds() -
                        TimerSingleton.getInstance().getTaskOffsetTime().getSeconds();

                System.out.println("here"+Time.valueOf(hh+":"+mm+":"+ss));

                // The Task is updated in the DB
                ControllerSingleton.getInstance().updateTaskDB(new Task(
                                this.taskId,
                                ConsultantSingleton.getInstance().getEmail(),
                                this.projectId,
                                this.taskChoice.getChoicebox().getValue().toString(),
                                Time.valueOf(hh+":"+mm+":"+ss),
                                this.taskDone,
                                0
                        )
                );
            }

            // Change activePomodoroAmount
            ControllerSingleton.getInstance().removeActivePomodoro(Integer.parseInt(this.counter.getLabel().getText()));

            // Remove this node from active nodepage
            InformationContainerSingleton.getInstance().getActiveNodePage().removeNode(this);

            // Remove this from the information container
            ControllerSingleton.getInstance().removeTasklineInDoToday(this);

            // Add 1 pomodoro to the next task in line
            if (InformationContainerSingleton.getInstance().getDoTodayList().size()>0){
                ((Taskline)InformationContainerSingleton.getInstance().getDoTodayList().get(0)).getCounter().getLabel().setText(""+
                        (Integer.parseInt(((Taskline)InformationContainerSingleton.getInstance().getDoTodayList().get(0)).getCounter().getLabel().getText())+1)
                );
            }

            // Set the time offset
            TimerSingleton.getInstance().setTaskOffsetTime();

            // Set new title to the timertype
            TimerSingleton.getInstance().setTimerTypeTaskWithTaskName();

        });
        arrayListButtonsEditFinish.add(buttonFinish);


        CustomButton buttonEdit = new CustomButton().Other().Edit();
        buttonEdit.setOnAction(e -> {

            changeTasklineSetup();
            this.editing = true;

            // Change activePomodoroAmount
            ControllerSingleton.getInstance().removeActivePomodoro(Integer.parseInt(this.counter.getLabel().getText()));

        });
        arrayListButtonsEditFinish.add(buttonEdit);

        //
        this.buttonsEditFinishBar = new NodeBarH(arrayListButtonsEditFinish);
        this.buttonsEditFinishBar.setAlignment(Pos.CENTER_RIGHT);
        this.buttonsEditFinishBar.removeBorder();
        this.buttonsEditFinishBar.prefWidthProperty().bind(this.widthProperty().divide(10).multiply(1));

        // The different things that gets disabled, when the timer is running
        buttonEdit.disableProperty().bind(this.diabled);
    }

    public void changeTasklineSetup(){
        if (this.editing){
            this.editing = false;
            setShowSetup();
        } else {
            this.editing = true;
            setEditSetup();
        }
    }

    /**
     *
     */
    private void setShowOnlySetup(){
        // Set children
        this.getChildren().setAll(taskChoiceShow);
        this.taskChoiceShow.maxWidthProperty().bind(this.widthProperty());
    }

    /**
     *
     */
    public void setShowSetup(){

        // Update information
        if (this.projectChoice.getChoicebox().getValue().equals("Personal")){
            this.projectChoiceShow.getChoicebox().setValue("Personal");
        } else{
            this.projectChoiceShow.getChoicebox().setValue(this.projectChoice.getChoicebox().getValue().toString());
        }
        this.taskChoiceShow.getChoicebox().setValue(this.taskChoice.getChoicebox().getValue().toString());

        // Change the setup
        if (this.projectChoice.getChoicebox().getValue().equals("Personal")){
            this.getChildren().setAll(taskChoiceShow,pomodoroHeadlineBar,buttonsEditFinishBar);
            this.taskChoiceShow.maxWidthProperty().bind(this.widthProperty().divide(10).multiply(8));
        } else {
            this.getChildren().setAll(projectChoiceShow,taskChoiceShow,pomodoroHeadlineBar,buttonsEditFinishBar);
            this.taskChoiceShow.maxWidthProperty().bind(this.widthProperty().divide(10).multiply(5));
        }


    }

    /**
     *
     */
    public void setEditSetup(){
        // Add the content to this
        this.getChildren().setAll(projectChoice,taskChoice,buttonsCounterBar,buttonsRemoveDoneContainerBar);
    }

}
