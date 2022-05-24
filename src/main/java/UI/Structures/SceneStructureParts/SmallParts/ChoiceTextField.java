package UI.Structures.SceneStructureParts.SmallParts;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * A simple choice line with a textfield
 * @author Patrick G. Schemel
 * @version 1.0
 */
public class ChoiceTextField extends HBox {

    private Label textLabel;
    private TextField textField;

    /**
     *
     * @param text
     */
    public ChoiceTextField(String text){

        // Shared setup of the Hbox
        normalSetup(text);

        //Set the prefered label size
        textLabel.prefWidthProperty().bind(this.widthProperty().divide(2));
        textLabel.prefHeightProperty().bind(this.prefHeightProperty().divide(2));


        // Set the prefered size of the textfield to be max length
        textField.prefWidthProperty().bind(this.widthProperty());
        textField.prefHeightProperty().bind(this.heightProperty());

    }

    /**
     *
     * @param text
     * @param width
     * @param height
     */
    public ChoiceTextField(String text,double width, double height){

        // Shared setup of the Hbox
        normalSetup(text);

        // Set the prefered label size
        textLabel.setMinWidth(width/3);
        textLabel.setMinHeight(height/3);

        // Set the prefered size of the textfield to be max length
        textField.setMinWidth(width/3*2);
        textField.setMinHeight(height/3*2);

    }

    /**
     *
     * @param text
     */
    private void normalSetup(String text){

        // Make this object use the custom css styling
        this.getStyleClass().add("choice-textfield");

        // Make the label and use custom css styling
        textLabel = new Label(text);
        textLabel.getStyleClass().add("choice-textfield-label");

        // Make the textfield, use custom css styling and position of the text in the field
        textField = new TextField();
        textField.getStyleClass().add("choice-textfield-textfield");
        textField.setAlignment(Pos.TOP_LEFT);

        // Set the content of the class object
        this.getChildren().addAll(textLabel,textField);

        // Set alignment
        this.setAlignment(Pos.TOP_LEFT);

    }

}
