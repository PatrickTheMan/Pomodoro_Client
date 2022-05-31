package UI.Structures.SceneStructureParts.SmallParts;

import UI.Enums.MyPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class NodeBarH extends HBox {

    public NodeBarH(ArrayList<Node> nodes){

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("node-bar-h");

        // Set alignment
        this.setAlignment(Pos.CENTER_RIGHT);

        // Add in the buttons in the bar
        for (Node n : nodes) {
            // Make this button use the custom css styling
            n.getStyleClass().add("node-bar-h-node");
            // Add the button
            this.getChildren().add(n);
        }

    }

    public NodeBarH(Node node){

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("node-bar-h");

        // Set alignment
        this.setAlignment(Pos.CENTER_RIGHT);

        // Make this button use the custom-menu css styling
        node.getStyleClass().add("node-bar-h-node");

        // Add in the button in the bar
        this.getChildren().add(node);

    }

    public NodeBarH(ArrayList<Node> nodes, MyPos pos){

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("node-bar-h");

        // Set alignment
        switch (pos){
            case RIGHT -> this.setAlignment(Pos.CENTER_RIGHT);
            case CENTER -> this.setAlignment(Pos.CENTER);
            case LEFT -> this.setAlignment(Pos.CENTER_LEFT);
        }

        // Add in the buttons in the bar
        for (Node n : nodes) {
            // Make this button use the custom css styling
            n.getStyleClass().add("node-bar-h-node");
            // Add the button
            this.getChildren().add(n);
        }

    }

    public NodeBarH(Node node, MyPos pos){

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("node-bar-h");

        // Set alignment
        switch (pos){
            case RIGHT -> this.setAlignment(Pos.CENTER_RIGHT);
            case CENTER -> this.setAlignment(Pos.CENTER);
            case LEFT -> this.setAlignment(Pos.CENTER_LEFT);
        }

        // Make this button use the custom-menu css styling
        node.getStyleClass().add("node-bar-h-node");

        // Add in the button in the bar
        this.getChildren().add(node);

    }

}
