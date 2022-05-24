package UI.Structures.MenuBar.Parts;

import UI.Buttons.Menu.CustomMenuButton;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class CustomMenuBarPart extends StackPane{

    private static final Duration ANIMATION_SPEED = Duration.seconds(0.1);

    public CustomMenuBarPart(CustomMenuButton customMenuButton){

        // Make this object use the custom-menu css styling
        this.getStyleClass().add("custom-menu-bar-part");

        // The text that will be shown
        Label label = new Label();
        label.setText(customMenuButton.getButtonText());

        // Add the label behind the menu button
        this.getChildren().addAll(label,customMenuButton);


        TranslateTransition transition = new TranslateTransition(ANIMATION_SPEED, label);

        this.setOnMouseEntered(e -> {

            transition.stop();

            transition.setToX(this.getWidth());

            transition.play();

        });

        this.setOnMouseExited(e -> {

            transition.stop();

            transition.setToX(0);

            transition.play();
        });



    }

}
