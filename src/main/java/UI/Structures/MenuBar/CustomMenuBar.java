package UI.Structures.MenuBar;

import UI.Buttons.Menu.CustomMenuButton;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class CustomMenuBar extends VBox {

    public CustomMenuBar(ArrayList<CustomMenuButton> arrayMenuButtonList){

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("custom-menu-bar");

        // Add in the buttons in the menubar
        for (CustomMenuButton c : arrayMenuButtonList) {
            this.getChildren().add(c);
            this.getChildren().add(new CustomMenuSeperator());
        }

    }



}
