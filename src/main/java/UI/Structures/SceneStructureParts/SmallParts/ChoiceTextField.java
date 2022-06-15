package UI.Structures.SceneStructureParts.SmallParts;

import Foundation.Singletons.ControllerSingleton;
import UI.Enums.MyPos;
import UI.Enums.MyShape;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * A simple choice line with a textfield
 * @author Patrick G. Schemel
 * @version 0.1
 */
public class ChoiceTextField extends HBox {

    //region [Variables]

    private Label textLabel;
    private TextField textField;

    //endregion

    //region [Normal Getters & Setters]

    public Label getTextLabel() {
        return textLabel;
    }

    public TextField getTextField() {
        return textField;
    }

    //endregion

    //region [Constructor]

    public ChoiceTextField(String text){

        // Make this object use the custom css styling
        this.getStyleClass().add("choice-textfield");

        // Make the label and use custom css styling
        textLabel = new Label(text);
        textLabel.getStyleClass().add("choice-textfield-label");

        // Remove focus option
        textLabel.setFocusTraversable(false);

        // Make the textfield, use custom css styling and position of the text in the field
        textField = new TextField();
        textField.getStyleClass().add("choice-textfield-textfield");
        textField.setAlignment(Pos.TOP_LEFT);
        // Set the selection & hover function
        textField.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });
        textField.hoverProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setHovered(this,newVal);
        });

        // Set the content of the class object
        this.getChildren().addAll(textLabel,textField);

        // Set alignment
        this.setAlignment(Pos.TOP_LEFT);

    }

    //endregion

    //region [Option Methods]

    /**
     * <Strong>Set the shape of the choiceTextField</Strong>
     * @param shape is the shape chosen from MyShape
     */
    public void setShape(MyShape shape){
        switch (shape){
            case ROUND -> this.setStyle("-fx-background-radius: 10; -fx-border-radius: 10;");
            case HALF_ROUND -> {this.setStyle("-fx-background-radius: 10 10 0 0; -fx-border-radius: 10 10 0 0;");}
            case NOT_ROUND -> this.setStyle("-fx-background-radius: 0; -fx-border-radius: 0;");
        }
    }

    /**
     * <Strong>Set the alignment of the choiceTextField</Strong>
     * @param pos is the position alignment of the choiceTextField
     */
    public void setPos(MyPos pos){
        switch (pos){
            case LEFT ->
                    // Set alignment
                    this.setAlignment(Pos.CENTER_LEFT);
            case CENTER ->
                    // Set alignment
                    this.setAlignment(Pos.CENTER);
            case RIGHT ->
                    // Set alignment
                    this.setAlignment(Pos.CENTER_RIGHT);
        }
    }

    /**
     * <Strong>Sets the border to transparent</Strong>
     */
    public void removeBorder(){
        // Make this object have nonvisible borders
        this.setStyle("-fx-border-color: transparent");
    }

    /**
     * <Strong>Activate scaling for the choiceTextField</Strong>
     * @param labelRemoval is wether or not to remove the label, when the choiceTextField gets to small for the label to be read
     */
    public void setScaling(boolean labelRemoval){

        if (labelRemoval){
            // Set the preferred label size
            textLabel.prefWidthProperty().bind(this.widthProperty().divide(2));
            textLabel.prefHeightProperty().bind(this.prefHeightProperty().divide(2));

            // Set the preferred size of the textfield to be max length
            textField.prefWidthProperty().bind(this.widthProperty());

            // Make the line adjustable to the width of it (Removes the label if it can't be seen anyways)
            this.widthProperty().addListener((obs, old, newVal) -> {

                // Removes the label or adds it
                if (newVal.intValue()<textLabel.getText().length()*30){
                    this.getChildren().setAll(textField);
                    textField.setPromptText(textLabel.getText());
                } else {
                    this.getChildren().setAll(textLabel,textField);
                    textField.setPromptText("");
                }

                // Changes the size of the label text
                if (newVal.intValue()/40>=16){
                    textLabel.setStyle("-fx-font-size: 16;");
                }
                if (newVal.intValue()/40<16 && newVal.intValue()/40>11){
                    textLabel.setStyle("-fx-font-size: "+newVal.intValue()/40+";");
                }

            });
        } else {
            // Set the preferred label size
            textLabel.prefWidthProperty().bind(this.widthProperty().divide(2));
            textLabel.prefHeightProperty().bind(this.prefHeightProperty().divide(2));

            // Set the preferred size of the textfield to be max length
            textField.prefWidthProperty().bind(this.widthProperty());
        }
    }

    //endregion

}
