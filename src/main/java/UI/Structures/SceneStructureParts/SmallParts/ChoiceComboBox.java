package UI.Structures.SceneStructureParts.SmallParts;

import Application.Singleton.ControllerSingleton;
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
    private ComboBox comboBox;

    public Label getTextLabel() {
        return textLabel;
    }

    public ComboBox getChoicebox() {
        return comboBox;
    }

    /**
     *
     * @param text
     * @param choices
     */
    public ChoiceComboBox(String text, ArrayList<String> choices){

        // Shared setup of the Hbox
        NormalSetup(false,text, choices);

        // Set the preferred label size
        textLabel.prefWidthProperty().bind(this.widthProperty().divide(2));
        textLabel.prefHeightProperty().bind(this.prefHeightProperty().divide(2));

        // Set the preferred size of the textfield to be max length
        comboBox.prefWidthProperty().bind(this.widthProperty());

    }

    /**
     *
     * @param writeAble
     * @param text
     * @param choices
     */
    public ChoiceComboBox(boolean writeAble,String text, ArrayList<String> choices){

        // Shared setup of the Hbox
        NormalSetup(writeAble,text, choices);

        // Set the preferred label size
        textLabel.prefWidthProperty().bind(this.widthProperty().divide(2));
        textLabel.prefHeightProperty().bind(this.prefHeightProperty().divide(2));

        // Set the preferred size of the textfield to be max length
        comboBox.prefWidthProperty().bind(this.widthProperty());

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
        NormalSetup(false,text, choices);

        // Set the preferred label size
        textLabel.setMinWidth(width/3);
        textLabel.setMinHeight(height/3);

        // Set the preferred size of the textfield to be max length
        comboBox.setMinWidth(width/3*2);

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
        NormalSetup(writeAble,text, choices);

        // Set the preferred label size
        textLabel.setMinWidth(width/3);
        textLabel.setMinHeight(height/3);

        // Set the preferred size of the textfield to be max length
        comboBox.setMinWidth(width/3*2);

    }

    /**
     *
     * @param text
     * @param choices
     */
    private void NormalSetup(boolean writeAble,String text, ArrayList<String> choices){

        // Make this object use the custom css styling
        this.getStyleClass().add("choice-combobox");

        // Make the label and use custom css styling
        textLabel = new Label(text);
        textLabel.getStyleClass().add("choice-combobox-label");

        // Remove focus option
        textLabel.setFocusTraversable(false);

        // Make the textfield, use custom css styling, set if write able and limit the shown items to 5
        comboBox = new ComboBox();
        comboBox.getStyleClass().add("choice-combobox-combobox");
        comboBox.setEditable(writeAble);
        comboBox.setVisibleRowCount(5);
        // Set the selection function
        comboBox.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });

        // Add the choices to the combobox
        for (String s:choices) {
            comboBox.getItems().add(s);
        }

        // Set the content of the class object
        this.getChildren().addAll(textLabel,comboBox);

        // Set alignment
        this.setAlignment(Pos.TOP_LEFT);

        // Make the line adjustable to the width of it (Removes the label if it can't be seen anyways)
        this.widthProperty().addListener((obs, old, newVal) -> {

            // Removes the label or adds it
            if (newVal.intValue()<textLabel.getText().length()*30){
                this.getChildren().setAll(comboBox);
                comboBox.setPromptText(textLabel.getText());
            } else {
                this.getChildren().setAll(textLabel,comboBox);
                comboBox.setPromptText("");
            }

            // Changes the size of the label text
            if (newVal.intValue()/40>=16){
                textLabel.setStyle("-fx-font-size: 16;");
            }
            if (newVal.intValue()/40<16 && newVal.intValue()/40>11){
                textLabel.setStyle("-fx-font-size: "+newVal.intValue()/40+";");
            }

        });

    }

}

