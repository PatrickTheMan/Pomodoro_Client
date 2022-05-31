package UI.Structures.SceneStructureParts;

import Domain.Singletons.ConsultantSingleton;
import Domain.Singletons.TimerSingleton;
import UI.Enums.MyPos;
import UI.Enums.MyShape;
import UI.Structures.SceneStructureParts.SmallParts.Headline;
import UI.Structures.SceneStructureParts.SmallParts.NodeBarH;
import UI.Structures.SceneStructureParts.Windows.PomodoroTimerWindow;
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

    public CustomWindow Headline(String text, MyPos pos){

        // Set content
        this.getChildren().add(new Headline(text, pos, MyShape.ROUND));

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

    public CustomWindow Pomodoro(){

        // Set content
        this.getChildren().addAll(new Headline("Pomodoro", MyPos.CENTER, MyShape.HALFROUND),new PomodoroTimerWindow());

        // Set the timer
        Headline timerline = new Headline("", MyPos.CENTER, MyShape.ROUND);
        timerline.getLabel().textProperty().bind(TimerSingleton.getInstance().timeProperty());

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("custom-window-pomodoro-timer");

        return this;
    }

    public CustomWindow Pomodoro(int maxWidth, int maxHeight){

        // Set content
        this.getChildren().addAll(new Headline("Pomodoro", MyPos.CENTER, MyShape.HALFROUND),new PomodoroTimerWindow());

        // Settings
        this.setMaxWidth(maxWidth);
        this.setMaxHeight(maxHeight);

        return this;
    }

    public CustomWindow Pomodoro(int minWidth, int maxWidth, int minHeight, int maxHeight){

        // Set content
        this.getChildren().addAll(new Headline("Pomodoro", MyPos.CENTER, MyShape.HALFROUND), new PomodoroTimerWindow());

        // Settings
        this.setMinSize(minWidth,minHeight);
        this.setMaxSize(maxWidth,maxHeight);

        return this;
    }

}
