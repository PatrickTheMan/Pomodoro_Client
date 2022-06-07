package UI.Structures.SceneStructureParts;

import UI.Enums.MyPos;
import UI.Enums.MyScaling;
import UI.Enums.MyShape;
import UI.Structures.SceneStructureParts.SmallParts.Headline;
import UI.Structures.SceneStructureParts.SmallParts.NodeBarH;
import UI.Structures.SceneStructureParts.SmallParts.NodePages;
import UI.Structures.SceneStructureParts.Windows.NodePageWindow;
import UI.Structures.SceneStructureParts.Windows.PomodoroTimerWindow;
import UI.Structures.SceneStructureParts.Windows.SettingsWindow;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class CustomWindow extends VBox {

    public CustomWindow(){
        // Make this object use the custom css styling
        this.getStyleClass().add("custom-window");
    }

    public CustomWindow Headline(String text){

        // Create the headline and set settings
        Headline headline = new Headline(text);
        headline.setPos(MyPos.LEFT);
        headline.setShape(MyShape.ROUND);
        headline.setScaling(MyScaling.BIG);

        // Set content
        this.getChildren().add(headline);

        return this;
    }

    public CustomWindow Headline(String text, MyPos pos){

        // Create the headline and set settings
        Headline headline = new Headline(text);
        headline.setPos(pos);
        headline.setShape(MyShape.ROUND);
        headline.setScaling(MyScaling.BIG);

        // Set content
        this.getChildren().add(headline);

        return this;
    }

    public CustomWindow ButtonBarH(ArrayList<Node> nodes){

        // Set content
        this.getChildren().add(new NodeBarH(nodes));

        return this;
    }

    public CustomWindow ButtonBarH(ArrayList<Node> nodes, MyPos pos){

        // Set content
        this.getChildren().add(new NodeBarH(nodes, pos));

        return this;
    }

    public CustomWindow Settings(){

        // Create the headline and set settings
        Headline headline = new Headline("Settings");
        headline.setPos(MyPos.CENTER);
        headline.setShape(MyShape.HALFROUND);
        headline.setScaling(MyScaling.SMALL);

        // Set content
        this.getChildren().addAll(headline,new SettingsWindow());

        return this;
    }

    public CustomWindow Pomodoro(){

        // Create the headline and set settings
        Headline headline = new Headline("Pomodoro");
        headline.setPos(MyPos.CENTER);
        headline.setShape(MyShape.HALFROUND);
        headline.setScaling(MyScaling.SMALL);

        // Set content
        this.getChildren().addAll(headline,new PomodoroTimerWindow());

        return this;
    }

    public CustomWindow NodePage(String text,ArrayList<Node> arrayList,int nodesPrPage,int offset, boolean addNodeButton){

        // Create the headline and set settings
        Headline headline = new Headline(text);
        headline.setPos(MyPos.CENTER);
        headline.setShape(MyShape.HALFROUND);
        headline.setScaling(15,25,offset);

        // Create the nodepage window
        NodePageWindow nodePagesWindow = new NodePageWindow(arrayList,nodesPrPage,addNodeButton);
        // Scaling of the list
        nodePagesWindow.prefHeightProperty().bind(this.heightProperty());

        // Set content
        this.getChildren().addAll(headline,nodePagesWindow);

        // Make this object use the custom css styling
        this.getStyleClass().add("custom-window-list");

        return this;
    }




    public void setMinMaxSize(int minWidth, int minHeight, int maxWidth, int maxHeight){
        // Settings
        this.setMinSize(minWidth,minHeight);
        this.setMaxSize(maxWidth,maxHeight);
    }

}
