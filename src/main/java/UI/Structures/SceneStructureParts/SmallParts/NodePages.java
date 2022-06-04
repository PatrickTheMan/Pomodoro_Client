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

public class NodePages extends VBox {

    private ArrayList<Node> buttons= new ArrayList<>();
    private ArrayList<Node> nodes = new ArrayList<>();

    private int nodesPrPage;
    private int height;
    private int currentPage;

    private ScrollPane contentContainer;
    private VBox itemContainer;
    private NodeBarH pageSelector;


    public int getNodesPrPage() {
        return nodesPrPage;
    }

    public ArrayList<Node> getButtons() {
        return buttons;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public NodePages(ArrayList<Node> nodes, int nodesPrPage){
        // Set the initial value
        this.nodesPrPage=nodesPrPage;
        // Normal setup
        normalSetup(nodes);
        // Set the scaling
        this.contentContainer.prefHeightProperty().bind(this.heightProperty());
        this.itemContainer.prefHeightProperty().bind(this.contentContainer.heightProperty());

        // Adjust the node amount pr. page, so it fits the window height
        this.itemContainer.prefHeightProperty().addListener((obs,old,newVal) -> {

            if ((newVal.intValue()-30)/105 == this.nodesPrPage-1){

                this.nodesPrPage = (newVal.intValue()-30)/105;

                //TODO - Needs to remove and add tasks and pages

                System.out.println("1");

            }

            if ((newVal.intValue()-30)/105 == this.nodesPrPage+1){

                this.nodesPrPage = (newVal.intValue()-30)/105;

                //TODO - Needs to remove and add tasks and pages

                System.out.println("2");

            }

        });
    }

    public NodePages(ArrayList<Node> nodes ,int nodesPrPage, int height){
        // Set the initial value
        this.nodesPrPage=nodesPrPage;
        // Normal setup
        normalSetup(nodes);
        // Set min height
        this.contentContainer.setMinHeight(height);
        this.contentContainer.setMaxHeight(height);
    }

    private void normalSetup(ArrayList<Node> nodes){

        // Make this use the custom css styling
        this.getStyleClass().add("node-pages");

        // Set alignment
        this.setAlignment(Pos.TOP_CENTER);

        // Add in the node in arraylist
        for (Node n : nodes) {
            // Make this node use the custom css styling
            n.getStyleClass().add("node-pages-container-node");
            // Add the node
            this.nodes.add(n);
        }

        // Create the content container
        this.contentContainer = new ScrollPane();

        // Make the contentContainer use the custom css styling
        this.contentContainer.getStyleClass().add("node-pages-container");

        // Fit to width of the parent
        this.contentContainer.setFitToWidth(true);

        // Get the content
        getContent();

        // Get the right amount of buttons, 1 button pr page
        getPages();

    }

    public void getContent(){

        // Create the item container
        this.itemContainer = new VBox();
        this.itemContainer.setAlignment(Pos.TOP_CENTER);
        this.itemContainer.setStyle("-fx-spacing: 10; -fx-padding: 10;");

        // Add the content to this
        this.contentContainer.setContent(itemContainer);

        // Add the nodes to the content container
        this.getChildren().addAll(contentContainer);
    }

    public void getPages(){

        // Make the right amount of buttons
        updatePageButtons();

        // Add the nodes to the content container
        this.getChildren().addAll(this.pageSelector);

    }

    public void updatePageButtons(){

        // Clear buttons if it has been used
        this.buttons.clear();

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

                // Set currentPage
                this.currentPage=Integer.parseInt(customButton.getText());

                // Update the page
                updatePageContent();

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

            // Update list to page 1
            if (customButton.getText().equals("1")){
                customButton.fire();
            }

            // Add to arraylist
            this.buttons.add(customButton);

        } while (f < this.nodes.size()/(float)this.nodesPrPage);

        // Create the button line and add a little padding
        this.pageSelector = new NodeBarH(this.buttons, MyPos.CENTER);
    }

    public void updatePageContent(){

        // Clear the current itemContainer
        this.itemContainer.getChildren().clear();

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

        // Create the add node button
        CustomButton addNewNode = new CustomButton().Other().Add();
        addNewNode.setOnAction(e -> {

            // 1 more page or not
            if (this.buttons.size()*this.nodesPrPage <= this.nodes.size()){

                // Save the taskline in the informationContainer
                ControllerSingleton.getInstance().newTasklineInDoToday(new Taskline(this));

                // Get new information
                this.nodes.clear();
                this.nodes.addAll(InformationContainerSingleton.getInstance().getDoTodayList());

                updatePageButtons();

            } else {

                // Save the taskline in the informationContainer
                ControllerSingleton.getInstance().newTasklineInDoToday(new Taskline(this));

                // Get new information
                this.nodes.clear();
                this.nodes.addAll(InformationContainerSingleton.getInstance().getDoTodayList());

                updatePageContent();
            }

            // Go to the page, where it was added
            ((Button)this.buttons.get(this.buttons.size()-1)).fire();

            // Set the children again
            this.getChildren().setAll(this.contentContainer,this.pageSelector);

        });
        this.itemContainer.getChildren().add(addNewNode);

    }

    public void removeNode(Node node){
        // 1 more page or not
        if (buttons.size()*nodesPrPage >= this.nodes.size()){

            this.nodes.remove(node);

            updatePageButtons();

        } else {

            this.nodes.remove(node);

            updatePageContent();
        }

        // Go to the page, where it was added
        ((Button)this.buttons.get(this.buttons.size()-1)).fire();

        // Set the children again
        this.getChildren().setAll(this.contentContainer,this.pageSelector);

    }

}
