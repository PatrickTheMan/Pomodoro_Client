package UI.Structures.MenuBar;

import UI.Buttons.CustomButton;
import UI.Structures.MenuBar.Parts.CustomMenuBarPart;
import UI.Structures.MenuBar.Parts.CustomMenuSeperator;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * @author Patrick G. Schemel
 */
public class CustomMenuBar extends VBox {

    //region [Variables]

    private ArrayList<CustomButton> customMenuButtonArrayList = new ArrayList<>();

    //endregion

    //region [Constructor]

    public CustomMenuBar(ArrayList<CustomButton> arrayMenuButtonList){

        // Copy the arraylist over to the menubar objects own arraylist
        this.customMenuButtonArrayList.addAll(arrayMenuButtonList);

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("custom-menu-bar");

        // Add in the buttons in the menubar
        for (CustomButton c : arrayMenuButtonList) {
            // Make this object use the custom css styling
            c.getStyleClass().add("custom-menu-button");

            this.getChildren().addAll(new CustomMenuBarPart(c),new CustomMenuSeperator());
        }

    }

    //endregion

}
