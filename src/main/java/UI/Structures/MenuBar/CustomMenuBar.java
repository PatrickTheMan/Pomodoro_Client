package UI.Structures.MenuBar;

import UI.Buttons.CustomButton;
import UI.Structures.MenuBar.Parts.CustomMenuBarPart;
import UI.Structures.MenuBar.Parts.CustomMenuSeperator;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class CustomMenuBar extends VBox {

    private ArrayList<CustomButton> customMenuButtonArrayList = new ArrayList<>();

    public CustomMenuBar(ArrayList<CustomButton> arrayMenuButtonList){

        // Copy the arraylist over to the menubar objects own arraylist
        this.customMenuButtonArrayList.addAll(arrayMenuButtonList);

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("custom-menu-bar");

        // Add in the buttons in the menubar
        for (CustomButton c : arrayMenuButtonList) {
            this.getChildren().addAll(new CustomMenuBarPart(c),new CustomMenuSeperator());
        }



    }



}
