package UI.Structures.SceneStructureParts.SmallParts;

import UI.Buttons.CustomButton;
import UI.Enums.MyPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class NodePages extends VBox {

    private ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<Node> buttons= new ArrayList<>();

    private VBox itemContainer;

    public NodePages(ArrayList<Node> nodes ,int nodesPrPage, int minHeight){

        // Make this object use the custom css styling
        this.getStyleClass().add("node-pages");

        // Create the content container
        ScrollPane contentContainer = new ScrollPane();

        // Make the contentContainer use the custom css styling
        contentContainer.getStyleClass().add("node-pages-container");

        // Set alignment
        this.setAlignment(Pos.TOP_CENTER);

        // Add in the node in arraylist
        for (Node n : nodes) {
            // Make this node use the custom css styling
            n.getStyleClass().add("node-pages-container-node");
            // Add the node
            this.nodes.add(n);
        }

        // Create the item container and add elements
        this.itemContainer = new VBox();
        // Make this container use the custom css styling
        this.itemContainer.getStyleClass().add("node-pages-container");

        // Add the buttons
        float f = 0;
        do {

            // Increment i
            f++;

            // Create buttons equal to amount needed
            CustomButton customButton = new CustomButton(""+(int)f);
            // Make this node use the custom css styling
            customButton.getStyleClass().add("node-pages-button-node");
            // Add functionality to the button
            customButton.setOnAction(e -> {
                updatePage(Integer.parseInt(customButton.getText()),nodesPrPage);

                // Make all other buttons not marked
                for (Node n: buttons) {
                    if (n.getStyleClass().contains("node-pages-button-node-marked")){
                        n.getStyleClass().remove("node-pages-button-node-marked");
                    }
                }

                // Make this button use the custom-button marked css styling
                if (customButton.getStyleClass().contains("node-pages-button-node-marked")){
                    customButton.getStyleClass().remove("node-pages-button-node-marked");
                } else {
                    customButton.getStyleClass().add("node-pages-button-node-marked");
                }
            });


            // Add to arraylist
            buttons.add(customButton);
        } while (f < this.nodes.size()/(float)nodesPrPage);

        // Create the button line
        NodeBarH pageSelector = new NodeBarH(buttons, MyPos.CENTER);

        // Fit to width of the parent
        contentContainer.setFitToWidth(true);

        // Add the content to this
        contentContainer.setContent(itemContainer);

        // Set the minimum height of the contentContainer
        contentContainer.setMinHeight(minHeight);

        // Add the nodes to the content container
        this.getChildren().addAll(contentContainer,pageSelector);

        // Update list to page 1
        this.updatePage(1,nodesPrPage);

    }

    private void updatePage(int pageNum, float nodesPrPage){

        // Clear the current itemContainer
        this.itemContainer.getChildren().clear();

        // Add the ones the user wants to see
        if (pageNum*nodesPrPage > this.nodes.size()){
            for (int i = (int)((pageNum-1)*nodesPrPage)+1; i <= this.nodes.size(); i++) {
                this.itemContainer.getChildren().add(this.nodes.get(i-1));
            }
        } else {
            for (int i = (int)(pageNum==1 ? 1 : ((pageNum-1)*nodesPrPage)+1); i <= pageNum*nodesPrPage; i++) {
                this.itemContainer.getChildren().add(this.nodes.get(i-1));
            }
        }
    }

}
