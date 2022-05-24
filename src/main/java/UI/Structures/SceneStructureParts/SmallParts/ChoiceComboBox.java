package UI.Structures.SceneStructureParts.SmallParts;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

/**
 * A simple choice line with a combobox
 * @author Patrick G. Schemel
 * @version 1.0
 */
public class ChoiceComboBox extends HBox {

    private Label textLabel;
    private ComboBox choicebox;

    /**
     *
     * @param text
     * @param choices
     */
    public ChoiceComboBox(String text, ArrayList<String> choices){

        // Shared setup of the Hbox
        normalSetup(false,text, choices);

        // Set the preferred label size
        textLabel.prefWidthProperty().bind(this.widthProperty().divide(2));
        textLabel.prefHeightProperty().bind(this.prefHeightProperty().divide(2));

        // Set the preferred size of the textfield to be max length
        choicebox.prefWidthProperty().bind(this.widthProperty());

    }

    /**
     *
     * @param writeAble
     * @param text
     * @param choices
     */
    public ChoiceComboBox(boolean writeAble,String text, ArrayList<String> choices){

        // Shared setup of the Hbox
        normalSetup(writeAble,text, choices);

        // Set the preferred label size
        textLabel.prefWidthProperty().bind(this.widthProperty().divide(2));
        textLabel.prefHeightProperty().bind(this.prefHeightProperty().divide(2));

        // Set the preferred size of the textfield to be max length
        choicebox.prefWidthProperty().bind(this.widthProperty());
        choicebox.prefHeightProperty().bind(this.heightProperty());

    }

    /**
     *
     * @param text
     * @param choices
     * @param width
     * @param height
     */
    public ChoiceComboBox(String text, ArrayList<String> choices ,double width, double height){

        // Shared setup of the Hbox
        normalSetup(false,text, choices);

        // Set the preferred label size
        textLabel.setMinWidth(width/3);
        textLabel.setMinHeight(height/3);

        // Set the preferred size of the textfield to be max length
        choicebox.setMinWidth(width/3*2);
        choicebox.setMinHeight(height/3*2);

    }

    /**
     *
     * @param writeAble
     * @param text
     * @param choices
     * @param width
     * @param height
     */
    public ChoiceComboBox(boolean writeAble,String text, ArrayList<String> choices ,double width, double height){

        // Shared setup of the Hbox
        normalSetup(writeAble,text, choices);

        // Set the preferred label size
        textLabel.setMinWidth(width/3);
        textLabel.setMinHeight(height/3);

        // Set the preferred size of the textfield to be max length
        choicebox.setMinWidth(width/3*2);
        choicebox.setMinHeight(height/3*2);

    }

    /**
     *
     * @param text
     * @param choices
     */
    private void normalSetup(boolean writeAble,String text, ArrayList<String> choices){

        // Make this object use the custom css styling
        this.getStyleClass().add("choice-combobox");

        // Make the label and use custom css styling
        textLabel = new Label(text);
        textLabel.getStyleClass().add("choice-combobox-label");

        // Make the textfield, use custom css styling, set if write able and limit the shown items to 5
        choicebox = new ComboBox();
        choicebox.getStyleClass().add("choice-combobox-combobox");
        choicebox.setEditable(writeAble);
        choicebox.setVisibleRowCount(5);


        // Add the choices to the combobox
        for (String s:choices) {
            choicebox.getItems().add(s);
        }

        // Set the content of the class object
        this.getChildren().addAll(textLabel,choicebox);

        // Set alignment
        this.setAlignment(Pos.TOP_LEFT);

    }

}

