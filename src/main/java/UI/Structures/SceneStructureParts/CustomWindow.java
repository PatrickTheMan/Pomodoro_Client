package UI.Structures.SceneStructureParts;

import UI.Enums.MyPos;
import UI.Enums.MyShape;
import UI.Structures.SceneStructureParts.SmallParts.Headline;
import UI.Structures.SceneStructureParts.SmallParts.NodeBarH;
import UI.Structures.SceneStructureParts.Windows.SettingsWindow;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class CustomWindow extends VBox {

    public CustomWindow(){
        // Make this object use the custom css styling
        this.getStyleClass().add("custom-window");
    }

    public CustomWindow Headline(String text){

        // Set content
        this.getChildren().add(new Headline(text, MyPos.LEFT, MyShape.ROUND));

        return this;
    }

    public CustomWindow Headline(String text, ArrayList<Node> nodes){

        // Set content
        this.getChildren().add(new Headline(text, nodes, MyPos.LEFT, MyShape.ROUND));

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

        // Set content
        this.getChildren().addAll(new Headline("Settings", MyPos.CENTER, MyShape.HALFROUND),new SettingsWindow());

        return this;
    }

    public CustomWindow Settings(int maxWidth, int maxHeight){

        // Set content
        this.getChildren().addAll(new Headline("Settings", MyPos.CENTER, MyShape.HALFROUND),new SettingsWindow());

        // Settings
        this.setMaxWidth(maxWidth);
        this.setMaxHeight(maxHeight);

        return this;
    }

    public CustomWindow Settings(int minWidth, int maxWidth, int minHeight, int maxHeight){

        // Set content
        this.getChildren().addAll(new Headline("Settings", MyPos.CENTER, MyShape.HALFROUND), new SettingsWindow());

        // Settings
        this.setMinSize(minWidth,minHeight);
        this.setMaxSize(maxWidth,maxHeight);

        return this;
    }

}
