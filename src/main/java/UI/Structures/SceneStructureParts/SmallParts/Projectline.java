package UI.Structures.SceneStructureParts.SmallParts;

import Domain.Project;
import Foundation.Singletons.InformationContainerSingleton;
import UI.Buttons.CustomButton;
import UI.Enums.MyScaling;
import UI.Enums.MyShape;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class Projectline extends HBox {

    private Project project;

    private NodeBarH buttonGotoBar;
    private Headline projectNameHeadline;


    /**
     *
     */
    public Projectline(Project project){

        // Set the project this line works with
        this.project=project;

        // Normal setup
        normalSetup();

    }

    /**
     *
     */
    public Projectline(String customProjectName){

        // Set the project this line works with
        this.project=project;

        // Normal setup
        normalSetup();

        // Change the projectName
        this.projectNameHeadline.getLabel().setText(customProjectName);

    }

    /**
     *
     */
    private void normalSetup(){

        // Make this object use the custom css styling
        this.getStyleClass().add("projectline");

        // Set alignment
        this.setAlignment(Pos.CENTER);

        // Some width property functionality
        this.widthProperty().addListener((obs,old,newVal) -> {

        });

        //
        this.projectNameHeadline = new Headline((this.project!=null ? this.project.getName() : ""));
        this.projectNameHeadline.getLabel().setStyle("-fx-font-size: 25");
        this.projectNameHeadline.noStyleClass();
        this.projectNameHeadline.setShape(MyShape.ROUND);
        this.projectNameHeadline.setScaling(MyScaling.SMALL);
        this.projectNameHeadline.setAlignment(Pos.CENTER_LEFT);
        this.projectNameHeadline.setStyle("-fx-padding: 10");
        this.projectNameHeadline.prefWidthProperty().bind(this.widthProperty().divide(10).multiply(9));

        //
        CustomButton buttonSaveEdit;
        if (this.projectNameHeadline.getLabel().getText().equals("")){
            buttonSaveEdit = new CustomButton().Other().Back();
            buttonSaveEdit.setOnAction(e -> {
                InformationContainerSingleton.getInstance().getProjectsToList();
            });
        } else {
            buttonSaveEdit = new CustomButton().Other().Forward();
            buttonSaveEdit.setOnAction(e -> {
                InformationContainerSingleton.getInstance().getTasksFromProjectToList(InformationContainerSingleton.getInstance().getProject(
                                this.projectNameHeadline.getLabel().getText()
                        )
                );
            });
        }



        //
        this.buttonGotoBar = new NodeBarH(buttonSaveEdit);
        this.buttonGotoBar.setAlignment(Pos.TOP_RIGHT);
        this.buttonGotoBar.removeBorder();
        this.buttonGotoBar.prefWidthProperty().bind(this.widthProperty().divide(10).multiply(1));

        //
        this.getChildren().addAll(this.projectNameHeadline,this.buttonGotoBar);
    }

}
