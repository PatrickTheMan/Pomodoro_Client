package UI.Structures.SceneStructureParts.SmallParts;

import UI.Enums.MyPos;
import UI.Enums.MyScaling;
import UI.Enums.MyShape;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * A simple headline
 * @author Patrick G. Schemel
 * @version 0.1
 */
public class Headline extends HBox {

    //region [Variables]

    private Label label;

    //endregion

    //region [Normal Getters & Setters]

    public Label getLabel() {
        return label;
    }

    //endregion

    //region [Constructor]

    public Headline(String text){

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("headline");

        // Add the label
        this.label= new Label();
        this.label.setText(text);

        // Remove focus option
        this.label.setFocusTraversable(false);

        // Make this object use the custom-menu css styling
        this.label.getStyleClass().add("headline-label");

        // Add content
        this.getChildren().add(this.label);

        // Set alignment
        this.setAlignment(Pos.CENTER);

    }

    //endregion

    //region [Option Methods]

    /**
     * <Strong>Remove the styling from this headline</Strong>
     */
    public void noStyleClass(){
        this.getStyleClass().setAll("");
    }

    /**
     * <Strong>Activate scaling (small/big)</Strong>
     * @param scaling is the scaling chosen with MyScaling
     */
    public void setScaling(MyScaling scaling){
        // Set the scaling of the label text
        switch (scaling){
            case BIG -> scalingBig();
            case SMALL -> scalingSmall();
        }
    }

    /**
     * <Strong>Activate scaling (custom)</Strong>
     * @param smallestFontSize is the smallest font size
     * @param biggestFontSize is the biggest font size
     * @param offset is the offset for the with, so it determines, when the scaling starts and stops
     */
    public void setScaling(int smallestFontSize, int biggestFontSize, int offset){
        // Set Scaling
        scalingCustom(smallestFontSize,biggestFontSize,offset);
    }

    /**
     * <Strong>Set the alignment of the headline</Strong>
     * @param pos is the position alignment chosen from MyPos
     */
    public void setPos(MyPos pos){
        // Set the settings
        switch (pos){
            case CENTER -> this.setAlignment(Pos.CENTER);
            case LEFT -> this.setAlignment(Pos.CENTER_LEFT);
            case RIGHT -> this.setAlignment(Pos.CENTER_RIGHT);
        }
    }

    /**
     * <Strong>Set the shape of the headline</Strong>
     * @param shape is the shape chosen from MyShape
     */
    public void setShape(MyShape shape){
        switch (shape){
            case ROUND -> this.setStyle("-fx-background-radius: 10; -fx-border-radius: 10;");
            case HALF_ROUND -> {
                this.setStyle("-fx-background-radius: 10 10 0 0; -fx-border-radius: 10 10 0 0;");
                // Make this object use the custom-menu css styling
                this.getStyleClass().add("headline-halfround");
            }
            case NOT_ROUND -> this.setStyle("-fx-background-radius: 0; -fx-border-radius: 0;");
        }
    }

    /**
     * <Strong>Set the borders to transparent</Strong>
     */
    public void removeBorder(){
        // Make this object have nonvisible borders
        this.setStyle("-fx-border-color: transparent");
    }

    /**
     * <Strong>Scale the headline with the small standard (15 - 25 font size)</Strong>
     */
    private void scalingSmall(){
        // Make the line adjustable to the width of it (Removes the label if it can't be seen anyways)
        this.widthProperty().addListener((obs, old, newVal) -> {
            // Changes the size of the label text
            if (newVal.intValue()/15>=25 && !this.label.getStyle().contains("-fx-font-size: 25;")){
                this.label.setStyle("-fx-font-size: 25;");
            }
            if (newVal.intValue()/15<25 && newVal.intValue()/15>15){
                this.label.setStyle("-fx-font-size: "+newVal.intValue()/15+";");
            }
        });
    }

    /**
     * <Strong>Scale the headline with the big standard (25 - 35 font size)</Strong>
     */
    private void scalingBig(){
        // Make the line adjustable to the width of it (Removes the label if it can't be seen anyways)
        this.widthProperty().addListener((obs, old, newVal) -> {
            // Changes the size of the label text
            if (newVal.intValue()/15>=35 && !this.label.getStyle().contains("-fx-font-size: 35;")){
                this.label.setStyle("-fx-font-size: 35;");
            }
            if (newVal.intValue()/15<35 && newVal.intValue()/15>25){
                this.label.setStyle("-fx-font-size: "+newVal.intValue()/15+";");
            }
        });
    }

    /**
     * <Strong>Scale the headline with the custom (smallestfont - biggestfont font size)</Strong>
     * @param smallestFont is the smallest font size
     * @param biggestFont is the biggest font size
     * @param offset is the offset, this determines at what width of the headline the scaling starts and stops
     */
    private void scalingCustom(int smallestFont, int biggestFont, int offset){
        // Make this object use the custom-menu css styling
        this.getStyleClass().add("headline-sizing");
        // Make the line adjustable to the width of it (Removes the label if it can't be seen anyways)
        this.widthProperty().addListener((obs, old, newVal) -> {
            // Changes the size of the label text
            if (newVal.intValue()/offset>=biggestFont && !this.label.getStyle().contains("-fx-font-size: "+biggestFont+";")){
                this.label.setStyle("-fx-font-size: "+biggestFont+";");
            }
            if (newVal.intValue()/offset<biggestFont && newVal.intValue()/offset>smallestFont){
                this.label.setStyle("-fx-font-size: "+newVal.intValue()/offset+";");
            }
        });
    }

    //endregion

}
