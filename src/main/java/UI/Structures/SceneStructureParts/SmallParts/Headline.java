package UI.Structures.SceneStructureParts.SmallParts;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Headline extends HBox {

    public Headline(String text){

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("headline");

        // Set alignment
        this.setAlignment(Pos.BOTTOM_RIGHT);

        // Add the label
        Label label = new Label();
        label.setText(text);

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("headline-label");

        // Add content
        this.getChildren().add(label);

        // Set alignment
        this.setAlignment(Pos.CENTER);

        // Make the line adjustable to the width of it (Removes the label if it can't be seen anyways)
        this.widthProperty().addListener((obs, old, newVal) -> {
            if (newVal.intValue()/15<25 && newVal.intValue()/15>15){
                label.setStyle("-fx-font-size: "+newVal.intValue()/15+";");
            }
        });

    }

}
