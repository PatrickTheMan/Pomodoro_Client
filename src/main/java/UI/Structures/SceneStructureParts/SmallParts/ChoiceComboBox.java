package UI.Structures.SceneStructureParts.SmallParts;

import Application.Singleton.ControllerSingleton;
import UI.Enums.MyPos;
import UI.Enums.MyScaling;
import UI.Enums.MyShape;
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
     * */
    public ChoiceComboBox(String text){

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

        comboBox.setVisibleRowCount(5);
        // Set the selection & hover function
        comboBox.focusedProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setSelected(this,newVal);
        });
        comboBox.hoverProperty().addListener((obs, old, newVal) -> {
            ControllerSingleton.getInstance().setHovered(this,newVal);
        });

        // Set the content of the class object
        this.getChildren().addAll(textLabel,comboBox);

        // Set alignment
        this.setAlignment(Pos.TOP_LEFT);

    }

    public void setShape(MyShape shape){
        switch (shape){
            case ROUND -> this.setStyle("-fx-background-radius: 10; -fx-border-radius: 10;");
            case HALFROUND -> {this.setStyle("-fx-background-radius: 10 10 0 0; -fx-border-radius: 10 10 0 0;");}
            case NOTROUND -> this.setStyle("-fx-background-radius: 0; -fx-border-radius: 0;");
        }
    }

    public void setContent(ArrayList<String> choices){
        // Clear
        this.comboBox.getItems().clear();
        // Add the choices to the combobox
        for (String s:choices) {
            comboBox.getItems().add(s);
        }
    }

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

    public void setWriteable(boolean writeable){
        comboBox.setEditable(writeable);
        comboBox.getStyleClass().add("choice-combobox-combobox-writable");
    }

    public void removeBorder(){
        // Make this object have nonvisible borders
        this.setStyle("-fx-border-color: -fx-color2");
    }

    public void setScaling(int width, int height){
        // Set the preferred label size
        textLabel.setMinWidth(width/3);
        textLabel.setMinHeight(height/3);

        // Set the preferred size of the textfield to be max length
        comboBox.setMinWidth(width/3*2);
    }

    public void setScaling(boolean labelRemoval){

        if (labelRemoval){
            // Set the preferred label size
            textLabel.prefWidthProperty().bind(this.widthProperty().divide(2));
            textLabel.prefHeightProperty().bind(this.prefHeightProperty().divide(2));

            // Set the preferred size of the textfield to be max length
            comboBox.prefWidthProperty().bind(this.widthProperty());

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
        } else {
            // Set the preferred label size
            textLabel.prefWidthProperty().bind(this.widthProperty().divide(2));
            textLabel.prefHeightProperty().bind(this.prefHeightProperty().divide(2));

            // Set the preferred size of the textfield to be max length
            comboBox.prefWidthProperty().bind(this.widthProperty());
        }
    }

}

