package UI.Structures.SceneStructureParts.SmallParts;

import javafx.scene.control.Label;

public class LabelWithSizing extends Label {

    public LabelWithSizing(String text){
        // Make this object use the custom-menu css styling
        this.getStyleClass().add("label-sizing");

        // Set text
        this.setText(text);

        // Remove focus option
        this.setFocusTraversable(false);

        // Make the font size change depending on the size
        this.widthProperty().addListener((obs, old, newVal) -> {
            // Changes the size of the label text
            if (newVal.intValue()/15>=35){
                this.setStyle("-fx-font-size: 35;");
            }
            if (newVal.intValue()/15<35 && newVal.intValue()/15>25){
                this.setStyle("-fx-font-size: "+newVal.intValue()/15+";");
            }
        });
    }

    public LabelWithSizing(String text, int minFontSize, int maxFontSize){
        // Make this object use the custom-menu css styling
        this.getStyleClass().add("label-sizing");

        // Set text
        this.setText(text);

        // Remove focus option
        this.setFocusTraversable(false);

        // Make the font size change depending on the size
        this.widthProperty().addListener((obs, old, newVal) -> {
            // Changes the size of the label text
            if (newVal.intValue()/15>=maxFontSize){
                this.setStyle("-fx-font-size: "+maxFontSize+";");
            }
            if (newVal.intValue()/15<maxFontSize && newVal.intValue()/15>minFontSize){
                this.setStyle("-fx-font-size: "+newVal.intValue()/15+";");
            }
        });
    }

}
