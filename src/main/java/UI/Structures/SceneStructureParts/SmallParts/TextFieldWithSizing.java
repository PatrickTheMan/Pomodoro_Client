package UI.Structures.SceneStructureParts.SmallParts;

import javafx.scene.control.TextField;

public class TextFieldWithSizing extends TextField {

    public TextFieldWithSizing(){
        // Make this object use the custom-menu css styling
        this.getStyleClass().add("textfield-sizing");

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

    public TextFieldWithSizing(int minFontSize, int maxFontSize){
        // Make this object use the custom-menu css styling
        this.getStyleClass().add("textfield-sizing");

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
