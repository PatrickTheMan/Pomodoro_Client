package UI.Structures.SceneStructureParts.Windows;

import UI.Structures.SceneStructureParts.CustomWindow;
import UI.Structures.SceneStructureParts.SmallParts.NodePages;
import javafx.scene.Node;

import java.util.ArrayList;

public class NodePageWindow extends CustomWindow {

    /**
     *
     */
    public NodePageWindow(ArrayList<Node> nodes , int nodesPrPage,int height){
        // Normal setup
        NormalSetup(nodes,nodesPrPage,height);
    }

    /**
     *
     */
    public NodePageWindow(ArrayList<Node> nodes , int nodesPrPage){
        // Normal setup
        NormalSetup(nodes,nodesPrPage,-1);
        // Set scaling of the window
        this.prefHeightProperty().bind(this.heightProperty());
    }


    /**
     *
     */
    private void NormalSetup(ArrayList<Node> nodes , int nodesPrPage, int height){

        // Make this object use the custom css styling
        this.getStyleClass().add("custom-window-nodepages");

        NodePages nodePage;

        // Add the list
        if (height==-1){
            // With scaling
            nodePage = new NodePages(nodes,nodesPrPage);
        } else {
            // Without scaling
            nodePage = new NodePages(nodes,nodesPrPage, height);
        }

        // Add the content to the class object
        this.getChildren().addAll(nodePage);

    }

}
