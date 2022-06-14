package UI.Structures.SceneStructureParts.Windows;

import UI.Enums.MyPos;
import UI.Enums.MyScaling;
import UI.Enums.MyShape;
import UI.Structures.SceneStructureParts.SmallParts.Headline;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * @author Patrick G. Schemel
 */
public class CustomWindow extends VBox {

    //region [Constructor]

    public CustomWindow(){
        // Make this object use the custom css styling
        this.getStyleClass().add("custom-window");
    }

    //endregion

    //region [Windows]

    /**
     * <Strong>Makes a custom window with a headline as content</Strong>
     * @param text is the text of the headline
     * @return this window
     */
    public CustomWindow headline(String text){

        // Create the headline and set settings
        Headline headline = new Headline(text);
        headline.setPos(MyPos.LEFT);
        headline.setShape(MyShape.ROUND);
        headline.setScaling(MyScaling.BIG);

        // Set content
        this.getChildren().add(headline);

        return this;
    }

    /**
     * <Strong>Makes a custom window with a settingsWindow and a headline as content</Strong>
     * @return this window
     */
    public CustomWindow settings(){

        // Create the headline and set settings
        Headline headline = new Headline("Settings");
        headline.setPos(MyPos.CENTER);
        headline.setShape(MyShape.HALFROUND);
        headline.setScaling(MyScaling.SMALL);

        // Set content
        this.getChildren().addAll(headline,new SettingsWindow());

        return this;
    }

    /**
     * <Strong>Makes a custom window with a pomodoroTimerWindow and a headline as content</Strong>
     * @return this window
     */
    public CustomWindow pomodoro(){

        // Create the headline and set settings
        Headline headline = new Headline("Pomodoro");
        headline.setPos(MyPos.CENTER);
        headline.setShape(MyShape.HALFROUND);
        headline.setScaling(MyScaling.SMALL);

        // Set content
        this.getChildren().addAll(headline,new PomodoroTimerWindow());

        return this;
    }

    /**
     * <Strong>Makes a custom window with a nodePages and a headline as content</Strong>
     * @param text is the text of the headline
     * @param arrayList is the arraylist which contains the nodes for the nodePages
     * @param nodesPrPage is the pref amount of nodes pr page
     * @param offset is the offest for custom scaling of the headline (set to 15 - 25 font size)
     * @param addNodeButton is whether to have an add taskline button or not
     * @return this window
     */
    public CustomWindow nodePage(String text,ArrayList<Node> arrayList,int nodesPrPage,int offset, boolean addNodeButton){

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

    //endregion

    //region [Option Methods]

    /**
     * <Strong>Set the min and max size of the customWindow</Strong>
     * @param minWidth is the min width
     * @param minHeight is the min height
     * @param maxWidth is the max width
     * @param maxHeight is the max height
     */
    public void setMinMaxSize(int minWidth, int minHeight, int maxWidth, int maxHeight){
        // Settings
        this.setMinSize(minWidth,minHeight);
        this.setMaxSize(maxWidth,maxHeight);
    }

    //endregion

}
