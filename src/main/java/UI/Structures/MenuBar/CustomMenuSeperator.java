package UI.Structures.MenuBar;

import javafx.geometry.Orientation;
import javafx.scene.control.Separator;

public class CustomMenuSeperator extends Separator {

    public CustomMenuSeperator(){
        this.getStyleClass().add("custom-menu-seperator");
        //this.setOrientation(Orientation.HORIZONTAL);
    }

}
