package UI.Structures.SceneStructureParts.Windows;

import Domain.Singletons.InformationContainerSingleton;
import UI.Structures.SceneStructureParts.CustomWindow;
import UI.Structures.SceneStructureParts.SmallParts.NodePages;
import javafx.scene.Node;

import java.util.ArrayList;

/**
 * @author Patrick G. Schemel
 */
public class NodePageWindow extends CustomWindow {

    //region [Constructor]

    public NodePageWindow(ArrayList<Node> nodes , int nodesPrPage, boolean addNodeButton){
        // Normal setup
        normalSetup(nodes,nodesPrPage,addNodeButton);
        // Set scaling of the window
        this.prefHeightProperty().bind(this.heightProperty());
    }

    //endregion

    //region [Normal Setup]

    /**
     * <Strong>The normal setup for the nodePageWindow, contains a headline and a nodepage</Strong>
     */
    private void normalSetup(ArrayList<Node> nodes , int nodesPrPage, boolean addNodeButton){

        // Make this object use the custom css styling
        this.getStyleClass().add("custom-window-nodepages");

        NodePages nodePages;

        // Add the list with scaling
        if (InformationContainerSingleton.getInstance().getActiveNodePage()!=null && addNodeButton){
            nodePages = new NodePages(InformationContainerSingleton.getInstance().getActiveNodePage().getNodes(),nodesPrPage,addNodeButton);
        } else {
            nodePages = new NodePages(nodes,nodesPrPage,addNodeButton);
        }


        // Add the content to the class object
        this.getChildren().addAll(nodePages);

        // Set the newest created to the current one
        if (addNodeButton){
            InformationContainerSingleton.getInstance().setActiveNodePage(nodePages);
        } else {
            InformationContainerSingleton.getInstance().setActiveOverviewNodePage(nodePages);
        }

    }

    //endregion

}
