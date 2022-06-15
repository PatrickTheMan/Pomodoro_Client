package UI.Structures.SceneStructureParts.SmallParts;

import UI.Enums.MyPos;
import UI.Enums.MyShape;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class NodeBarH extends HBox {

    //region [Variables]

    private ArrayList<Node> nodes = new ArrayList<>();

    public ArrayList<Node> getContent() {
        return nodes;
    }

    //endregion

    //region [Normal Getters & Setters]

    public void setContent(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    //endregion

    //region [Constructor]

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

    //endregion

    /**
     * <Strong>Set the alignment of the nodeBarH</Strong>
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
     * <Strong>Set the shape of the nodeBarH</Strong>
     * @param shape is the shape chosen from MyShape
     */
    public void setShape(MyShape shape){
        switch (shape){
            case ROUND -> this.setStyle("-fx-background-radius: 10; -fx-border-radius: 10;");
            case HALF_ROUND -> {this.setStyle("-fx-background-radius: 10 10 0 0; -fx-border-radius: 10 10 0 0;");}
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

}
