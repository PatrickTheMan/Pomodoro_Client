package UI.Structures.SceneStructureParts.SmallParts;

import UI.Enums.MyPos;
import UI.Enums.MyShape;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class NodeBarH extends HBox {

    private ArrayList<Node> nodes = new ArrayList<>();


    public ArrayList<Node> getContent() {
        return nodes;
    }

    public void setContent(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public NodeBarH(ArrayList<Node> nodes){

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("node-bar-h");

        // Set alignment
        this.setAlignment(Pos.CENTER_RIGHT);

        // Set the nodes
        this.nodes = nodes;

        // Add in the buttons in the bar
        for (Node n : this.nodes) {
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


    public void setShape(MyShape shape){
        switch (shape){
            case ROUND -> this.setStyle("-fx-background-radius: 10; -fx-border-radius: 10;");
            case HALFROUND -> {this.setStyle("-fx-background-radius: 10 10 0 0; -fx-border-radius: 10 10 0 0;");}
            case NOTROUND -> this.setStyle("-fx-background-radius: 0; -fx-border-radius: 0;");
        }
    }

    public void removeBorder(){
        // Make this object have nonvisible borders
        this.setStyle("-fx-border-color: transparent");
    }

}
