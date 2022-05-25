package UI.Structures.SceneStructureParts;

import UI.Structures.SceneStructureParts.SmallParts.Headline;
import UI.Structures.SceneStructureParts.Windows.SettingsWindow;
import javafx.scene.layout.VBox;

public class CustomWindow extends VBox {

    public CustomWindow(){
        // Make this object use the custom css styling
        this.getStyleClass().add("custom-window");
    }

    public CustomWindow settings(){

        // Set content
        this.getChildren().addAll(new Headline("Settings"),new SettingsWindow());

        return this;
    }

    public CustomWindow settings(int height){

        // Set content
        this.getChildren().addAll(new Headline("Settings"),new SettingsWindow());

        // Set preferred size
        this.setPrefHeight(height);
        this.setMinHeight(height);
        this.setMaxHeight(height);

        return this;
    }

    public CustomWindow settings(int width, int height){

        // Set content
        this.getChildren().addAll(new Headline("Settings"),new SettingsWindow());

        // Set preferred size
        this.setPrefSize(width,height);
        this.setMinSize(width,height);
        this.setMaxSize(width,height);

        return this;
    }

}
