package UI.Structures.SceneStructureParts.SmallParts;

import Application.Singleton.ControllerSingleton;
import Foundation.Singletons.InformationContainerSingleton;
import UI.Buttons.CustomButton;
import UI.Enums.MyPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * A simple scrollpane which has a multiple pages function, so it only shows some nodes at a time
 * @author Patrick G. Schemel
 * @version 0.1
 */
public class NodePages extends VBox {

    //region [Variables]

    private ArrayList<Node> buttons= new ArrayList<>();
    private ArrayList<Node> nodes = new ArrayList<>();

    private int nodesPrPage;
    private int currentPage;
    private boolean addNodeButton;

    private ScrollPane contentContainer;
    private VBox itemContainer;
    private NodeBarH pageSelector;

    //endregion

    //region [Normal Getters & Setters]

    public int getNodesPrPage() {
        return nodesPrPage;
    }

    public ArrayList<Node> getButtons() {
        return buttons;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    //endregion

    //region [Constructor]

    public NodePages(ArrayList<Node> nodes, int nodesPrPage, boolean addNodeButton){

        // Set the initial value
        this.nodesPrPage=nodesPrPage;
        // Add button on/off?
        this.addNodeButton=addNodeButton;
        // Normal setup
        normalSetup(nodes);
        // Set the scaling
        this.contentContainer.prefHeightProperty().bind(this.heightProperty());
        this.itemContainer.prefHeightProperty().bind(this.contentContainer.heightProperty());

        // Adjust the node amount pr. page, so it fits the window height
        this.itemContainer.prefHeightProperty().addListener((obs,old,newVal) -> {
            if ((newVal.intValue()-20)/98!=0){
                this.nodesPrPage = (newVal.intValue()-20)/98;
                updatePageContent(false);
                updatePageButtons();
            } else {
                this.nodesPrPage=1;
            }
        });
    }

    //endregion

    //region [Normal Setup]

    /**
     * <Strong>The normal setup of the nodePages</Strong>
     * @param nodes is the initial content of the nodePages
     */
    private void normalSetup(ArrayList<Node> nodes){

        // Make this use the custom css styling
        this.getStyleClass().add("node-pages");

        // Set alignment
        this.setAlignment(Pos.TOP_CENTER);

        // Add in the node in arraylist
        for (Node n : nodes) {
            // Add the node
            this.nodes.add(n);
        }

        // Create the content container
        this.contentContainer = new ScrollPane();

        // Make the contentContainer use the custom css styling
        this.contentContainer.getStyleClass().add("node-pages-container");

        // Fit to width of the parent
        this.contentContainer.setFitToWidth(true);

        InformationContainerSingleton.getInstance().updateNodePagesProperty().addListener((obs,old,newVal) -> {
            // Update content and buttons
            updatePageContent(true);
            updatePageButtons();
            // Set it back to false
            InformationContainerSingleton.getInstance().updateNodePagesProperty().setValue(false);
        });

        // Create the item container
        this.itemContainer = new VBox();
        this.itemContainer.setAlignment(Pos.TOP_CENTER);
        this.itemContainer.setStyle("-fx-spacing: 10; -fx-padding: 10;");

        // Add the content to this
        this.contentContainer.setContent(itemContainer);

        // Add the nodes to the content container
        this.getChildren().add(this.contentContainer);

        // Get the right amount of buttons, 1 button pr page
        updatePageButtons();

    }

    //endregion

    //region [Pages]

    /**
     * <Strong>Update the buttons depending on the nodesprpage</Strong>
     */
    public void updatePageButtons(){

        // Clear buttons if it has been used and remove bar
        this.buttons.clear();

        // Add the buttons
        float f = 0;
        do {
            // Increment f
            f++;

            // Create buttons equal to amount needed
            CustomButton customButton = new CustomButton(""+(int)f);
            // Make this node use the custom css styling
            customButton.getStyleClass().add("node-pages-button-node");
            // Add functionality to the button
            customButton.setOnAction(e -> {

                // Set currentPage
                this.currentPage=Integer.parseInt(customButton.getText());

                // Update the page
                updatePageContent(false);

                // Remove focus
                customButton.setCustomFocused(false);

                // Make all other buttons not marked
                for (Node n: this.buttons) {
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
            this.buttons.add(customButton);

        } while (f < this.nodes.size()/(float)this.nodesPrPage);

        // Set the page
        if (this.currentPage>1){
            if (this.currentPage*this.nodesPrPage>this.nodes.size()){
                // Go to the page last available page
                ((Button)this.buttons.get(this.buttons.size()-1)).fire();
            } else {
                // Go to the current page
                ((Button)this.buttons.get(this.currentPage-1)).fire();
            }
        } else {
            // Go to the page, where it was added
            ((Button)this.buttons.get(0)).fire();
        }

        // Set the pagebar
        this.pageSelector = new NodeBarH(this.buttons);
        this.pageSelector.setPos(MyPos.CENTER);

        // Set the children again
        this.getChildren().setAll(this.contentContainer,this.pageSelector);

    }

    /**
     * <Strong>Update the nodePages content depending on the nodesprpage and current pagenum</Strong>
     * @param removeCurrentTask
     */
    public void updatePageContent(boolean removeCurrentTask){

        // Clear the current itemContainer
        this.itemContainer.getChildren().clear();

        // Remove the first task in line if it should be
        if (removeCurrentTask && this.nodes.size()>0){
            if (!this.nodes.get(0).getClass().getName().equals("UI.Structures.SceneStructureParts.SmallParts.Projectline") && ((Taskline)this.nodes.get(0)).getCounter().getLabel().getText().equals("0")){
                this.nodes.remove(0);
            }
        }

        // Add the ones the user wants to see
        if (this.currentPage*this.nodesPrPage > this.nodes.size()){
            for (int i = ((this.currentPage-1)*this.nodesPrPage)+1; i <= this.nodes.size(); i++) {
                this.itemContainer.getChildren().add(this.nodes.get(i-1));
            }
        } else {
            for (int i = (this.currentPage==1 ? 1 : ((this.currentPage-1)*this.nodesPrPage)+1); i <= this.currentPage*this.nodesPrPage; i++) {
                this.itemContainer.getChildren().add(this.nodes.get(i-1));
            }
        }

        if (this.addNodeButton){
            // Create the add node button
            CustomButton addNewNode = new CustomButton().other().add();
            addNewNode.setOnAction(e -> {

                // 1 more page or not
                if (this.buttons.size()*this.nodesPrPage <= this.nodes.size()){

                    // Save the taskline in the informationContainer
                    ControllerSingleton.getInstance().newTasklineInDoToday(new Taskline());

                    // Get new information
                    this.nodes.clear();
                    this.nodes.addAll(InformationContainerSingleton.getInstance().getDoTodayList());

                    updatePageButtons();

                } else {

                    // Save the taskline in the informationContainer
                    ControllerSingleton.getInstance().newTasklineInDoToday(new Taskline());

                    // Get new information
                    this.nodes.clear();
                    this.nodes.addAll(InformationContainerSingleton.getInstance().getDoTodayList());

                    updatePageContent(false);
                }

                // Go to the page, where it was added
                ((Button)this.buttons.get(this.buttons.size()-1)).fire();

                // Set the children again
                this.getChildren().setAll(this.contentContainer,this.pageSelector);

            });

            this.itemContainer.getChildren().add(addNewNode);
        }

    }

    //endregion

    //region [Nodes]

    /**
     * <Strong>Remove a node from the nodePage</Strong>
     * @param node is the node to be removed
     */
    public void removeNode(Node node){

        // 1 less page or not
        if (buttons.size()*nodesPrPage >= this.nodes.size()){

            this.nodes.remove(node);

            updatePageButtons();

        } else {

            this.nodes.remove(node);

            updatePageContent(false);
        }

        if (this.currentPage>this.buttons.size()){
            // Go to the last page
            ((Button)this.buttons.get(this.buttons.size()-1)).fire();
        } else {
            // Go to the page, where it was removed from
            ((Button)this.buttons.get(this.currentPage-1)).fire();
        }


        // Set the children again
        this.getChildren().setAll(this.contentContainer,this.pageSelector);

    }

    //endregion

}
