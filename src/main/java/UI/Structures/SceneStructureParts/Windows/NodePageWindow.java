package UI.Structures.SceneStructureParts.Windows;

import UI.Structures.SceneStructureParts.CustomWindow;
import UI.Structures.SceneStructureParts.SmallParts.NodePages;
import javafx.scene.Node;

import java.util.ArrayList;

public class NodePageWindow extends CustomWindow {

    /**
     *
     */
    public NodePageWindow(ArrayList<Node> nodes , int nodesPrPage, int height){
        // Normal setup
        NormalSetup(nodes,nodesPrPage,height);
    }


    /**
     *
     */
    private void NormalSetup(ArrayList<Node> nodes , int nodesPrPage, int height){

        // Make this object use the custom css styling
        this.getStyleClass().add("custom-window-nodepages");

        // Add the list
        NodePages nodePage = new NodePages(nodes,nodesPrPage,height);

        // Add the content to the class object
        this.getChildren().addAll(nodePage);

    }

}
