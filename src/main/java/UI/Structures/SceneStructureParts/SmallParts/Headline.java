package UI.Structures.SceneStructureParts.SmallParts;

import UI.Enums.MyPos;
import UI.Enums.MyShape;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class Headline extends HBox {

    public Headline(String text){
        // Get normalt setup
        NormalSetup(text, false);
    }

    public Headline(String text, ArrayList<Node> nodes){
        // Get normalt setup
        NormalSetup(text, false);

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
            NormalSetup(text,false);
        } else {
            // Get normalt setup
            NormalSetup(text,true);
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

    public Headline(String text, ArrayList<Node> nodes, MyPos pos, MyShape shape){

        // Get the normal setup with the right scaling
        if (shape.equals(MyShape.HALFROUND.HALFROUND)){
            // Get normalt setup
            NormalSetup(text,false);
        } else {
            // Get normalt setup
            NormalSetup(text,true);
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

        // Initiate a button container
        HBox buttonContainter = new HBox();

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("headline-buttonContainer");

        // Add the other nodes
        buttonContainter.getChildren().addAll(nodes);

        // Add the container
        this.getChildren().add(buttonContainter);
    }

    private void scalingSmall(Label label){
        // Make the line adjustable to the width of it (Removes the label if it can't be seen anyways)
        this.widthProperty().addListener((obs, old, newVal) -> {
            // Changes the size of the label text
            if (newVal.intValue()/15>=25){
                label.setStyle("-fx-font-size: 25;");
            }
            if (newVal.intValue()/15<25 && newVal.intValue()/15>15){
                label.setStyle("-fx-font-size: "+newVal.intValue()/15+";");
            }
        });
    }

    private void scalingBig(Label label){
        // Make the line adjustable to the width of it (Removes the label if it can't be seen anyways)
        this.widthProperty().addListener((obs, old, newVal) -> {
            // Changes the size of the label text
            if (newVal.intValue()/15>=35){
                label.setStyle("-fx-font-size: 35;");
            }
            if (newVal.intValue()/15<35 && newVal.intValue()/15>25){
                label.setStyle("-fx-font-size: "+newVal.intValue()/15+";");
            }
        });
    }

    private void NormalSetup(String text, boolean big){
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

        // Set the scaling of the label text
        if (big){
            scalingBig(label);
        } else {
            scalingSmall(label);
        }
    }

}
