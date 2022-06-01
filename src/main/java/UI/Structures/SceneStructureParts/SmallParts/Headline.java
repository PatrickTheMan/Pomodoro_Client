package UI.Structures.SceneStructureParts.SmallParts;

import UI.Enums.MyPos;
import UI.Enums.MyScaling;
import UI.Enums.MyShape;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class Headline extends HBox {

    private Label label;

    public Label getLabel() {
        return label;
    }

    public Headline(String text){
        // Get normalt setup
        NormalSetup(text, MyScaling.SMALL);
    }

    public Headline(String text, ArrayList<Node> nodes){
        // Get normalt setup
        NormalSetup(text, MyScaling.SMALL);

        // Initiate a button container
        HBox buttonContainter = new HBox();

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("headline-buttonContainer");

        // Add the other nodes
        buttonContainter.getChildren().addAll(nodes);

        // Add the container
        this.getChildren().add(buttonContainter);
    }

    public Headline(String text, MyPos pos, MyShape shape){

        // Get the normal setup with the right scaling
        if (shape.equals(MyShape.HALFROUND)){
            // Get normalt setup
            NormalSetup(text,MyScaling.SMALL);
        } else {
            // Get normalt setup
            NormalSetup(text,MyScaling.BIG);
        }

        // Set the settings
        switch (pos){
            case CENTER -> this.setAlignment(Pos.CENTER);
            case LEFT -> this.setAlignment(Pos.CENTER_LEFT);
            case RIGHT -> this.setAlignment(Pos.CENTER_RIGHT);
        }
        switch (shape){
            case ROUND -> this.setStyle("-fx-background-radius: 10; -fx-border-radius: 10;");
            case HALFROUND -> {
                this.setStyle("-fx-background-radius: 10 10 0 0; -fx-border-radius: 10 10 0 0;");
                // Make this object use the custom-menu css styling
                this.getStyleClass().add("headline-halfround");
            }
            case NOTROUND -> this.setStyle("-fx-background-radius: 0; -fx-border-radius: 0;");
        }
    }

    public Headline(String text, MyPos pos, MyShape shape, int smallestFontSize, int biggestFontSize, int offset){

        // Get the normal setup with the right scaling
        if (shape.equals(MyShape.HALFROUND)){
            // Get normalt setup
            NormalSetup(text,MyScaling.SMALL);
        } else {
            // Get normalt setup
            NormalSetup(text,MyScaling.CUSTOM);
        }

        // Set the settings
        switch (pos){
            case CENTER -> this.setAlignment(Pos.CENTER);
            case LEFT -> this.setAlignment(Pos.CENTER_LEFT);
            case RIGHT -> this.setAlignment(Pos.CENTER_RIGHT);
        }
        switch (shape){
            case ROUND -> this.setStyle("-fx-background-radius: 10; -fx-border-radius: 10;");
            case HALFROUND -> {
                this.setStyle("-fx-background-radius: 10 10 0 0; -fx-border-radius: 10 10 0 0;");
                // Make this object use the custom-menu css styling
                this.getStyleClass().add("headline-halfround");
            }
            case NOTROUND -> this.setStyle("-fx-background-radius: 0; -fx-border-radius: 0;");
        }

        // Set Scaling
        scalingCustom(smallestFontSize,biggestFontSize,offset);
    }



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

    private void scalingCustom(int smallestFont, int biggestFont, int offset){
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

    private void NormalSetup(String text, MyScaling scaling){
        // Make this object use the custom-menu css styling
        this.getStyleClass().add("headline");

        // Add the label
        this.label= new Label();
        this.label.setText(text);

        // Remove focus option
        this.label.setFocusTraversable(false);

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("headline-label");

        // Add content
        this.getChildren().add(this.label);

        // Set alignment
        this.setAlignment(Pos.CENTER);

        // Set the scaling of the label text
        switch (scaling){
            case BIG -> scalingBig();
            case SMALL -> scalingSmall();
        }
    }

}
