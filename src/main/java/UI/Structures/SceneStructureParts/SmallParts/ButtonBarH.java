package UI.Structures.SceneStructureParts.SmallParts;

import UI.Buttons.CustomButton;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class ButtonBarH extends HBox {

    public ButtonBarH(ArrayList<CustomButton> buttonArrayList){

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("button-bar-h");

        // Set alignment
        this.setAlignment(Pos.BOTTOM_RIGHT);

        // Add in the buttons in the bar
        for (CustomButton c : buttonArrayList) {
            this.getChildren().add(c);
        }

    }

    public ButtonBarH(CustomButton customButton){

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("button-bar-h");

        // Set alignment
        this.setAlignment(Pos.BOTTOM_RIGHT);

        // Add in the button in the bar
        this.getChildren().add(customButton);

    }

}
