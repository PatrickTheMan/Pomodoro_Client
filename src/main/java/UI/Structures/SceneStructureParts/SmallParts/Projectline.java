package UI.Structures.SceneStructureParts.SmallParts;

import Domain.Project;
import Domain.Singletons.InformationContainerSingleton;
import UI.Buttons.CustomButton;
import UI.Enums.MyScaling;
import UI.Enums.MyShape;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 * @author Patrick G. Schemel
 */
public class Projectline extends HBox {

    //region [Variables]

    private Project project;

    private NodeBarH buttonGotoBar;
    private Headline projectNameHeadline;

    //endregion

    //region [Constructor]

    public Projectline(Project project){

        // Set the project this line works with
        this.project=project;

        // Normal setup
        normalSetup();

    }

    public Projectline(String customProjectName){

        // Set the project this line works with
        this.project=project;

        // Normal setup
        normalSetup();

        // Change the projectName
        this.projectNameHeadline.getLabel().setText(customProjectName);

    }

    //endregion

    //region [Normal Setup]

    /**
     * <Strong>The normal setup for the projectline</Strong>
     */
    private void normalSetup(){

        // Make this object use the custom css styling
        this.getStyleClass().add("projectline");

        // Set alignment
        this.setAlignment(Pos.CENTER);

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
            buttonSaveEdit = new CustomButton().other().back();
            buttonSaveEdit.setOnAction(e -> {
                InformationContainerSingleton.getInstance().getProjectsToList();
            });
        } else {
            buttonSaveEdit = new CustomButton().other().forward();
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

    //endregion

}
